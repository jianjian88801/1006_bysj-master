package cn.com.scitc.graduationproject.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断session中是否存在用户信息
        HttpSession session = request.getSession();
        // 获取session中的用户信息
        String username = (String) session.getAttribute("Tlis");
        if (username != null) {
            return true; // 放行
        }
        // 获取请求路径
        String path = request.getRequestURI();
        if (path.contains("/login")) {
            return true;
        }
        // 重定向到登录页面
//        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        response.sendRedirect("student/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
