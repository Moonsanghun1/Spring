package org.zerock.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.util.Base64;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;

@ClientEndpoint
public class StockWebSocketClient {

    private static String aesKey;
    private static String aesIv;

    public static void main(String[] args) {
        String url = "ws://ops.koreainvestment.com:21000"; // 실전투자
        String gAppKey = "PSdTPt6Y6Y8jlz2bZavylela0LPunIuP9CAq";
        String gAppSecret = "5A9NiHMzRkPIxx6rujN5hkpZ/LI4lEU69Yh34G4b9YzUxgrSgQMPTMpztTzoXtdIytjMYr6UwlH+CMNQxI33p04UmV4c4KhKrNnWXmV0Y0Qpjp2+Tn4Jxg6iPNNNU5F0pt+m0NQ0ZDnuW+I0CKgjxYTYdwtu7QDmPF/5Z4CCYDVCqwot0zo=";
        String stockCode = "005930";  // 테스트용 임시 종목 설정, 삼성전자
        String htsId = "fireq0112";  // 체결통보용 htsid 입력
        String custType = "P";  // customer type, 개인:'P' 법인 'B'

        String approvalKey = getApprovalKey(gAppKey, gAppSecret);
        System.out.println("approval_key [" + approvalKey + "]");

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI uri = URI.create(url);
            Session session = container.connectToServer(StockWebSocketClient.class, uri);

            Scanner scanner = new Scanner(System.in);
            System.out.println("1.주식호가, 2.주식호가해제, 3.주식체결, 4.주식체결해제, 5.주식체결통보(고객), 6.주식체결통보해제(고객), 7.주식체결통보(모의), 8.주식체결통보해제(모의)");
            System.out.println("Input Command :");
            String cmd = scanner.nextLine();

            if (cmd.compareTo("0") <= 0 || cmd.compareTo("9") > 0) {
                System.out.println("> Wrong Input Data");
                return;
            } else if (cmd.equals("0")) {
                System.out.println("Exit!!");
                return;
            }

            String trId = "";
            String trType = "";
            if (cmd.equals("1")) {
                trId = "H0STASP0";
                trType = "1";
            } else if (cmd.equals("2")) {
                trId = "H0STASP0";
                trType = "2";
            } else if (cmd.equals("3")) {
                trId = "H0STCNT0";
                trType = "1";
            } else if (cmd.equals("4")) {
                trId = "H0STCNT0";
                trType = "2";
            } else if (cmd.equals("5")) {
                trId = "H0STCNI0"; // 고객체결통보
                trType = "1";
            } else if (cmd.equals("6")) {
                trId = "H0STCNI0"; // 고객체결통보
                trType = "2";
            } else if (cmd.equals("7")) {
                trId = "H0STCNI9";  // 테스트용 직원체결통보
                trType = "1";
            } else if (cmd.equals("8")) {
                trId = "H0STCNI9";  // 테스트용 직원체결통보
                trType = "2";
            }

            String sendData = createSendData(cmd, approvalKey, custType, trType, trId, stockCode, htsId);
            System.out.println("Input Command is :" + sendData);
            session.getAsyncRemote().sendText(sendData);

            // 서버로부터 데이터를 무한히 대기
            CompletableFuture<Void> future = new CompletableFuture<>();
            future.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getApprovalKey(String key, String secret) {
        // 웹소켓 접속키 발급
        // HTTP 요청을 통해 approval_key를 가져오는 부분 구현
        // 실제 API 요청을 대신해 하드코딩으로 리턴

        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImFmMjdlZmM1LTc1NWYtNGNjZS1iMjA0LTZhNjA4Y2EzZGI3ZiIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTcyMzg4MTU3OCwiaWF0IjoxNzIzNzk1MTc4LCJqdGkiOiJQU2RUUHQ2WTZZOGpsejJiWmF2eWxlbGEwTFB1bkl1UDlDQXEifQ.Uu2HID2lHDmV-FrWHGp4p-26wsdiY1B8He8x0gKQie3flah13_62rxfFHQ38C0vfXyNvZdwowfbvj1Gdayx7oA";
    }

    private static String createSendData(String cmd, String approvalKey, String custType, String trType, String trId, String stockCode, String htsId) {
        JsonObject jsonObject;
        if (cmd.equals("5") || cmd.equals("6") || cmd.equals("7") || cmd.equals("8")) {
            jsonObject = Json.createObjectBuilder()
                .add("header", Json.createObjectBuilder()
                    .add("approval_key", approvalKey)
                    .add("custtype", custType)
                    .add("tr_type", trType)
                    .add("content-type", "utf-8"))
                .add("body", Json.createObjectBuilder()
                    .add("input", Json.createObjectBuilder()
                        .add("tr_id", trId)
                        .add("tr_key", htsId)))
                .build();
        } else {
            jsonObject = Json.createObjectBuilder()
                .add("header", Json.createObjectBuilder()
                    .add("approval_key", approvalKey)
                    .add("custtype", custType)
                    .add("tr_type", trType)
                    .add("content-type", "utf-8"))
                .add("body", Json.createObjectBuilder()
                    .add("input", Json.createObjectBuilder()
                        .add("tr_id", trId)
                        .add("tr_key", stockCode)))
                .build();
        }

        return jsonObject.toString();
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Recev Command is :" + message);
        // 받은 메시지 처리 부분 구현
        JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
        String trid = jsonObject.getJsonObject("header").getString("tr_id");

        if (trid.equals("H0STCNI0") || trid.equals("H0STCNI9")) {
            aesKey = jsonObject.getJsonObject("body").getJsonObject("output").getString("key");
            aesIv = jsonObject.getJsonObject("body").getJsonObject("output").getString("iv");
            System.out.println("### TRID [" + trid + "] KEY[" + aesKey + "] IV[" + aesIv + "]");
        }

        // PINGPONG 응답 처리
        if (trid.equals("PINGPONG")) {
            System.out.println("### RECV [PINGPONG] [" + message + "]");
        }
    }

    public static String decryptAES(String key, String iv, String cipherText) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(cipherText));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
