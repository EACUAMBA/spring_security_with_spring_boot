package com.mafurrasoft.springsecurity.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Este é um filtro que somente será executado uma vez por request.
 */
public class JwtTokenVerify extends OncePerRequestFilter {
    private final JwtSecreteKey jwtSecreteKey;
    private final JwtConfig jwtConfig;

    public JwtTokenVerify(JwtSecreteKey jwtSecreteKey, JwtConfig jwtConfig) {
        this.jwtSecreteKey = jwtSecreteKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader= request.getHeader(this.jwtConfig.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(this.jwtConfig.getTokenPrefix())){
            filterChain.doFilter(request, response);
        }

            String token = authorizationHeader.replace(this.jwtConfig.getTokenPrefix(), "");
        try{


            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(this.jwtSecreteKey.getSecurityKeyForSigin())
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthoritiesSet = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthoritiesSet
            );
            Authentication authentication = usernamePasswordAuthenticationToken;

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }catch (JwtException jwtException){
            throw new IllegalStateException(String.format("We cannot validate this token: \"%s\";", token));
        }
    }
}
