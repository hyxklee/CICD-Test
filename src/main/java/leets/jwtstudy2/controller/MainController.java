package leets.jwtstudy2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String index() {
        return "main controller";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin controller";
    }
}