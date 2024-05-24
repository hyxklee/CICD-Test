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


        String accessToken = jwtUtil.createAccessToken(userid);
        return accessToken;
    }
}
