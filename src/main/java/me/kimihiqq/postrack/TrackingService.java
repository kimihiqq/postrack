package me.kimihiqq.postrack;

import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    public String parseHanjinHtml(String html, String company, String trackingNumber ) {

        Document doc = Jsoup.parse(html);

        // <tbody> 안의 <tr> 요소들 선택
        Elements trElements = doc.select(".board-list-table tbody tr");

        // 마지막 <tr> 요소 선택
        Element lastTrElement = trElements.last();

        // class="w-date" 값 추출
        String date = lastTrElement.select(".w-date").text();

//        // class="w-time" 값 추출
//        String wTimeValue = lastTrElement.select(".w-time").text();

        // class="l w-process" 값 추출
        String state = lastTrElement.select(".stateDesc").text();

        // 이 정보를 Gson의 JsonObject로 구성
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", date);
//        jsonObject.addProperty("time", wTimeValue);
        jsonObject.addProperty("status", state);
        jsonObject.addProperty("company", company);
        jsonObject.addProperty("trackingNumber", trackingNumber);

        // JsonObject를 JSON 문자열로 변환하여 반환
        return jsonObject.toString();
    }

    public String parseDaehanHtml(String html, String company, String trackingNumber) {
        Document doc = Jsoup.parse(html);

        // 지정한 경로의 마지막 <tr> 태그 선택
        Element lastTrElement = doc.select("#tabContents .tap_area.tap01.mb30 .first.focus .intap div:nth-child(2) .board_area.mb50.ml23 table tbody tr").last();

        // 마지막 <tr> 태그의 두 번째 <td> 값 추출 (date)
        String date = lastTrElement.select("td").get(1).text().substring(0,10);

        // 마지막 <tr> 태그의 세 번째 <td> 값 추출 (status)
        String state = lastTrElement.select("td").get(2).text();

        String[] parts = state.split("\\."); // 온점을 구분자로 사용하여 분리

        if (parts.length > 0) {
            state = parts[0].trim();
            state += ".";
        } else {
            state = "No parts found.";
        }

        // 이 정보를 Gson의 JsonObject로 구성
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", date);
        jsonObject.addProperty("status", state);
        jsonObject.addProperty("company", company);
        jsonObject.addProperty("trackingNumber", trackingNumber);

        // JsonObject를 JSON 문자열로 변환하여 반환
        return jsonObject.toString();

    }



    public String parseLotteHtml(String html, String company, String trackingNumber) {
        Document doc = Jsoup.parse(html);

        // id="contents" 아래 class="inner" 아래 class="contArea" 아래, 2번째 <table> 태그 아래, <tbody> 태그 아래 첫번째 <tr> 태그 선택
        Element trElements = doc.select("#contents .inner .contArea .tblH tbody tr").get(1);

        // 첫 번째 <tr> 태그 선택
//        Element firstTrElement = trElements.first();

        // 첫 번째 <tr> 태그의 2번째 <td> 값 추출 (date)
        String date = trElements.select("td").get(1).text().substring(0,10);

        // 첫 번째 <tr> 태그의 class="tl" 값 추출 (status)
        String state = trElements.select(".tl").text();

        // 이 정보를 Gson의 JsonObject로 구성
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", date);
        jsonObject.addProperty("status", state);
        jsonObject.addProperty("company", company);
        jsonObject.addProperty("trackingNumber", trackingNumber);

        // JsonObject를 JSON 문자열로 변환하여 반환
        return jsonObject.toString();
    }

}
