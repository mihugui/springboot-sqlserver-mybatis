package com.ibms.app.web.login;

import com.ibms.app.config.Audience;
import com.ibms.app.utils.AjaxResult;
import com.ibms.app.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNmae LoginConroller
 * @Description TODO
 * @Author PWT
 * @Date 2018/8/14 16:53
 * @Version 1.0
 **/
@RestController
public class LoginConroller {

    @Autowired
    private Audience audience;

    @RequestMapping("login")
    @ResponseBody
    public AjaxResult Login(){

        String jwtToken = JwtHelper.createJWT(
                "admin",
                "1",
                "管理员",
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond()*1000,
                audience.getBase64Secret());

        System.out.println(jwtToken);

        return null;
    }
}
