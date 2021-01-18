package org.rangjin.springbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Entity 와 Entity Repository 는 함께 위치해야 함
// 따라서 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
