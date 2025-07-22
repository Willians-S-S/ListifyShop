package com.willians.ListifyShop.security.authentication;

import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.security.userdetails.UserAuthenticated;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder){
        this.encoder = encoder;
    }

    public String genereteToken(Authentication authentication){
        Instant now = Instant.now();
        long expire = 3600L;

        String scopes = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String userId = null;
        if (authentication.getPrincipal() instanceof UserAuthenticated) {
            UserAuthenticated user = (UserAuthenticated) authentication.getPrincipal();
            userId = user.getId().toString();
        }

//        var claims = JwtClaimsSet.builder()
//                .issuer("spring-security-jwt")
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(expire))
//                .subject(authentication.getName())
//                .claim("scope", scopes)
//                .build();

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expire))
                .subject(authentication.getName())
                .claim("scope", scopes); // Adicione o escopo aqui também

        // Adicione a claim "userId" ao builder ANTES de chamar .build()
        if (userId != null) {
            claimsBuilder.claim("id", userId);
        }

        var claims = claimsBuilder.build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
