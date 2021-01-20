package org.rangjin.springbootwebservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rangjin.springbootwebservice.config.auth.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// JUnit 에 내장된 실행자 외에 다른 실행자 실행
// 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)
// Web 에 집중하는 테스트 어노테이션(@Controller, @ControllerAdvice 만 테스트 가능)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                // @WebMvcTest 는 @Component, @Service 등은 스캔하지 못함
                // SecurityConfig 는 읽었지만 SecurityConfig 에 필요한 CustomOAuth2UserService 를 읽지 못함
                // 따라서 스캔 대상에서 SecurityConfig 를 삭제
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    // 웹 API 테스트에 사용
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(1000)))
                .andExpect(status().isOk())
                // jsonPath: JSON 응답값을 필드별($를 기준으로 명시)로 검증하는 메소드
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}