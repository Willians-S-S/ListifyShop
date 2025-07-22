package com.willians.ListifyShop.security.authentication;

import com.willians.ListifyShop.security.userdetails.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder){
        this.encoder = encoder;
    }

    public String genereteToken(Authentication authentication){
        try {
            Instant now = Instant.now();
            long expire = 3600L;

            String scopes = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                    .issuer("listify-shop")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expire))
                    .subject(authentication.getName())
                    .claim("scope", scopes);

            claimsBuilder.claim("id", ((UserDetailsImpl) authentication.getPrincipal()).getId());

            JwtClaimsSet claims = claimsBuilder.build();

            return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível autenticar o usuário.");
        }
    }

}
