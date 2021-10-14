package Gachon.ComunityBoard.config.auth;

import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUri = determineTargetUrl(request, response, authentication);

        HttpSession session = request.getSession();
        String sessionId = session.getId();

//        Cookie cookie= new Cookie("SessionId",sessionId);
//        cookie.setPath("https://healthtohether.cafe24.com");
//        cookie.setMaxAge(60*60*24);
//        response.addCookie(cookie);


        getRedirectStrategy().sendRedirect(request, response, targetUri);
    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //서버 배포용
//        String targetUri = "https://healthtohether.cafe24.com";
        // 프론트 로컬용
        String targetUri = "http://localhost:3000";
        HttpSession session = request.getSession();
        String sessionId = Base64.getEncoder().encodeToString(session.getId().getBytes());

        return UriComponentsBuilder.fromUriString(targetUri).queryParam("session",sessionId).build().toUriString();
    }

}



