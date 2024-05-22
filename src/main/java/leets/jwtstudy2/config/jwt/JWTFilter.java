package leets.jwtstudy2.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.jwtstudy2.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        //헤더 검증
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("token is null");
            filterChain.doFilter(request, response);

            return;
        }
        System.out.println("authorization header is " + authorizationHeader);
        String token = authorizationHeader.split(" ")[1];

        //토큰 소멸 검증
        if (jwtUtil.isExpired(token)){
            System.out.println("token is expired");
            filterChain.doFilter(request, response);
            return;
        }
        String userId = jwtUtil.getUserId(token);

        User user = User.builder()
                .userid(userId)
                .build();
        Authentication authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        //토큰이 유효하다면 토큰에서 정보를 빼서 그 정보로 UsernamePasswordAuthenticationToken을 생성함
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
