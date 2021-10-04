package Gachon.ComunityBoard.domain.user;

import Gachon.ComunityBoard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.security.MessageDigest;

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

    //지역
    private String livingPlace;

    // 선호 운동 1 2 3
    private String preference1;
    private String preference2;
    private String preference3;


    //자기소개글
    @Column(length = 200)
    private String SelfIntroduction;

    // 권한설정
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email,String picture,String livingPlace
            ,String preference1, String preference2, String preference3,String selfIntroduction,Role role){
        this.name = name;
        // 초기 닉네임은 랜덤글자 10글자
        this.nickname = RandomStringUtils.randomAlphanumeric(10);
        this.email = email;
        this.picture = picture;
        this.livingPlace = livingPlace;
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
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
    public void updateInfo(String nickname,String livingPlace
            ,String preference1, String preference2, String preference3,String selfIntroduction){
        this.nickname = nickname;
        this.livingPlace = livingPlace;
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
        this.SelfIntroduction = selfIntroduction;
    }


    public String getRoleKey(){
        return this.role.getKey();
    }









//    @Column
//    private List<User> follower;

//    private int followerCnt;

}
