package com.mayab.quality.logginunittest.service;

//Import mockito

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import com.mayab.quality.loginunittest.service.LoginService;
import com.mayab.quality.loginunittest.service.UserService;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
//Import hamcrest
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

	IDAOUser dao;
	User user;
	LoginService service;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = mock(IDAOUser.class);
		user = mock(User.class);
		service = new LoginService(dao);
		
	}

	@Test
	void loginSuccess_whenPasswordCorrect() {
		when(user.getPassword()).thenReturn("123");
		when(dao.findByUserName("name")).thenReturn(user);
	
		
		//exercise
		boolean res = service.login("name","123");
		
		//assertion
		assertThat(res,is(true));
		
		
	}

}
