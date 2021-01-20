package org.rangjin.springbootwebservice.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.rangjin.springbootwebservice.domain.user.Role;
import org.rangjin.springbootwebservice.domain.user.User;

import java.util.Map;

@Getter
@ToString
public class OAuthAttributes {

    private Map<String, Object> attributes;

    private String nameAttributeKey;

    private String name;

    private String email;

    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("naver")) {
            // registrationId = "naver", userNameAttributeName = "response"
            return ofNaver("id", attributes);
        }

        // registrationId = "google", userNameAttributeName = "sub"
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}

//google
//OAuthAttributes(
//    attributes={
//        sub=105180831447934252206,
//        name=유상진,
//        given_name=상진,
//        family_name=유,
//        picture=https://lh5.googleusercontent.com/-9Q0scHZKMpY/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuckShJEVztmiyqd_dTIoK2y5TsGBDQ/s96-c/photo.jpg,
//        email=heathaze2037@gmail.com,
//        email_verified=true,
//        locale=ko
//    },
//    nameAttributeKey=sub,
//    name=유상진,
//    email=heathaze2037@gmail.com,
//    picture=https://lh5.googleusercontent.com/-9Q0scHZKMpY/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuckShJEVztmiyqd_dTIoK2y5TsGBDQ/s96-c/photo.jpg
//)

//naver
//OAuthAttributes(
//    attributes={
//        id=178275368,
//        profile_image=https://ssl.pstatic.net/static/pwe/address/img_profile.png,
//        email=heathaze2037@naver.com,
//        name=유상진
//    },
//    nameAttributeKey=id,
//    name=유상진,
//    email=heathaze2037@naver.com,
//    picture=https://ssl.pstatic.net/static/pwe/address/img_profile.png
//)