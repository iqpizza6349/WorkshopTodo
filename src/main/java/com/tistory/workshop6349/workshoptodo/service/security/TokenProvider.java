package com.tistory.workshop6349.workshoptodo.service.security;

import com.tistory.workshop6349.workshoptodo.advice.exception.AuthenticationEntrypointException;
import com.tistory.workshop6349.workshoptodo.domain.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    private final String ROLES = "roles";

    private final CustomUserDetailService customUserDetailService;

    @PostConstruct
    protected void init() {
        // 암호화
        SECRET_KEY = Base64UrlCodec.BASE64URL.encode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDto createTokenDto(Long autoId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(autoId));
        claims.put(ROLES, roles);

        Date now = new Date();

        // 1시간
        long accessTokenValidMilliSecond = 60 * 60 * 1000L;
        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date(now.getTime() + accessTokenValidMilliSecond))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // 2일
        long refreshTokenValidMilliSecond = 2 * 24 * 60 * 60 * 1000L;
        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMilliSecond))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return TokenDto.builder()
                .grantType("Bearer") // OAUTH 토큰 권한
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenValidMilliSecond)
                .build();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token); // Jwt 에서 claims 추출

        if (claims.get(ROLES) == null) {
            // 권한 정보 없음
            throw new AuthenticationEntrypointException("권한 정보가 없습니다!");
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims parseClaims(String token) {
        // Jwt 토큰 복호화
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException exception) {
            return exception.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Bearer");
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 Jwt 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원하지않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("잘못된 토큰입니다");
        }
        return false;
    }

}
