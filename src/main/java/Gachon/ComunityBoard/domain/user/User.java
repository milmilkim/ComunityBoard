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

    // 이름
    @Column(nullable = false)
    private String name;
    // 닉네임
    @Column(nullable = false)
    private String nickname;

    // 이메일
    private String email;

    // 프로필 사진
    private String picture;

    //자기소개글
    @Column(length = 200)
    private String SelfIntroduction;

    // 권한설정
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email,String picture, String selfIntroduction,Role role){
        this.name = name;
        this.nickname = "익명"; //처음 닉네임은 익명
        this.email = email;
        this.picture = picture;
        this.SelfIntroduction = selfIntroduction;
        this.role = role;
    }
    //여기는 구글 로그인시 구글계정정보에따라 바뀌는거 업데이트되는지
    public User update(String name,String picture){
        this.name= name;
        this.picture = picture;

        return this;
    }

    // 여기는 나중에 유저정보 업데이트하는거 (사진은 추후 수정)
    public void updateSelfIntroduction(String nickname,String selfIntroduction){
        this.nickname = nickname;
        this.SelfIntroduction = selfIntroduction;
    }


    public String getRoleKey(){
        return this.role.getKey();
    }







//    @Column
//    private List<User> follower;

//    private int followerCnt;

}
