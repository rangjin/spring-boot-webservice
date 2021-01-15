package org.rangjin.springbootwebservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// JUnit 에 내장된 실행자 외에 다른 실행자 실행
// 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)
// Web 에 집중하는 테스트 어노테이션(@Controller, @ControllerAdvice 만 테스트 가능)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    // 웹 API 테스트에 사용
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

}