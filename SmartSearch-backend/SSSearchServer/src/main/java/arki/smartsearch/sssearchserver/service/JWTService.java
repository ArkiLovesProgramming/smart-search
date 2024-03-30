package arki.smartsearch.sssearchserver.service;

import arki.smartsearch.common.pojo.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Base64;
import java.util.Date;

public class JWTService {
    private static String key = "smartsearch_key";
    public static String getJWT(User user){
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60*60*1000;
        Date expired = new Date(currentTime);
        String token = JWT.create().withAudience(String.valueOf(user.getUsername())).withIssuedAt(start).withExpiresAt(expired)
                .withClaim("history_id", user.getHistory_id())
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC256(key));
        return token;
    }

    public static User verifyJWT(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e){
            System.out.println("验证失败！token=" + token);
            return null;
        }
        User user = new User(jwt.getClaim("id").asString(),
                jwt.getClaim("username").asString(),
                jwt.getClaim("password").asString(),
                jwt.getClaim("history_id").asString());
        return user;
    }

    public static void main(String[] args) {
        User user = new User("1111", "arki", "123", "2222");
        String token = JWTService.getJWT(user);
        System.out.println(token);
    }
}
