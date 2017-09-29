package guo.cn.note.service.impl;




import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import guo.cn.note.dao.UserDao;
import guo.cn.note.entity.User;
import guo.cn.note.service.PasswordNotFoundException;
import guo.cn.note.service.UserNameException;
import guo.cn.note.service.UserNotFoundException;
import guo.cn.note.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
   @Value("#{jdbc.salt}")
   private String salt;
   @Resource
	private UserDao userDao;
		public User loginService(String name,String password)throws 
	     UserNotFoundException,PasswordNotFoundException{
			if(password==null || password.trim().isEmpty()){
				throw new PasswordNotFoundException("密码空");
			}
			if(name==null || name.trim().isEmpty()){
				throw new PasswordNotFoundException("用户不存在");
			}
			User user=userDao.findUserByName(name);
			if(user==null){
				throw new UserNotFoundException("name错误");
			}
			String pwd=DigestUtils.md5Hex(salt+password.trim());
			if(pwd.equals(user.getPassword())){
				return user;
			}
			throw new PasswordNotFoundException("密码错误");
		}
		public User regist(String name, String nick, String password, String confirm)
				throws UserNameException, PasswordNotFoundException {
			if(name==null||name.trim().isEmpty()){
				throw new UserNameException("不能空"); 
			}
			User one=userDao.findUserByName(name);
			if(one!=null){
				throw new UserNameException("已注册");
			}
			if(password==null||password.trim().isEmpty()){
				throw new PasswordNotFoundException("不能空");
			}
			if(!password.equals(confirm)){
				throw new PasswordNotFoundException("密码不一致");
			}
			if(nick==null||nick.trim().isEmpty()){
				nick=name;
			}
			String id=UUID.randomUUID().toString();
			String token="";
			password=DigestUtils.md5Hex(salt+password);
			User user=new User(id,name,password,token,nick);
			int n=userDao.addUser(user);
			if(n!=1){
				throw new RuntimeException("添加失败");
			}
			return user;
		}
		
}
