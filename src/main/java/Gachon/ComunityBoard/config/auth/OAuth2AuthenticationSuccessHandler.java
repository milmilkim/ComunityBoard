package Gachon.ComunityBoard.config.auth;

import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.config.auth.dto.UserDto;
import Gachon.ComunityBoard.domain.token.Token;
import Gachon.ComunityBoard.service.token.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String targetUri = determineTargetUrl(request, response, authentication);


        //서버 배포용
        String targetUriFront = "https://healthtohether.cafe24.com";
        // 프론트 로컬용
        //String targetUriFront = "http://localhost:3000";
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        Token token = tokenService.generateToken(userDto.getEmail(),"USER");
        log.info("{}", token);

        response =writeTokenResponse(response,token);

        String targetUri = UriComponentsBuilder.fromUriString(targetUriFront).queryParam("token", token).build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUri);
    }



    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //서버 배포용
        String targetUri = "https://healthtohether.cafe24.com";
        // 프론트 로컬용
        //String targetUri = "http://localhost:3000";
        //HttpSession session = request.getSession();
        //String sessionId = Base64.getEncoder().encodeToString(session.getId().getBytes());

        return UriComponentsBuilder.fromUriString(targetUri).build().toUriString();
    }

//    private void writeTokenResponse(HttpServletResponse response, Token token)
//            throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        response.addHeader("Auth", token.getToken());
//        response.addHeader("Refresh", token.getRefreshToken());
//        response.setContentType("application/json;charset=UTF-8");
//
//        var writer = response.getWriter();
//        writer.println(objectMapper.writeValueAsString(token));
//        writer.flush();
//    }

    private HttpServletResponse writeTokenResponse(HttpServletResponse response, Token token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", token.getToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

//        var writer = response.getWriter();
//        writer.println(objectMapper.writeValueAsString(token));
//        writer.flush();
        return response;
    }

}



