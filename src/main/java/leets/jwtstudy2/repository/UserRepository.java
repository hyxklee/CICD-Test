package leets.jwtstudy2.repository;

import leets.jwtstudy2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserid(String userId); //사용자 ID로 중복 확인을 위한 메소드

    User findByUserid(String userid); //유니크한 사용자 ID로 찾기
}
