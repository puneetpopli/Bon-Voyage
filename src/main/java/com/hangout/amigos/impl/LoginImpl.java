package com.hangout.amigos.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.hangout.amigos.dao.LoginDao;
import com.hangout.amigos.dto.Login;
import com.hangout.amigos.dto.LoginDTO;
import com.hangout.amigos.intf.LoginIntf;
import com.hangout.amigos.util.HangoutAmigosUtil;

public class LoginImpl implements LoginIntf{

	LoginDao loginDao = new LoginDao();
	List<Login> loginList = new ArrayList<Login>();
	@Override
	public LoginDTO login(LoginDTO loginDTO) {
		
		Login logindao = new Login();
		
		
		try{
			BeanUtils.copyProperties(logindao, loginDTO);
			//check username and password
			
			
			//uncomment this when mongo starts working and in if change logindao to login
			//Login login = loginDao.getObject(logindao.getEmail(), logindao.getPassword());
			if(logindao == null){
				System.out.println("Invalid Username/Password");
			}else{
				//generate sessionId
				int sessionId = HangoutAmigosUtil.getRandomInteger();
				
				//changing this because mongo is not working. uncomment when mongo starts working
				//login.setSessionId(Integer.toString(sessionId));
				
				loginList.add(logindao);
				
				//loginDao.updateObject(login);
				
				//set session id in header
				loginDTO.setSessionId(Integer.toString(sessionId));
				
				//change logindao to login when mongo starts working
				loginDTO.setUserId(logindao.getUserId());
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
