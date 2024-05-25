package leets.jwtstudy2.controller;

import leets.jwtstudy2.dto.UserLoinDTO;
import leets.jwtstudy2.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserLoginService userLoginService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoinDTO dto) {
        try {
            String token = userLoginService.login(dto.getUserId(), dto.getPassword());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).build();
        } catch (AuthenticationException e) {
            String errorMessage;
            if (e instanceof UsernameNotFoundException) {
                errorMessage = "존재하지 않는 유저입니다.";
                log.error(errorMessage);
            } else if (e instanceof BadCredentialsException) {
                errorMessage = "비밀번호가 틀렸습니다.";
                log.error(errorMessage);
            } else {
                errorMessage = "로그인 정보를 다시 입력해주세요.";
                log.error(errorMessage);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
