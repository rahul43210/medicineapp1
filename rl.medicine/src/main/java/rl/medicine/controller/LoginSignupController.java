package rl.medicine.controller;

import static rl.medicine.utility.AppLogger.appDebug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rl.medicine.model.LoginDetailModel;
import rl.medicine.service.LoginSignupService;
import rl.medicine.utility.AppConstant;

@Controller
public class LoginSignupController {

	private final static String TAG = LoginSignupController.class.getSimpleName();
	
	@Autowired
	private LoginSignupService loginSignupService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAction(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		appDebug(TAG+" loginAction method");

		
		final String actionValue = request.getParameter(AppConstant.LOGIN_JSP_ACTION_VALUE);
		if(actionValue.equalsIgnoreCase(AppConstant.LOGIN_JSP_ACTION_VALUE_LOGIN)) {
			
			final String userName = request.getParameter(AppConstant.LOGIN_USERNAME);
			final String password = request.getParameter(AppConstant.LOGIN_PASSWORD);
			
			if(loginSignupService.loginAction(new LoginDetailModel(userName, password))) {
				modelMap.addAttribute(AppConstant.WELCOME_PAGE_MESSAGE, "Login successfully");
			}
		}
		if(actionValue.equalsIgnoreCase(AppConstant.LOGIN_JSP_ACTION_VALUE_SIGN_UP)) { 
			appDebug(" Sign up actions. ");
		}
		return AppConstant.JSP_APP_HOME;
	}
}
