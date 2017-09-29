package guo.cn.note.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import guo.cn.note.dao.NotebookDao;
import guo.cn.note.dao.UserDao;
import guo.cn.note.entity.User;
import guo.cn.note.service.NotebookService;
import guo.cn.note.service.UserNotFoundException;
@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
	@Resource
	private  NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	public List<Map<String, Object>> listNotebook(String userId) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID不能空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		return notebookDao.findNotebookByUserId(userId);
	}

}
