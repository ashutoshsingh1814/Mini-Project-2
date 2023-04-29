package com.ashu.services;

import com.ashu.binding.LoginForm;
import com.ashu.binding.SignUpForm;
import com.ashu.binding.UnlockForm;

public interface UserServices {
	
	public String login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String forgotPassword(String email);
	
	
	

}
