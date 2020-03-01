package cn.itcast.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.tomcat.util.security.PermissionCheck;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.core.Constant.Constant;
import cn.itcast.core.perssion.perssionCheck;
import cn.itcast.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain chain)// 所有的aciton访问都要
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) ServletRequest;
		HttpServletResponse response = (HttpServletResponse) ServletResponse;
		String url = request.getRequestURI();
		// 判断当前请求是否是登录地址
		if (!url.contains("sys/login_")) {
			// 如果是非登录请求
			if (request.getSession().getAttribute(Constant.USER) != null) {
				// 已经登录过，放行
				// 判断是否访问纳税服务系统
				if (url.contains("/nsfw/")) {
					// 访问纳税服务系统
					User user = (User) request.getSession().getAttribute(Constant.USER);
					// 获取application容器，
					WebApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					perssionCheck pc = (perssionCheck) application.getBean("perssionCheck");
					if (pc.isAccessible(user, "nsfw")) {
						// 说明有权限放行
						chain.doFilter(request, response);
					} else {
						// 沒有权限到提示页面
						response.sendRedirect(request.getContextPath() + "/sys/login_toNoPermissionUI.action");
					}
				} else {
					// 非纳税服务则直接放行
					chain.doFilter(request, response);
				}
			} else {
				// 没有登录
				response.sendRedirect(request.getContextPath() + "/sys/login_toLoginUI.action");
			}

		} else {
			// 登录请求直接放行
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
