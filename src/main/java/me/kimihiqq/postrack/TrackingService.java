package me.kimihiqq.postrack;

import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    public String parseHanjinHtml(String html) {

        Document doc = Jsoup.parse(html);

        // <tbody> 안의 <tr> 요소들 선택
        Elements trElements = doc.select(".board-list-table tbody tr");

        // 마지막 <tr> 요소 선택
        Element lastTrElement = trElements.last();

        // class="w-date" 값 추출
        String wDateValue = lastTrElement.select(".w-date").text();

        // class="w-time" 값 추출
        String wTimeValue = lastTrElement.select(".w-time").text();

        // class="l w-process" 값 추출
        String stateDescValue = lastTrElement.select(".stateDesc").text();

        // 이 정보를 Gson의 JsonObject로 구성
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", wDateValue);
        jsonObject.addProperty("time", wTimeValue);
        jsonObject.addProperty("status", stateDescValue);

        // JsonObject를 JSON 문자열로 변환하여 반환
        return jsonObject.toString();


    }


//    public String parseDaehanHtml(String html) {
//
//        Document doc = Jsoup.parse(html);
//
//        // <tbody> 안의 <tr> 요소들 선택
//        Elements trElements = doc.select(".board-list-table tbody tr");
//
//        // 마지막 <tr> 요소 선택
//        Element lastTrElement = trElements.last();
//
//        // class="w-date" 값 추출
//        String wDateValue = lastTrElement.select(".w-date").text();
//
//        // class="w-time" 값 추출
//        String wTimeValue = lastTrElement.select(".w-time").text();
//
//        // class="l w-process" 값 추출
//        String stateDescValue = lastTrElement.select(".l.w-process .stateDesc").text();
//
//        // 이 정보를 Gson의 JsonObject로 구성
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("date", wDateValue);
//        jsonObject.addProperty("time", wTimeValue);
//        jsonObject.addProperty("status", stateDescValue);
//
//        // JsonObject를 JSON 문자열로 변환하여 반환
//        return jsonObject.toString();
//
//    }
//
//    public String parseLotteHtml(String html) {
//        // 롯데택배의 HTML을 파싱하여 필요한 정보 추출
//        // ...
//        return resultJson;
//    }
}
