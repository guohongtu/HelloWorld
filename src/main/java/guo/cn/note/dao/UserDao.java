package guo.cn.note.dao;

import guo.cn.note.entity.User;

public interface UserDao {
	User findUserByName(String name);	
	int addUser(User user);
	User findUserById(String userId);
	
}
