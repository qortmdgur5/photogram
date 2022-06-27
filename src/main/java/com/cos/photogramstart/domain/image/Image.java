package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다. 즉, 데이터베이스에 따라 따로 설정해줄필요가없다.
    private int id;
    private String caption; //사진 설명
    private String postImageUrl;    //사진을 전송받아서 그 사진을 서버에 특정폴더에 저장 -DB에 그 저장된 경로를 insert

    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    //이미지 좋아요

    //댓글

    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT되기 직전에 실행. 다른값들을 넣어주면 이 값은 자동으로 들어간다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
