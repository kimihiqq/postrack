package me.kimihiqq.postrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class TrackingController {

    private final WebClient webClient = WebClient.create();

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/track")
    public String trackPackage(@RequestParam String company, @RequestParam String trackingNumber) {
        String url = createUrl(company, trackingNumber);

        // WebClient를 사용하여 URL 호출
        String htmlResponse = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String result;
        switch (company) {
            case "한진택배":
                result = trackingService.parseHanjinHtml(htmlResponse,company, trackingNumber);
                System.out.println("한진택배 호출");
                break;
            case "대한통운":
                result = trackingService.parseDaehanHtml(htmlResponse,company, trackingNumber);
                System.out.println("대한통운 호출");

                break;
            case "롯데택배":
                result = trackingService.parseLotteHtml(htmlResponse,company, trackingNumber);
                System.out.println("롯데택배 호출");

                break;
            default:
                throw new IllegalArgumentException("지원되지 않는 택배 회사입니다.");
        }
        return result;
    }

    public String createUrl(String company, String trackingNumber) {
        String url = "";

        switch (company) {
            case "한진택배":
                url = "https://www.hanjin.com/kor/CMS/DeliveryMgr/WaybillResult.do?mCode=MN038&wblnumText2=" + trackingNumber + "&schLang=KR";
                break;
            case "대한통운":
                url = "https://www.doortodoor.co.kr/parcel/doortodoor.do?fsp_action=PARC_ACT_002&fsp_cmd=retrieveInvNoACT&invc_no=" + trackingNumber;
                break;
            case "롯데택배":
                url = "https://www.lotteglogis.com/home/reservation/tracking/linkView?InvNo=" + trackingNumber;
                break;
            default:
                throw new IllegalArgumentException("지원되지 않는 택배 회사입니다.");
        }

        return url;
    }
}