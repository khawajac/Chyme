package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private static final String SECRET_KEY = "1a9745d3ccc4cab6005344e8ad43dcf5efd75d07f3e799290682f6deeb6e71adcf00f78427faa3f2ae2be2f14f4cb76bbd5e080059d57bdc49b619cbeca755cc346943da13e811414435570c91a9c97637c4348c628fc607fe5d072938107d1cf7128028fe1649ac00f92766a0de71e9f69512b10fcbe7f876fc701d49e9336bc8a1a1b2ce3da7d7ad5e56f34f90c8da58b7d18954a841fadf1da81df00cb958770d6d8ba0c7b40eff9d2836eac829bef3c3c8f1b8e5ee9169a82f29d15a23cd7fabf64643079987c908f2bc18752514faaff3a21e567078e66ab043242c6d03c46e290d5204a7dbff05152053bc4a08a1a999008d0649bfd2dc9ed5fa74afba6c1f9a111b2280b8c61c6dab5714be6b\n";

    public String extractUsername(String token) {
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
