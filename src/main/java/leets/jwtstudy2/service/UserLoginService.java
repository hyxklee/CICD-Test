package leets.jwtstudy2.service;

import leets.jwtstudy2.config.jwt.JWTUtil;
import leets.jwtstudy2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;
    public String login(String userid, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userid, password);
        authenticationManager.authenticate(authenticationToken);

        //여기서 loadByUsername 이 실행
//        try {
//            authenticationManager.authenticate(authenticationToken);
//        } catch (AuthenticationException e) {
//            String errorMessage;
//            if (e instanceof UsernameNotFoundException) {
//                errorMessage = "유저없음";
//                System.out.println("errorMessage = " + errorMessage);
//            } else if (e instanceof BadCredentialsException) {
//                errorMessage = "비밀번호틀림";
//                System.out.println("errorMessage = " + errorMessage);
//            } else {
//                errorMessage = "로그인 정보를 다시 입력해주세요.";
//                System.out.println("errorMessage = " + errorMessage);
//            }
//        }
        /*
        인증이 완료되면 다음 코드 실행?
         */

        String accessToken = jwtUtil.createAccessToken(userid);
        return accessToken;
    }
}
