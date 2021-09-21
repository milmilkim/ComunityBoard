package Gachon.ComunityBoard.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    private String name;


    private String Email;


    private String picture;

    @Column(length = 500)
    private String SelfIntroduction;

//    @Column
//    private List<User> follower;

    private int followerCnt;

}
