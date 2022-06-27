package com.cos.photogramstart.domain.subscribe;

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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"}
                )
        }
)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다. 즉, 데이터베이스에 따라 따로 설정해줄필요가없다.
    private int id;

    @JoinColumn(name="fromUserId")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser;


    private LocalDateTime createDate;

    @PrePersist // DB에 INSERT되기 직전에 실행. 다른값들을 넣어주면 이 값은 자동으로 들어간다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
