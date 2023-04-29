package com.ashu.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
@Component
public class PwdUtil {
	
	public String generateRandomPassword() {
		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 8, characters );
		return pwd;
	}
	


}
