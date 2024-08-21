package org.zerock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.AllArgsConstructor;
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
@RequestMapping("/sample")
@Log4j
public class SampleController {
	@RequestMapping("") // /sample/
	// return이 없으면(void)이면 uri 정보를 jsp 정보로 사용
	// return이 String이면 redirect: -> redirect 시킨다. 없으면 jsp로 forward 시킨다.
	public void basic() {
		log.info("basic.............");
	}
	// @RequestMapping은 uri 맵핑이 get, post 방식만 허용
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		
		log.info("basic get/post ............");
		
	}
	// get만 사용. value 속성 하나만 남으면 기본으로 데이터가 들어가는 속성이 된다. 생략 가능
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		
		log.info("basic get only get.........");
		
	}
	
	// get 방식 맵핑
	@GetMapping("/ex01")
	// property(VO = DTO)로 넘어오는 데이터 받기(setter 이름과 name이 같으면 자동으로 받는다.)
	public String ex01(SampleDTO dto) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex01() : dto=" + dto);
	return "ex01";
	}
	// get 방식 맵핑
	@GetMapping("/ex02")
	// parameter 병수로 받기 - 변수명과 name이 같아야 한다. age가 없으면 오류가 난다.
	public String ex02(@RequestParam("name")String name, @RequestParam(defaultValue = "0" ) int age) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex02() : name=" + name + "age= " + age);
		return "ex02";
	}
	// get 방식 맵핑
	@GetMapping("/ex02List")
	// parameter 병수로 받기 - 아이디 여러개를 받아서 처리 - List / 배열
	// List로 여러개의 데이터를 받을 떄는 @RequestParam을 꼭 써야한다.
	public String ex02List(@RequestParam ArrayList<String> ids, String[] names) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex02List() : ids=" + ids + ", names= " + Arrays.toString(names));
		for (String id : ids) {
			log.info("ex02List() : id=" + id);
		}
		return "ex02List";
	}
	// get 방식 맵핑
	@GetMapping("/ex03")
	// parameter 병수로 받기 - 아이디 여러개를 받아서 처리 - List / 배열
	// List로 여러개의 데이터를 받을 떄는 @RequestParam을 꼭 써야한다.
	public String ex03(TodoDTO dto) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex03() : dto=" + dto);
		return "ex03";
	}
	// get 방식 맵핑
	@GetMapping("/ex06")
	// parameter 병수로 받기 - 아이디 여러개를 받아서 처리 - List / 배열
	// List로 여러개의 데이터를 받을 떄는 @RequestParam을 꼭 써야한다.
	public @ResponseBody SampleDTO ex06() {
		// /WEB-INF/views/ + ex01 + .jsp
		// DTO에 @AllArgsConstructor가 붙어있으면 생성할 때 파라미터값을 넣어준다.
		SampleDTO dto = new SampleDTO("홍길", 10);
//		dto.setName("홍길");
//		dto.setAge(10);
		log.info("ex03() : dto=" + dto);
		return dto;
	}
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		
		log.info("/ex07........");
		
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, headers, HttpStatus.OK);
	}
	// get 방식 맵핑
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload()..........");
	}
	@GetMapping("/stock")
	public void stock() {
		log.info("stock()..........");
	}
	@PostMapping("/exUploadPost")
	public void exUploadPost(@RequestParam ArrayList<MultipartFile> files){
		
		log.info("/exUploadPost........");
		
		for(MultipartFile file : files) {
			log.info("----------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		}
		
		files.forEach(file -> {
			log.info("--------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
		
		
	}
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

        return "sample/stockPrice"; // stockPrice.jsp로 전달
    }
    
    @GetMapping("/get-token")
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
        return "sample/token";
    }
    @GetMapping("/get-token-p")
    public String getTokenP(Model model) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // 요청 바디 생성
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"grant_type\": \"client_credentials\",\r\n    \"appkey\": \"PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq\",\r\n    \"appsecret\": \"5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo=\"\r\n}");
        
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
                // 응답 본문을 모델에 추가
                String responseBody = response.body().string();
                model.addAttribute("tokenDataP", responseBody);
            } else {
                model.addAttribute("tokenDataP", "Request failed: " + response.code());
            }

            response.close();
        } catch (IOException e) {
            log.error("Error occurred: " + e.getMessage(), e);
            model.addAttribute("tokenDataP", "Error: " + e.getMessage());
        }

        // JSP로 전달
        return "sample/tokenP";
    }
    @GetMapping("/chart")
    public String chart() {
        
        return "sample/chart"; // stockPrice.jsp로 전달
    }
}
