package me.kimihiqq.postrack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrackingViewController {

    @GetMapping("/")
    public String index() {
        return "index"; // Thymeleaf 템플릿 이름
    }
}

