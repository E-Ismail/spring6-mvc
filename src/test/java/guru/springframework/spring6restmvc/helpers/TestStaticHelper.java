package guru.springframework.spring6restmvc.helpers;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 23/11/2025 - 14:00
 */

import org.jetbrains.annotations.NotNull;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

public class TestStaticHelper {

    public static @NotNull RequestPostProcessor getHttpBasic() {
        return httpBasic("user1", "password");
    }
}
