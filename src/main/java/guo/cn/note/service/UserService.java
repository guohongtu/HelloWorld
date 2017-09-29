package guo.cn.note.service;

import guo.cn.note.entity.User;

public interface UserService {
     User loginService(String name,String password)throws 
     UserNotFoundException,PasswordNotFoundException;
     User regist(String name,String nick,String password,String confirm)
     throws UserNameException,PasswordNotFoundException;
     
}
