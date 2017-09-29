package guo.cn.note.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import guo.cn.note.util.JsonResult;

public abstract class AbstractController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handException(Exception e){
    	e.printStackTrace();
    	return new JsonResult(e);
    }
}
