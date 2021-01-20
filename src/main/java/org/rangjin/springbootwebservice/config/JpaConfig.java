package org.rangjin.springbootwebservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
// JPA Auditing 어노테이션 활성화
// @WebMvcTest(PostsApiControllerTest) 가 @SpringBootApplication 를 스캔하면서 @EnableJpaAuditing 도 같이 스캔함
// @EnableJpaAuditing 은 최소 하나의 @Entity 클래스가 필요한데 @WebMvcTest 는 @Entity 를 스캔하지 않음
// 따라서 오류를 일으키므로 @SpringBootApplication 과 분리하였음
@EnableJpaAuditing
public class JpaConfig {
}
