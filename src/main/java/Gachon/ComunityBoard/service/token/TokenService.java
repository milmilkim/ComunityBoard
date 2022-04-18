package Gachon.ComunityBoard.service.token;


import Gachon.ComunityBoard.domain.token.Token;
import Gachon.ComunityBoard.domain.user.User;
import Gachon.ComunityBoard.domain.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@PropertySource("classpath:secretKey.properties")
@Service
public class TokenService {


    @Value("${secretKey}")
    private String secretKey;
    private final UserRepository userRepository;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public Token generateToken(String uid,String role){

        //1시간(지금은 임시로 1시간으로 넉넉하게
        long tokenPeriod = 1000L * 60L * 60L;

        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;
        User user = userRepository.findByUserEmail(uid);
        // 토큰에 정보담아주기
        Claims claims = Jwts.claims().setSubject(uid);
        claims.put("userID",user.getId());
        claims.put("role",role);

        Date now = new Date();

        return new Token(
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + tokenPeriod))
                    .signWith(SignatureAlgorithm.HS256,secretKey)
                    .compact(),
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime()+ refreshPeriod))
                    .signWith(SignatureAlgorithm.HS256,secretKey)
                    .compact());
    }


    public boolean verifyToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e){
            return false;
        }
    }

    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


}
