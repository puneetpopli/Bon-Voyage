package com.hangout.amigos.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.hangout.amigos.dao.LoginDao;
import com.hangout.amigos.dao.UserDao;
import com.hangout.amigos.dto.Login;
import com.hangout.amigos.dto.User;
import com.hangout.amigos.dto.UserDTO;
import com.hangout.amigos.intf.UserOperationIntf;
import com.hangout.amigos.util.HangoutAmigosUtil;

/**
 * 
 * @author puneetpopli
 *
 */

@Component
public class UserOperationImpl implements UserOperationIntf{

	UserDao userDao = new UserDao();
	LoginDao loginDao = new LoginDao();
	List<User> userList = new ArrayList<User>();
	List<Login> loginList = new ArrayList<Login>();
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		User userObj = new User();
		Login loginObj = new Login();
		int userId = HangoutAmigosUtil.generateUserId();
		
		try {
			
			BeanUtils.copyProperties(userObj, userDto);
			
			loginObj.setEmail(userObj.getEmail());
			loginObj.setPassword(HangoutAmigosUtil.passwordEncryption(userObj.getEmail()));
			loginObj.setUserId(userObj.getUserId());
		
			System.out.println("User id " + userId);
			userDto.setUserId(userId);
			
			//userDao.saveUser(userObj);
			//loginDao.saveObject(loginObj);
			
			userList.add(userObj);
			loginList.add(loginObj);
			
			System.out.println("User created");
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		return userDto;
	}

	@Override
	public UserDTO getUser(String userId) {
		
		UserDTO userDto = new UserDTO();
		
		
		User getUserDao = userDao.getUser(userId);
		
		try {
			BeanUtils.copyProperties(userDto, getUserDao);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		return userDto;
	}

	
	
}
