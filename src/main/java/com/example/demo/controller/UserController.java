package com.example.demo.controller;


import com.example.demo.common.Constant;
import com.example.demo.entity.User;
import com.example.demo.sesrvice.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author WangYuanrong
 * @since 2021-06-18
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户接口")
public class UserController extends BaseController {

//    private static final SecretKey KEY = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes(StandardCharsets.UTF_8));


//    @Value("${wx.mini-program.appid}")
//    private String appid;
//
//    @Value("${wx.mini-program.secret}")
//    private String secret;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "授权登录")
    @GetMapping("/login")
    public User login(String code, HttpServletRequest request, HttpServletResponse response) {
        log.info("code:{}", code);
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
//        String result = HttpUtils.get(url);
//        log.info("微信返回结果：{}", result);
//        if (StringUtils.isBlank(result)) {
//            throw new ApiException("微信授权错误");
//        }
//        JSONObject jsonObject = JSON.parseObject(result);
//        Object openid = jsonObject.get("openid");
//        if (openid == null) {
//            throw new ApiException("无法获取openid");
//        }
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
//                .eq(User::getOpenId, openid.toString());
//        User user = userService.getOne(wrapper);
//        if (user == null) {
//            user = new User();
//            user.setOpenId(openid.toString());
//            userService.save(user);
//        }

        User user = userService.getById(code);
        log.info("用户登录，{}", user);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH, 3);
//        String jws = Jwts.builder().setSubject(user.getId().toString()).setExpiration(calendar.getTime()).signWith(KEY).compact();
//        response.setHeader("Authorization", jws);
        HttpSession session = request.getSession();
        session.setAttribute(Constant.LOGIN_USER, user);
        return user;
    }


}

