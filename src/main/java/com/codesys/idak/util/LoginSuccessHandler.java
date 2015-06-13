package com.codesys.idak.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import com.codesys.idak.entity.SystemUser;
import com.codesys.idak.entity.UserRole;
import com.codesys.idak.service.SystemUserService;

@Component(value = "loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private SystemUserService systemUserService;

	private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			UserDetails userDetails = (UserDetails) (principal instanceof UserDetails ? principal
					: null);
			// check if the user is logged in
			if (userDetails != null) {
				try {
					SystemUser systemUserNew = systemUserService
							.getSystemUserByUserName(userDetails.getUsername());
					UserRole userRole = systemUserNew.getUserRole();

					HttpSession session = request.getSession();

					session.setMaxInactiveInterval(10000000);
					session.setAttribute(ApplicationConstants.SYSTEM_USER,
							systemUserNew);

					DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request
							.getSession().getAttribute(
									"SPRING_SECURITY_SAVED_REQUEST_KEY");

					if (defaultSavedRequest != null) {

						target.onAuthenticationSuccess(request, response,
								authentication);
					} else {

						if (!(userRole == null)) {

							if (userRole.getUserRoleType() == UserRoleType.ROLE_SUPER_ADMIN) {
								response.sendRedirect("adminDashboard.htm");
							} else if (userRole.getUserRoleType() == UserRoleType.ROLE_ADMIN) {
								response.sendRedirect("adminDashboard.htm");
							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}
}

