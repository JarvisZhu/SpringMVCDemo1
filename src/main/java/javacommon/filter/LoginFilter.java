package javacommon.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/9/10.
 */
public class LoginFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("loginFilter got requestURI = " + requestURI);

        boolean empty = false;

        Object sessionData = request.getSession().getAttribute("LoginUser");
        if (sessionData == null) {
            empty = true;
            log.info("从session中拿到数据为空");
            request.getSession().setAttribute("LoginUser", "朱德方");
        } else {
            log.info("从session中拿到数据为{}", sessionData);
        }

//        if (empty) {
//            java.io.PrintWriter out = response.getWriter();
//            out.println("<html>");
//            out.println("<script>");
//            out.println("window.location.href='http://www.baidu.com'");
//            out.println("</script>");
//            out.println("</html>");
//
//            return;
//        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
