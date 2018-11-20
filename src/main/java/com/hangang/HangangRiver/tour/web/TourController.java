package com.hangang.HangangRiver.tour.web;

import com.hangang.HangangRiver.tour.model.TourInformation;
import com.hangang.HangangRiver.weather.model.Weather;
import com.hangang.HangangRiver.weather.service.WeatherService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tour")
public class TourController {

    @GetMapping("/tourAPI")
    private ResponseEntity <List<TourInformation>> getTourInformation(HttpServletRequest request) throws IOException, ParseException{
    	List<TourInformation> tourInformation = callTourAPI();
        return ResponseEntity.ok().body(tourInformation);
    }


	public List<TourInformation> callTourAPI() throws IOException, ParseException {
		List<TourInformation> tourInformationList = new ArrayList<TourInformation>();

		String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "ServiceKey=wHkULcMomTpz3wVCYqSbrap5VrtdRQrZSTLd%2BcX4pwaZTjaVpDj9cF5fydh7%2BapE1tAwOBFUMJPrsTOBWTJUfA%3D%3D"
				+ "&contentTypeId=12&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&contentId=822861&imageYN=Y&_type=json";

		  URL url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(sb.toString());

			JSONObject parse_response = (JSONObject) obj.get("response");
			JSONObject parse_body = (JSONObject) parse_response.get("body");
			JSONObject parse_items = (JSONObject) parse_body.get("items");
			JSONArray parse_item = (JSONArray) parse_items.get("item");

			JSONObject imgurl;
			for (int i = 0; i < parse_item.size(); i++) {
				TourInformation tourInformation = new TourInformation();
				imgurl = (JSONObject) parse_item.get(i);
				String orignurl = (String) imgurl.get("originimgurl");
				String smallurl = (String) imgurl.get("smallimageurl");
				tourInformation.setOriginimgurl(orignurl);
				tourInformation.setSmallimageurl(smallurl);
				tourInformationList.add(tourInformation);
			}
		return tourInformationList;
	}
}
