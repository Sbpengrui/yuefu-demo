package com.sofn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.entity.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		String url = req.getRequestURI();
		if(url.indexOf("login")>0){
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
