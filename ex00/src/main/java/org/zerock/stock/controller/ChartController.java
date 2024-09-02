package org.zerock.stock.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.stock.vo.StockVO;

import lombok.extern.log4j.Log4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/chart")
@Log4j
public class ChartController {
	
	StockController sc = new StockController();
	
    private final String APP_KEY = "PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq"; // 실제 키로 교체 필요
    private final String APP_SECRET = "5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo="; // 실제 키로 교체 필요

    @GetMapping("/chart")
    public String getStockChartData(Model model, HttpSession session, StockVO vo) {
    	sc.getTokenP(session);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String token= (String) session.getAttribute("token");
        int code = vo.getCompany_id();
        String formattedCode = String.format("%06d", code);
        log.info(vo);
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
        		  .url("https://openapivts.koreainvestment.com:29443/uapi/domestic-stock/v1/quotations/"
        		  		+ "inquire-daily-itemchartprice?"
        		  		+ "fid_cond_mrkt_div_code=J"
        		  		+ "&fid_input_iscd="+formattedCode
        		  		+ "&fid_input_date_1="+vo.getStartDate()
        		  		+ "&fid_input_date_2="+vo.getEndDate()
        		  		+ "&fid_period_div_code="+vo.getPeriod_div_code()
        		  		+ "&fid_org_adj_prc=1")
        		  .get() // GET 요청은 body 없이 사용
        		  .addHeader("content-type", "application/json")
        		  .addHeader("authorization", "Bearer "+ token)
        		  .addHeader("appkey", APP_KEY)
        		  .addHeader("appsecret", APP_SECRET)
        		  .addHeader("tr_id", "FHKST03010100")
        		  .build();
        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                model.addAttribute("chartData", responseBody);
            } else {
                log.error("Request failed: " + response.code() + " - " + response.message());
                model.addAttribute("chartData", "Request failed: " + response.code());
            }

            response.close();
        } catch (IOException e) {
            log.error("Error occurred while getting stock chart data: " + e.getMessage(), e);
            model.addAttribute("chartData", "Error: " + e.getMessage());
        }

        return "chart/chart"; // 
    }

}
