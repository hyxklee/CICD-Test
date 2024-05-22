package leets.jwtstudy2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.jwtstudy2.config.jwt.JWTUtil;
import leets.jwtstudy2.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("로그인 성공");
        User user = (User) authentication.getPrincipal();

        String userId = user.getUsername();
        String accessToken = jwtUtil.createAccessToken(userId);
        //리프레쉬토큰 생성

        response.addHeader("Authorization", "Bearer " + accessToken);
    }
}
