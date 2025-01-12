package pl.mg.project;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pl.mg.project.entity.User;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("/login");
            return false;
        }

        User user = (User) session.getAttribute("loggedUser");
        if (!"admin".equals(user.getUsername())) {
            response.sendRedirect("/user-panel");
            return false;
        }

        return true;
    }
}
