package com.hangout.amigos.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.hangout.amigos.dao.LoginDao;
import com.hangout.amigos.dto.Login;
import com.hangout.amigos.dto.LoginDTO;
import com.hangout.amigos.intf.LoginIntf;
import com.hangout.amigos.util.HangoutAmigosUtil;

public class LoginImpl implements LoginIntf{

	LoginDao loginDao = new LoginDao();
	
	@Override
	public LoginDTO login(LoginDTO loginDTO) {
		
		Login logindao = new Login();
		
		try{
			BeanUtils.copyProperties(logindao, loginDTO);
			//check username and password
			Login login = loginDao.getObject(logindao.getEmail(), logindao.getPassword());
			if(login == null){
				System.out.println("Invalid Username/Password");
			}else{
				//generate sessionId
				int sessionId = HangoutAmigosUtil.getRandomInteger();
				login.setSessionId(Integer.toString(sessionId));
				loginDao.updateObject(login);
				//set session id in header
				loginDTO.setSessionId(Integer.toString(sessionId));
				loginDTO.setUserId(login.getUserId());
				System.out.println("Login Successful");
			}
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginDTO;
	}

}
