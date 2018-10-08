package com.sofn.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.entity.User;

public class LoginInterceptor2 implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		 //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //查看方法上是否有注解
        if (method.getAnnotation(Authorization.class) == null) {
            return true;
        }
        
        User user = (User) req.getSession().getAttribute("user");
		if(user == null){
//			res.sendRedirect("/demo/login.jsp");
			String jsStr = "<script>alert('xxxxx');top.location.href = '/demo/login.jsp'</script>";
			res.getWriter().write(jsStr);
			res.getWriter().close();
			System.out.println("#########################################");
			System.out.println("####----未登陆，跳转到登陆----####");
			System.out.println("#########################################");
			return false;
		}
		return true;
	}

}
