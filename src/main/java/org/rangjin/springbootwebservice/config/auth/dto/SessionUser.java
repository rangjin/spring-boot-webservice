package org.rangjin.springbootwebservice.config.auth.dto;

import lombok.Getter;
import org.rangjin.springbootwebservice.domain.user.User;

import java.io.Serializable;

@Getter
// 세션에 사용자 정보를 저장하기 위한 클래스
// 세션에 저장하기 위해서는 직렬화(Serialize)가 필요
// 엔티티는 언제 다른 엔티티와 관계가 형성될지 모름
// 따라서 직렬화 기능을 가진 세션 dto 하나를 만드는 것 운영 및 유지보수에 더 나음
public class SessionUser implements Serializable {

    private String name;

    private String email;

    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
