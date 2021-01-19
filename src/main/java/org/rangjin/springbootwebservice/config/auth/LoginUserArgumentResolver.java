package org.rangjin.springbootwebservice.config.auth;

import lombok.RequiredArgsConstructor;
import org.rangjin.springbootwebservice.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
// HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer 를 통해 추가해야 함
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    // 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        // 이 경우 파라미터에 @LoginUser 어노테이션이 붙어 있고 클래스 타입이 SessionUser 라면 true 를 반환함
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    // 파라미터에 전달할 객체 생성
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 이 경우 세션에서 객체를 가져옴
        return httpSession.getAttribute("user");
    }

}
