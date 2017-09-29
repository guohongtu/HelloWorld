package test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import com.sun.security.auth.NTDomainPrincipal;

import guo.cn.note.dao.UserDao;
import guo.cn.note.entity.User;

public class UserDaoTest extends BaseTest{
	UserDao dao;
	@Before
	public void initDao(){
		dao = ctx.getBean(
				"userDao", UserDao.class);
	}
		@Test
		public void testFindUserByName(){
			String name="demo";
			User user=dao.findUserByName(name);
			System.out.println(user);
		}
		@Test
		public void testAddUser(){
		String id=UUID.randomUUID().toString();
			String name="fdghfdg";
			String salt="今天你吃了吗?";
			String password=DigestUtils.md5Hex(salt+"123456");
			String token="1";
			String nick="1";
			User user=new User(id, name, password, token, nick);
			int n =dao.addUser(user);
			System.out.println(n);
		}
		@Test
		public void testFindUserById(){
			String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
			User user=dao.findUserById(userId);
			System.out.println(user);
		}
		
}
