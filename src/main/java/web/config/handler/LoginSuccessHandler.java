package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println("Мой весь юзер который прилетел для АУТЕНИФИКАЦИИ: " + authentication.getPrincipal().toString());
        System.out.println("Логин принципиала: " + authentication.getPrincipal().getClass().getName());
        if (roles.contains("ROLE_ADMIN"))
        {
            httpServletResponse.sendRedirect("/admin/admin_page");
        } else if (roles.contains("ROLE_USER"))
        {
            httpServletResponse.sendRedirect("/user/user_page");
        }
    }
}