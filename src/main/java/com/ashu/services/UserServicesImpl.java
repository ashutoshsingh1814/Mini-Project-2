package com.ashu.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.binding.LoginForm;
import com.ashu.binding.SignUpForm;
import com.ashu.binding.UnlockForm;
import com.ashu.entity.UserDtlsEntity;
import com.ashu.repo.UserDtlsRepo;
import com.ashu.util.EmailUtil;
import com.ashu.util.PwdUtil;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserDtlsRepo userRepo;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private PwdUtil pwdItil;
	
	@Autowired
	private HttpSession session;

	@Override
	public String login(LoginForm form) {
		UserDtlsEntity entity = userRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
		if(entity== null) {
			return "Invalid";
		}
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Account is Locked";
		}
		
		session.setAttribute("userId", entity.getUserId());
		return "Success";
		
		
	}

	@Override
	public boolean signUp(SignUpForm form) {

		UserDtlsEntity status = userRepo.findByEmail(form.getEmail());

		if (status != null) {
			return false;
		}

		// TODO: Copy data from binding object to entity object

		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);

		// TODO: Generate Temp password and set to object
		String pwd = pwdItil.generateRandomPassword();

		entity.setPassword(pwd);

		// TODO: set AccountStatus as Locked
		entity.setAccStatus("LOCKED");

		// TODO: Insert Records
		userRepo.save(entity);

		// TODO: Email sent to Unlock Account

		String subject = "Set Password";
		String to = form.getEmail();
		StringBuffer body = new StringBuffer("");
		body.append("<h3>Hi , Click on the Link and Use given temporary password to Set new Password</h3>");
		body.append("temporory password :    " + pwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:9090/unlock?email=" + to + "\">Click Here to Unlock your Account</a>");

		emailUtil.mailSend(subject, body.toString(), to);

		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {

		UserDtlsEntity entity = userRepo.findByEmail(form.getEmail());

		if (entity.getPassword().equals(form.getTempPwd())) {

			entity.setPassword(form.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			userRepo.save(entity);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String forgotPassword(String email) {
		
		UserDtlsEntity entity = userRepo.findByEmail(email);
		if(entity == null) {
			return "Invalid Mail Id";
		}
		
		String subject = "Recover Your password";
		StringBuffer body = new StringBuffer();
		body.append("Your Saved Password is   : "+entity.getPassword());
		
		
		emailUtil.mailSend(subject, body.toString(), email);
		
		return "Success, Please Check mail";
	}

}
