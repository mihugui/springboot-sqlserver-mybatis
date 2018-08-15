package com.ibms.app.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibms.app.config.Audience;
import com.ibms.app.utils.AjaxResult;
import com.ibms.app.utils.Globals;
import com.ibms.app.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassNmae JwtFilter
 * @Description TODO
 * @Author PWT
 * @Date 2018/8/14 17:13
 * @Version 1.0
 **/
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AjaxResult msg = new AjaxResult();

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String auth = httpRequest.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 7))
        {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0)
            {

                auth = auth.substring(7, auth.length());
                if (JwtHelper.parseJWT(auth, audience.getBase64Secret()) != null)
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();

        msg.setRetcode(Globals.HTTP_TOKEN_INVALID);
        msg.setRetmsg(Globals.LOGIN_TOKEN_MISS);
        msg.setData(null);
        httpResponse.getWriter().write(mapper.writeValueAsString(msg));
        return;

    }
}
