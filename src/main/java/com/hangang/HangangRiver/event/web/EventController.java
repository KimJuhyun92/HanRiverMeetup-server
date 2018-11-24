package com.hangang.HangangRiver.event.web;

import com.hangang.HangangRiver.event.model.Event;
import com.hangang.HangangRiver.event.model.TourEventInfo;
import com.hangang.HangangRiver.event.service.EventService;
import com.hangang.HangangRiver.exceptions.InvalidEventException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    private ResponseEntity<Event> createEvent(HttpServletRequest request, @RequestBody Event event){
    	Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok().body(createdEvent);
    }

    @DeleteMapping("/event/{event_seq}")
    private ResponseEntity<Object> removeEvent(@PathVariable int event_seq) throws Exception{
    	eventService.removeEvent(event_seq);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/event/{event_seq}")
    private ResponseEntity<Object> modifyEvent(@PathVariable int event_seq, @RequestBody Event event)throws InvalidEventException{
    	eventService.modifyEvent(event_seq, event);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/events")
    private ResponseEntity<List<Event>> getEvents(HttpServletRequest request)throws Exception{
        return ResponseEntity.ok().body(eventService.selectEvent());
    }

    @GetMapping("/tour/events/recently")
    private ResponseEntity <List<TourEventInfo>> getTourInformation(HttpServletRequest request) throws IOException, ParseException {
        List<TourEventInfo> tourEventInfo = callTourAPI();
        return ResponseEntity.ok().body(tourEventInfo);
    }

    public List<TourEventInfo> callTourAPI() throws IOException, ParseException {
        List<TourEventInfo> tourEventInfoList = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar todayCalc = Calendar.getInstance();
        Calendar nextMonthCalc = Calendar.getInstance();
        nextMonthCalc.add(Calendar.MONTH, 1);

        String today = dateFormat.format(todayCalc.getTime());
        String nextMonth = dateFormat.format(nextMonthCalc.getTime());

        String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?"
                + "ServiceKey=wHkULcMomTpz3wVCYqSbrap5VrtdRQrZSTLd%2BcX4pwaZTjaVpDj9cF5fydh7%2BapE1tAwOBFUMJPrsTOBWTJUfA%3D%3D"
                + "&eventStartDate=" + today
                + "&eventEndDate=" + nextMonth
                + "&areaCode=1&sigunguCode=&cat1=A02&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=100";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpUriRequest getRequest = new HttpGet(urlStr);
        getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");

        try (CloseableHttpResponse httpResponse = httpClient.execute(getRequest)) {
            String content = EntityUtils.toString(httpResponse.getEntity());

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(statusCode == HttpURLConnection.HTTP_OK) {
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(content);
                JSONObject response = (JSONObject) obj.get("response");
                JSONObject body = (JSONObject) response.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray item = (JSONArray) items.get("item");

                for (int i = 0; i < item.size(); i++) {
                    TourEventInfo tourEventInfo = new TourEventInfo();
                    JSONObject data = (JSONObject) item.get(i);

                    String x = data.get("mapx").toString();
                    String y = data.get("mapy").toString();
                    tourEventInfo.setTitle((String)data.get("title"));
                    tourEventInfo.setAddr((String)data.get("addr1"));
                    tourEventInfo.setHompage((String)data.get("homepage"));
                    tourEventInfo.setImage((String)data.get("firstimage"));
                    tourEventInfo.setTel((String)data.get("tel"));
                    tourEventInfo.setMapx(Double.parseDouble(x));
                    tourEventInfo.setMapy(Double.parseDouble(y));

                    tourEventInfoList.add(tourEventInfo);
                }
            }
        } catch (IOException e) {
            return null;
        }
        catch (Exception e) {
            return null;
        }

        return tourEventInfoList;
    }
}
