package com.hangang.HangangRiver.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.access.service.AccessService;
import com.hangang.HangangRiver.exceptions.LoginValidateException;
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AccessService accessService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath();

		if (checkPath("/swagger", servletPath)) {
			return true;
		}
		if (checkPath("/webjars", servletPath)) {
			return true;
		}
		if (checkPath("/images", servletPath)) {
			return true;
		}
		if (checkPath("/error", servletPath)) {
			return true;
		}
		if (checkPath("/v2", servletPath)) {
			return true;
		}
		if (checkPath("/configuration", servletPath)) {
			return true;
		}

		String reqHangang_token =request.getHeader("hangang_token");
		String reqUser_id =request.getHeader("user_id");
		User reqUser = accessService.getUserDetailById(reqUser_id);
		if (reqUser.getUser_id().equals(reqUser_id) && reqUser.getHangang_token().equals(reqHangang_token)){
			System.out.println("================ LoginUser");
			return true;
		}
		System.out.println("================ Before Method");
		throw new LoginValidateException();
	}

	private boolean checkPath(String url, String servletPath) {
		if (servletPath.startsWith(url)) {
			Matcher matcher = Pattern.compile(url).matcher(servletPath);
			if (matcher.find()) {;
				System.out.println("Interceptor URL - {}"+url);
				return true;
			}
		}
		return false;
	}

}
