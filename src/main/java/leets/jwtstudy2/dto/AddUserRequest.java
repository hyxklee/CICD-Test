package leets.jwtstudy2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String userid;
    private String password;
    private String name;
    private String part;

}
