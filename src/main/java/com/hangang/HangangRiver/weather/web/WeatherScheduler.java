package com.hangang.HangangRiver.weather.web;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private String serviceKey = "wHkULcMomTpz3wVCYqSbrap5VrtdRQrZSTLd%2BcX4pwaZTjaVpDj9cF5fydh7%2BapE1tAwOBFUMJPrsTOBWTJUfA%3D%3D";

	private static final Logger logger = LogManager.getLogger(WeatherScheduler.class);

	@Autowired
	WeatherService weatherService;


	//@Scheduled(cron="*/40 * * * * *")40초마다

	@Scheduled(cron="0 45 * * * *")//매시간 45분
	public void setWeatherInformation() throws IOException, ParseException{
		Weather weatherInformation = new Weather();
		weatherInformation = getNowWeather();

		String lastTmn = weatherService.selectWeather().getTmn();
		String lastTmx = weatherService.selectWeather().getTmx();

		if (Double.parseDouble(weatherInformation.getT1h()) < Double.parseDouble(todayTmn) || Double.parseDouble(todayTmn)<=0) {//최저온도가 현재온도보다 높을경우
			//weatherInformation.setTmn(weatherInformation.getT1h());
			//weatherInformation.setTmx(todayTmx);
			weatherInformation.setTmn(lastTmn);
			weatherInformation.setTmx(lastTmx);
		} else if (Double.parseDouble(weatherInformation.getT1h()) > Double.parseDouble(todayTmx) || Double.parseDouble(todayTmx)<=0) {//최고온도가 현재온도보다 낮을경우
			//weatherInformation.setTmn(todayTmn);
			//weatherInformation.setTmx(weatherInformation.getT1h());
			weatherInformation.setTmn(lastTmn);
			weatherInformation.setTmx(lastTmx);
		} else {
			weatherInformation.setTmn(todayTmn);
			weatherInformation.setTmx(todayTmx);
		}

		logger.error("[insertDBWeather::::"+weatherInformation+"nowTime::::"+new Date()+"todayWeather::::"+todayTmn+"--"+todayTmx+"]");
		System.out.println("[insertDBWeather::::"+weatherInformation+"nowTime::::"+new Date()+"todayWeather::::"+todayTmn+"--"+todayTmx+"]");

		weatherService.createWeatherInformation(weatherInformation);
	}

	public Weather getNowWeather() throws IOException, ParseException{
		//현재기온, 현재 구름 상태 , 현재 강수상태 조회
		Date today = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
		String baseTime = timeFormat.format(today)+"40";
		String service = "ForecastGrib";
		String serviceValue = "obsrValue";
		Weather resultWeather = callWeatherAPI(baseTime, service, serviceValue);
		return resultWeather;
	}

	@Scheduled(cron=" 0 10 02 * * * ")//새벽 2시 10분에 2시데이터 한번 조회
	public void setMinMaxWeather() throws IOException, ParseException{
		// 오늘 최저,최고기온 조회
		String baseTime = "0200";
		String service = "ForecastSpaceData";
		String serviceValue = "fcstValue";
		Weather resultWeather = callWeatherAPI(baseTime, service, serviceValue);
	}

	@Scheduled(cron=" 0 10 05 * * * ")//새벽 5시 10분에 5시데이터 한번 조회
	public void setMaxWeatherStep2() throws IOException, ParseException{
		String baseTime = "0500";
		String service = "ForecastSpaceData";
		String serviceValue = "fcstValue";
		Weather resultWeather = callWeatherAPI(baseTime, service, serviceValue);
	}

	@Scheduled(cron="0 10 08 * * *")//새벽 8시 10분에 8시데이터 한번 조회
	public void setMaxWeatherStep3() throws IOException, ParseException{
		String baseTime = "0800";
		String service = "ForecastSpaceData";
		String serviceValue = "fcstValue";
		Weather resultWeather = callWeatherAPI(baseTime, service, serviceValue);
	}

	@Scheduled(cron="0 10 11 * * *")//오전 11시 10분에 11시데이터 한번 조회
	public void setMaxWeatherStep4() throws IOException, ParseException{
		String baseTime = "1100";
		String service = "ForecastSpaceData";
		String serviceValue = "fcstValue";
		Weather resultWeather = callWeatherAPI(baseTime, service, serviceValue);
	}

	public Weather callWeatherAPI(String baseTime, String service, String serviceValue) throws IOException, ParseException {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String baseDate = dateFormat.format(today);
		String urlStr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/"+service+"?"
						+ "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" + baseTime
						+ "&nx="+ nx + "&ny=" + ny + "&_type=json";

		logger.error("[callWeatherAPIParams::::"+service+"::"+serviceValue+":"+baseTime+"]");
		System.out.println("[callWeatherAPIParams::::"+service+"::"+serviceValue+":"+baseTime+"]");
		System.out.println(urlStr);

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

		for(int i = 0 ; i < parse_item.size(); i++){
			weather = (JSONObject) parse_item.get(i);
			String fcst_Value = weather.get(serviceValue).toString();
			category = (String)weather.get("category");

			if (serviceValue.equals("obsrValue")) {
				if (category.contains("T1H")){
					nowWeather.setT1h(fcst_Value);
				} else if (category.contains("PTY")){
					nowWeather.setPty(fcst_Value);
				} else if (category.contains("SKY")){
					nowWeather.setSky(fcst_Value);
				}
			} else if (serviceValue.equals("fcstValue")) {
				if (category.contains("TMN")){
					todayTmn=fcst_Value;
				} else if (category.contains("TMX")){
					todayTmx=fcst_Value;
				}
			}
		}
		System.out.println("[callWeatherAPI::::"+nowWeather+"nowTime::::"+new Date()+"todayWeather::::"+todayTmn+"--"+todayTmx+"]");
		logger.error("[callWeatherAPI::::"+nowWeather+"nowTime::::"+new Date()+"todayWeather::::"+todayTmn+"--"+todayTmx+"]");
		return nowWeather;
	}

}
