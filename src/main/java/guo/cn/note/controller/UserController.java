package guo.cn.note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guo.cn.note.entity.User;
import guo.cn.note.service.PasswordNotFoundException;
import guo.cn.note.service.UserNameException;
import guo.cn.note.service.UserNotFoundException;
import guo.cn.note.service.UserService;
import guo.cn.note.util.JsonResult;
@Controller
@RequestMapping("user")
public class UserController extends AbstractController {
	@Resource
	private UserService userService;
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password){
		User user=userService.loginService(name, password);
		return new JsonResult(user);
	}
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public Object handleUserNotFound(UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	@ExceptionHandler(PasswordNotFoundException.class)
	@ResponseBody
	public Object handlePasswordNotFound(PasswordNotFoundException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}
	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public Object handleUserNameExcetion(UserNameException e){
		e.printStackTrace();
		return new JsonResult(4,e);
	}
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name,String nick,String password,String confirm){
		User user=userService.regist(name, nick, password, confirm);
		return new JsonResult(user);
	}
	
	
}



















