package test;

import org.junit.Before;
import org.junit.Test;

import com.sun.corba.se.spi.activation.InitialNameService;

import guo.cn.note.entity.User;
import guo.cn.note.service.UserService;

public class UserServiceTest extends BaseTest{
	UserService service;
	@Before
	public void InitService(){
		service=ctx.getBean("userService",UserService.class);
	}
	
    @Test
    public void loginTest(){
    	User user=service.loginService("demo", "8bbf37d0962bf915a780f410ec1a4741");
    	System.out.println(user);
    }
    @Test
    public void regist(){
    	User user=service.regist("guot", "Andy", "123456", "123456");
    	System.out.println(user);
    }
}
