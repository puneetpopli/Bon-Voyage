package com.hangout.amigos.impl;

import java.io.UnsupportedEncodingException;
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
import com.hangout.amigos.mail.EmailNotification;
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
		
		
		try {
			
			BeanUtils.copyProperties(userObj, userDto);
			Integer userId = HangoutAmigosUtil.generateUserId(10);
			System.out.println("User id --- " + userId);
			userObj.setUserId(userId);
			loginObj.setUserId(userId);
			loginObj.setEmail(userObj.getEmail());
			loginObj.setPassword(HangoutAmigosUtil.passwordEncryption(userObj.getEmail()));
			System.out.println("userObj.getUserId() " + userObj.getUserId());
			loginObj.setUserId(userObj.getUserId());
		
			System.out.println("User id " + userId);
			userDto.setUserId(userId);
			
			//stroing in mongo
			userDao.saveUser(userObj);
			loginDao.saveObject(loginObj);
			
			try {
				EmailNotification.sendEmailonSignUp(userObj.getEmail(), userObj.getFirstName());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
