package com.hangang.HangangRiver.weather.web;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hangang.HangangRiver.weather.model.Weather;
import com.hangang.HangangRiver.weather.service.WeatherService;

@Component
public class WeatherScheduler {
	private String todayTmn = "0";
	private String todayTmx = "0";
	private String nx = "59";
	private String ny = "126";

	@Autowired
	WeatherService weatherService;

	public Weather getNowWeather() throws ParseException, IOException{
		//현재기온, 현재 구름 상태 , 현재 강수상태 조회
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
		String baseDate = dateFormat.format(today);
		//String baseTime = timeFormat.format(today)+"40";
		String baseTime = "2240";
		String serviceKey = "wHkULcMomTpz3wVCYqSbrap5VrtdRQrZSTLd%2BcX4pwaZTjaVpDj9cF5fydh7%2BapE1tAwOBFUMJPrsTOBWTJUfA%3D%3D";
		String service = "ForecastGrib"; //ForecastSpaceData or ForecastGrib
		String urlStr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/"+service+"?"
	            + "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" + baseTime
	            + "&nx="+ nx + "&ny=" + ny + "&_type=json";
		URL url = new URL(urlStr);
		BufferedReader bf;
		String line = "";
		String result="";

		bf = new BufferedReader(new InputStreamReader(url.openStream()));

        while((line=bf.readLine())!=null){
            result=result.concat(line);
        }
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);

        JSONObject parse_response = (JSONObject) obj.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");

        String category;
        JSONObject weather;
        Weather nowWeather = new Weather();
        // 필요한 데이터만 가져오려고합니다.
        for(int i = 0 ; i < parse_item.size(); i++){
          weather = (JSONObject) parse_item.get(i);
          String fcst_Value = weather.get("obsrValue").toString(); //실수로된 값과 정수로된 값이 둘다 있어서 실수로 통일했습니다.
          category = (String)weather.get("category");
          if (category.contains("T1H")){
        	nowWeather.setT1h(fcst_Value);
          } else if (category.contains("PTY")){
        	nowWeather.setPty(fcst_Value);
          } 
          else if (category.contains("SKY")){
          	nowWeather.setSky(fcst_Value);
            }
        }
        System.out.println(nowWeather);
		return nowWeather;
	}

	public String getMinMaxWeather(){
		// 오늘 최저, 최고 기온 조회
		// 02시에 최저기온조회 후 tmn값변경
		// 02, 05, 08, 11시에 최고기온 조회해서있으면 tmx값 변경
		return null;
	}

	//@Scheduled(cron="* /45 * * * *")매시간 45분
	//@Scheduled(cron="*/30 * * * * *")30초마다
	public void setWeatherInformation() throws ParseException, IOException{
		System.out.println("hi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Weather weatherInformation = new Weather();
		weatherInformation = getNowWeather();
		weatherInformation.setTmn(todayTmn);
		weatherInformation.setTmx(todayTmx);
		weatherService.createWeatherInformation(weatherInformation);
	}
}
