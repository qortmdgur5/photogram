package com.cos.photogramstart.domain.user;

//JPA - Java Persistence API ( 자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다. 즉, 데이터베이스에 따라 따로 설정해줄필요가없다.
    private int id;

    @Column(length = 100, unique = true)  //아이디 중복을 체크하자 DB에서 같은 아이디는 들어올 수 없다.
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사진
    private String role; //권한한

    //나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마. 만들면 리스트형식인데 어케만들어
    //User를 Select할 때 해당 User id로 등록된 image들을 다 가져와
    //LAZY = user를 Select할 때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages() 함수가 호출될 때 가져와
    //EAGER = User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와!!
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)   //LAZY 전략을 이용하는 이유는 유저를 가져올때마다 자동으로 모든 이미지들을 가져오면 서버에 무리야
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT되기 직전에 실행. 다른값들을 넣어주면 이 값은 자동으로 들어간다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
