package org.rangjin.springbootwebservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rangjin.springbootwebservice.domain.BaseTimeEntity;

import javax.persistence.*;

// Entity 클래스에는 절대 Setter 메소드를 만들지 않음
@Getter
@NoArgsConstructor
@Entity
// Entity 클래스를 Request/Response 클래스로 사용해서는 안 됨
public class Posts extends BaseTimeEntity {

    @Id
    // 스프링 부트 2.0 버전은 IDENTITY 옵션을 추가해야만 auto_increment 가능
    // MySQL 기준 BIGINT 타입이 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 비즈니스 로직은 도메인 계층에서 처리
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
