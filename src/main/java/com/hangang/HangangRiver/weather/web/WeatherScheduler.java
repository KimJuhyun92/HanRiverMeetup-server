package com.hangang.HangangRiver.weather.web;




import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hangang.HangangRiver.weather.model.Weather;
import com.hangang.HangangRiver.weather.service.WeatherService;

@Component
public class WeatherScheduler {

	private static final Logger logger = LogManager.getLogger(WeatherScheduler.class);

	@Autowired
	WeatherService weatherService;


	//@Scheduled(cron="*/40 * * * * *")40초마다
	@Scheduled(cron="0 45 * * * *")//매시간 45분
	public void setWeatherInformation() throws IOException, ParseException{
		//한시간에 한번씩 네이버 크롤링 후 weather insert
		weatherService.createWeatherInformation(getWeatherInformation());
	}

	public Weather getWeatherInformation() throws IOException{
		Weather weather = new Weather();
		String URL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%98%A4%EB%8A%98+%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8&oquery=%ED%98%84%EC%9E%AC%EB%82%A0%EC%94%A8";
		Document doc = Jsoup.connect(URL).get();

		Elements info_data = doc.select("div.info_data");
		String todaytemp = info_data.select("span.todaytemp").text();

		weather.setT1h(todaytemp);

		Elements info_list = doc.select("ul.info_list");
		String cast_txt = info_list.select("p.cast_txt").text();

		if (cast_txt.substring(0,cast_txt.indexOf(",")).equals("맑음")){
			weather.setSky("1");
		}else if (cast_txt.substring(0,cast_txt.indexOf(",")).equals("구름 조금")){
			weather.setSky("2");
		}else if (cast_txt.substring(0,cast_txt.indexOf(",")).equals("흐림")){
			weather.setSky("3");
		}else if (cast_txt.substring(0,cast_txt.indexOf(",")).equals("비")){
			weather.setSky("4");
		}else {
			weather.setSky("2");
		}

		Elements min = info_list.select("span.min");
		String minNum = min.select("span.num").text();
		weather.setTmn(minNum);
		Elements max = info_list.select("span.max");
		String maxNum = max.select("span.num").text();
		weather.setTmx(maxNum);
		weather.setPty("0");
		return weather;
	}
}
