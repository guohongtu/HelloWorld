package guo.cn.note.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import guo.cn.note.dao.NoteDao;
import guo.cn.note.dao.NotebookDao;
import guo.cn.note.dao.UserDao;
import guo.cn.note.entity.Note;
import guo.cn.note.entity.User;
import guo.cn.note.service.NoteNotFoundException;
import guo.cn.note.service.NotebookNoteFoundException;
import guo.cn.note.service.NotesService;
import guo.cn.note.service.UserNotFoundException;
@Service("notesService")
public class NoteServiceImpl implements NotesService {
	@Resource
	private UserDao userDao;
	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;
	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNoteFoundException {
		
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new NotebookNoteFoundException("ID不存在");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookNoteFoundException("没有笔记本");
		}
		 return noteDao.findNotesByNotebookId(notebookId);
	}
	public Note getNote(String noteId) throws NoteNotFoundException {
		if(noteId==null|noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID空");
		}
		Note note=noteDao.findNoteById(noteId);
		if(note==null){
			throw new NotebookNoteFoundException("id错误");
		}
		return note;
	}
	public Note addNote(String userId, String notebookId, String title) {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID是空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("没有人");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookNoteFoundException("没有笔记本");
		}
		if(title==null || title.trim().isEmpty()){
			title="葵花宝典";
		}
		String id=UUID.randomUUID().toString();
		String statusId="0";
		String typeId="0";
		String body="";
		long time=System.currentTimeMillis();
		Note note=new Note(id, notebookId, userId, statusId, typeId, title, body, time,time);
		n=noteDao.addNote(note);
		if(n!=1){
			throw new NoteNotFoundException("保存失败");
		}
		return note;
	}
	public boolean update(String noteId, String title, String body) throws NoteNotFoundException {
		if(noteId==null|| noteId.trim().isEmpty()){
			throw new NoteNotFoundException("Id不能为空");
		}
		Note note=noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		}
		Note data=new Note();
		if(title!=null && !title.equals(note.getTitle())){
			data.setTitle(title);
		}
		if(body!=null && !body.equals(note.getBody())){
			data.setBody(body);
		}
		data.setId(noteId);
		data.setLastModifyTime(System.currentTimeMillis());
		System.out.println(data);
		int n=noteDao.updateNote(data);
		return n==1;
	}
}
