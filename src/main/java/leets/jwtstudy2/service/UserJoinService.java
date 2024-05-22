package leets.jwtstudy2.service;

import leets.jwtstudy2.domain.User;
import leets.jwtstudy2.dto.AddUserRequest;
import leets.jwtstudy2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserJoinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(AddUserRequest dto) {

//        Boolean isExist = userRepository.existsByUsername(dto.getUserId());

        //기존에 존재하는 이름이면 회원가입 불가. 중복 확인 로직을 위해 주석화
//        if (isExist) {
//            System.out.println("[ERROR] 기존에 존재하는 회원");
//            return;
//        }

        userRepository.save(User.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .part(dto.getPart())
                .userid(dto.getUserid())
                .build());
    }
}
