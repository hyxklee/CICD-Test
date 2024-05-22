package leets.jwtstudy2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk_id")
    private Long id; //DB의 기본키

    private String userid; //사용자 ID
    private String password; //사용자 비번
    private String name; //사용자 이름
    private String part; //파트


//    //== jwt 토큰 추가 ==//
//    @Column(length = 1000)
//    private String refreshToken;
//
//    public void updateRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
//
//    public void destroyRefreshToken() {
//        this.refreshToken = null;
//    }

    @Builder
    public User(String userid, String password, String name, String part) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.part = part;
    }

    @Override//권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override //오버라이딩이라 Username이지만 사용자 ID를 반환하도록 변경
    public String getUsername() {
        return userid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
