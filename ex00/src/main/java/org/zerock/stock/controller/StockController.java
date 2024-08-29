package org.zerock.stock.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
@RequestMapping("/stock")
@Log4j
public class StockController {
	
    private final String API_URL = "https://openapivts.koreainvestment.com:29443/uapi/domestic-stock/v1/quotations/inquire-time-itemchartprice";
    private final String APP_KEY = "PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq"; // 실제 키로 교체 필요
    private final String APP_SECRET = "5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo="; // 실제 키로 교체 필요
    private String AUTH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImQyNTA1YjEzLTkzMjMtNDNiNy05YTdlLTJhZTczNzBmYTIxYiIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTcyNDgzNDA4NywiaWF0IjoxNzI0NzQ3Njg3LCJqdGkiOiJQU2RUUHQ2WTZZOGpsejJiWmF2eWxlbGEwTFB1bkl1UDlDQXEifQ.N6KP04-MNguns0xnZl7jrgWU7BOmbIFnq1SOsa-ZhR599q9iDKg6ksPlNnDeLb23fWaP6mhx8yS4F_W5XuDyjQ";

    @GetMapping("/stock-price")
    public String getStockPrice(Model model) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url("https://openapivts.koreainvestment.com:29443/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd=005930")
            .get()
            .addHeader("content-type", "application/json")
            .addHeader("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImFmMjdlZmM1LTc1NWYtNGNjZS1iMjA0LTZhNjA4Y2EzZGI3ZiIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTcyMzg4MTU3OCwiaWF0IjoxNzIzNzk1MTc4LCJqdGkiOiJQU2RUUHQ2WTZZOGpsejJiWmF2eWxlbGEwTFB1bkl1UDlDQXEifQ.Uu2HID2lHDmV-FrWHGp4p-26wsdiY1B8He8x0gKQie3flah13_62rxfFHQ38C0vfXyNvZdwowfbvj1Gdayx7oA")  // 여기에 실제 토큰 값을 넣어야 합니다.
            .addHeader("appkey", "PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq")
            .addHeader("appsecret", "5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo=")
            .addHeader("tr_id", "FHKST01010100")
            .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                model.addAttribute("priceData", responseBody);
            } else {
            	log.error("Request failed: " + response.code() + " - " + response.message());
                model.addAttribute("priceData", "Request failed: " + response.code());
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("priceData", "Error: " + e.getMessage());
        }

        return "stock/stockPrice"; // stockPrice.jsp로 전달
    }
    //(웹소켓) 접속키 
    @GetMapping("/get-token-webSocket")
    public String getToken(Model model) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // 요청 바디 생성
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"grant_type\": \"client_credentials\",\r\n    \"appkey\": \"PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq\",\r\n    \"secretkey\": \"5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo=\"\r\n}");
        
        // 요청 생성
        Request request = new Request.Builder()
                .url("https://openapivts.koreainvestment.com:29443/oauth2/Approval")
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        try {
            // 요청 보내기
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                // 응답 본문을 모델에 추가
                String responseBody = response.body().string();
                model.addAttribute("tokenData", responseBody);
            } else {
                model.addAttribute("tokenData", "Request failed: " + response.code());
            }

            response.close();
        } catch (IOException e) {
            log.error("Error occurred: " + e.getMessage(), e);
            model.addAttribute("tokenData", "Error: " + e.getMessage());
        }

        // JSP로 전달
        return "stock/token";
    }
    // 접근토큰발급 세션에 집어넣는다.
    @GetMapping("/get-token-p")
    public String getTokenP(HttpSession session) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // 요청 바디 생성 - APP_KEY와 APP_SECRET을 사용
        String requestBodyContent = String.format(
            "{\r\n    \"grant_type\": \"client_credentials\",\r\n    \"appkey\": \"%s\",\r\n    \"appsecret\": \"%s\"\r\n}", 
            APP_KEY, 
            APP_SECRET
        );

        RequestBody body = RequestBody.create(mediaType, requestBodyContent);
        
        // 요청 생성
        Request request = new Request.Builder()
                .url("https://openapivts.koreainvestment.com:29443/oauth2/tokenP")
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        try {
            // 요청 보내기
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                // 응답 본문을 JSON 형태로 파싱하여 access_token 추출
                String responseBody = response.body().string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                String accessToken = rootNode.path("access_token").asText();
                session.setAttribute("token", accessToken);
                log.info(AUTH_TOKEN);
                response.close();
                return accessToken;  // access_token 값 반환
            } else {
                response.close();
                return "Request failed: " + response.code();  // 실패 시 오류 코드 반환
            }

        } catch (IOException e) {
            log.error("Error occurred: " + e.getMessage(), e);
            return "Error: " + e.getMessage();  // 예외 발생 시 오류 메시지 반환
        }
    }


    @GetMapping("/websocketTest")
    public String webSocketTest(Model model) {
        String approvalKey = getApprovalKey(APP_KEY, APP_SECRET);
        
        model.addAttribute("app_key", APP_KEY);
        model.addAttribute("appsecret", APP_SECRET);
        model.addAttribute("approval_key", approvalKey);
        
        return "stock/webSocket"; // JSP 파일명에 맞게 변경
    }
    
    @GetMapping("/stockMain")
    public void stockMain(Model model) {

    }
    
    @GetMapping("/0")
    public String getStockChartData(Model model, HttpSession session) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String token= (String) session.getAttribute("token");
        String url = API_URL + "?FID_ETC_CLS_CODE=&FID_COND_MRKT_DIV_CODE=J&FID_INPUT_ISCD=005930&FID_INPUT_HOUR_1=094400&FID_PW_DATA_INCU_YN=Y";
        
        Request request = new Request.Builder()
                .url(url)
                .get() // GET 요청은 body 없이 사용
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + token)
                .addHeader("appkey", APP_KEY)
                .addHeader("appsecret", APP_SECRET)
                .addHeader("tr_id", "FHKST03010200")
                .build();
        log.info(AUTH_TOKEN);
        log.info(token);
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

        return "stock/chart"; // stockChart.jsp로 전달
    }
    
    private String getApprovalKey(String appKey, String appSecret) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, String.format("{\r\n    \"grant_type\": \"client_credentials\",\r\n    \"appkey\": \"%s\",\r\n    \"secretkey\": \"%s\"\r\n}", appKey, appSecret));

        Request request = new Request.Builder()
                .url("https://openapivts.koreainvestment.com:29443/oauth2/Approval")
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // JSON 파싱을 통해 approval_key 추출 (Gson 또는 Jackson 사용 가능)
                // 아래는 간단한 JSON 파싱 예시
                Map<String, Object> map = new ObjectMapper().readValue(responseBody, Map.class);
                return (String) map.get("approval_key");
            } else {
                log.error("Approval key request failed: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            log.error("Error occurred while getting approval key: " + e.getMessage(), e);
        }
        return null;
    }
}
