package com.example.demo.aop;

import com.example.demo.annotation.LoginUser;
import com.example.demo.common.ApiException;
import com.example.demo.dto.LoginUserDTO;
import com.example.demo.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 通过注解标识获取HTTP头中的token，解析成登录用户信息，适用于jwt场景
 */
public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 若不想自定义注解，可以直接在实现HandlerMethodArgumentResolver的supportsParameter直接返回true 这样每一个请求过来的都会分解该参数
     * 功能描述: 用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument。
     * supportsParameter方法，
     * 判断什么时候要执行下面的resolveArgument方法。这里我们判断当一个方法的参数含有@LoginUser
     * 并且方法的参数是我们的用户类时返回true。
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && LoginUserDTO.class.isAssignableFrom(methodParameter.getParameterType());
    }

    /**
     * 功能描述: 真正用于处理参数分解的方法，返回的Object就是controller方法上的形参对象。（获得登录用户信息）
     * resolveArgument方法，在这里我们直接把放在session中的用户信息放回去即可。
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        if (loginUser != null) {
            String token = nativeWebRequest.getHeader("Authorization");
            if (StringUtils.isBlank(token)) {
                throw new ApiException(ResultCodeEnum.UNAUTHORIZED);
            }
            // todo 解析校验jwt获取用户信息
        }
        return null;
    }

}
