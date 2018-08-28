package com.hangang.HangangRiver.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {
public static void main(String[] args) throws IOException, ParseException {
	String nx = "59";  //경도
	String ny = "126";   //위도
	String baseDate = "20180821"; // 자신이 조회하고싶은 날짜를 입력해주세요
	String baseTime = "1100"; //자신이 조회하고싶은 시간대를 입력해주세요
	String serviceKey = "wHkULcMomTpz3wVCYqSbrap5VrtdRQrZSTLd%2BcX4pwaZTjaVpDj9cF5fydh7%2BapE1tAwOBFUMJPrsTOBWTJUfA%3D%3D";
	String urlStr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?"
            + "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" + baseTime
            + "&nx="+ nx + "&ny=" + ny + "&_type=json";
        URL url = new URL(urlStr); // 위 urlStr을 이용해서 URL 객체를 만들어줍니다.
        BufferedReader bf;
        String line = "";
        String result="";

        bf = new BufferedReader(new InputStreamReader(url.openStream()));
        
        //버퍼에 있는 정보를 하나의 문자열로 변환.
        while((line=bf.readLine())!=null){
            result=result.concat(line);
           // System.out.println(result);  // 받아온 데이터를 확인해봅니다.
        }
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);
         
        // Top레벨 단계인 response 키를 가지고 데이터를 파싱합니다.
        JSONObject parse_response = (JSONObject) obj.get("response");
        // response 로 부터 body 찾아옵니다.
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        // body 로 부터 items 받아옵니다.
        JSONObject parse_items = (JSONObject) parse_body.get("items");
         
        // items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다
        JSONArray parse_item = (JSONArray) parse_items.get("item");
         
        String category;
        JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용합니다.
 
        // 필요한 데이터만 가져오려고합니다.
        for(int i = 0 ; i < parse_item.size(); i++)
        {
          weather = (JSONObject) parse_item.get(i);
          //String base_Date = (String)weather.get("baseDate");
          //String fcst_Time = (String)weather.get("fcstDate");
          String fcst_Value = weather.get("fcstValue").toString(); //실수로된 값과 정수로된 값이 둘다 있어서 실수로 통일했습니다.
          //String nX = (String)weather.get("nx");
          //String nY = (String)weather.get("ny");
          category = (String)weather.get("category");
          //String base_Time = (String)weather.get("baseTime");
          //String fcscDate = (String)weather.get("fcscDate");

          // 출력합니다.
          System.out.print("배열의 "+i+"번째 요소");
          System.out.print("   category : "+ category);
          System.out.print("   fcst_Value : "+ fcst_Value);
          System.out.print("");
        }
}
}
