package cn.pbj.demo2020.myblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @pClassName: VerificationCodeFilter
 * @author: pengbingjiang
 * @create: 2020/11/26 21:30
 * @description: TODO
 */
@Component
public class VerificationCodeFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("POST".equals(req.getMethod()) && "/login".equals("verify_code")) {
            //登录请求
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
                //验证码不正确
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString("验证码填写错误"));
                out.flush();
                out.close();
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req,resp);
        }
    }
}
