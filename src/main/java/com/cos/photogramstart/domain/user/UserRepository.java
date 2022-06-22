package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository 상속하면 Ioc등록이 자동
public interface UserRepository extends JpaRepository<User, Integer> {
}
