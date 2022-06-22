package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service    //Ioc 뿐만 아니라 트랜잭션도 관리해준다.
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional  //write(insert, update, delete) 를 할떄 트랜잭션을 하자
    public User 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");  // 관리자는 ROLE_ADMIN
        User userEntity = userRepository.save(user);    //save는 클라이언트로부터 요청받은 정보를 담는거고 userEntity는 그 정보가 담긴 DB를 가져오는거니까 entity로 명시하자
        return userEntity;
    }
}
