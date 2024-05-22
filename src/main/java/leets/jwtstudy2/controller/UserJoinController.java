package leets.jwtstudy2.controller;

import leets.jwtstudy2.dto.AddUserRequest;
import leets.jwtstudy2.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class UserJoinController {
    private final UserJoinService userJoinService;

    @PostMapping("/signup")
    public String signup(AddUserRequest dto) {
        userJoinService.join(dto);

        return "ok";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "signup";
    }
}
