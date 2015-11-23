package com.hangout.amigos.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.hangout.amigos.dao.UserDao;
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
	List<User> userList = new ArrayList<User>();
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		User userObj = new User();
		int userId = HangoutAmigosUtil.generateUserId();
		System.out.println("User id " + userId);
		userDto.setUserId(userId);
		try {
			
			BeanUtils.copyProperties(userObj, userDto);
			//userDao.saveUser(userObj);
			userList.add(userObj);
			
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
