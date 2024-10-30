package com.mayab.quality.logginunittest.service;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.HashMap;

//Import hamcrest
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import com.mayab.quality.loginunittest.service.UserService;

public class UserServiceTest {
	
	private UserService service;
	private IDAOUser dao;
	private HashMap<Integer , User> db;
	

	@BeforeEach
	public void setUp() throws Exception {
		dao = mock(IDAOUser.class);
		service = new UserService(dao);
		db = new HashMap<Integer, User>();
	}

	@Test
	public void whenPasswordShort_test() {
		//initialize 
		String shortPass = "123";
		String name = "user1";
		String email = "user1@email.com";
		User user = null;
		//Fake code for findUserByEmail & save methods
		when(dao.findUserByEmail(anyString())).thenReturn(user);
		when(dao.save(any(User.class))).thenReturn(1);
		
		//Exercise
		user = service.createUser(name, email, shortPass);
		
		//Verify
		//assertThat(user,isNullValue(null));
		
		
	}
	
	@Test
	void whenAllDataCorrect_saveUserTest() {
		//Initialization
		int sizeBefore = db.size();
		System.out.println("sizeBefore = "+sizeBefore);
		//Fake code for findUserByEmail & save
		when(dao.findUserByEmail(anyString())).thenReturn(null);
		when(dao.save(any(User.class))).thenAnswer(new Answer<Integer>() {
			// Method within the class
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior in every invocation 
				User arg = (User) invocation.getArguments()[0]; 
				db.put(db.size()+1, arg); 
				//System.out.println("Size after=" + db.size() + "\n"); 
				// Return the invoked value
				return db.size()+1; 
				}
			}
		);
		//Exercise
		User user = service.createUser("hola","hola@email.com","pass1234");
		
		//Verify
		assertThat(db.size(),is(sizeBefore+1));
		
	}
	
	@Test
	void whenUserUpdateData_test() {
		//Initialize
		User oldUser = new User("oldUser","oldEmail","oldPassword");
		db.put(1, oldUser);
		oldUser.setId(1);
		User newUser = new User("newUser","oldEmail","newPassword");
		newUser.setId(1);
		when(dao.findById(1)).thenReturn(oldUser);
		
		when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
			// Method within the class
			public User answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior in every invocation 
				User arg = (User) invocation.getArguments()[0]; 
				db.replace(arg.getId(), arg);
				
				// Return the invoked value
				return db.get(arg.getId()); 
				}
			}
		);
		//Exercise
		User result = service.updateUser(newUser);
		
		//Verification
		assertThat(result.getName(),is("newUser"));
		assertThat(result.getPassword(),is("newPassword"));
		
	}

}
