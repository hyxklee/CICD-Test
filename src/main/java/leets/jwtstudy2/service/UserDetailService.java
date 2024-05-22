package leets.jwtstudy2.service;

import leets.jwtstudy2.domain.User;
import leets.jwtstudy2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override //메소드명은 오버라이딩이라 Username 이지만 사용자 ID를 통해 가져오도록 변경//매개변수 명을 username이 아닌 다른 이름으로 하면 스프링 시큐리티가 값을 받아오지 못함 -> 프론트에서 ID 입력부 필드명을 'username'으로 설정해줘야함
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findByUserid(userid);

        //가입되지 않은 회원이면 null
        if (user == null) {
            System.out.println("없는 회원입니다");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userid);
        }
        //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
        //user에 이미 구현해뒀으므로 AutneticationManager이 가져가서 검증 함
        return user;
    }
}
