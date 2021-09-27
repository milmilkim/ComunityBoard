package Gachon.ComunityBoard.domain.user;

import Gachon.ComunityBoard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String Email;

    private String picture;

    @Column(length = 200)
    private String SelfIntroduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email,String picture, String selfIntroduction,Role role){
        this.name = name;
        this.Email = email;
        this.picture = picture;
        this.SelfIntroduction = selfIntroduction;
        this.role = role;
    }

    public User update(String name,String picture, String selfIntroduction){
        this.name= name;
        this.picture = picture;
        this.SelfIntroduction = selfIntroduction;

        return this;
    }


    public String getRoleKey(){
        return this.role.getKey();
    }







//    @Column
//    private List<User> follower;

//    private int followerCnt;

}
