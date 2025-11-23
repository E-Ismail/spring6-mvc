package guru.springframework.spring6restmvc.helpers;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 23/11/2025 - 14:00
 */

import org.jetbrains.annotations.NotNull;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.Instant;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

public class TestStaticHelper {

    private static final String CLIENT_ID  = "messaging-client";

    public static @NotNull RequestPostProcessor getHttpBasic() {
        return httpBasic("user1", "password");
    }

    public static SecurityMockMvcRequestPostProcessors.@NotNull JwtRequestPostProcessor getJwtRequestPostProcessor() {
        return jwt().jwt(jwt -> {
            jwt.claims(claim -> {
                        claim.put("scope", List.of("message-read", "message-write"));
                    })
                    .subject(CLIENT_ID)
                    .notBefore(Instant.now().minusSeconds(5L));
        });
    }
}
