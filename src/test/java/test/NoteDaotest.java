package test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.support.DaoSupport;

import guo.cn.note.dao.NoteDao;
import guo.cn.note.entity.Note;

public class NoteDaotest extends BaseTest{
	NoteDao noteDao;
    @Before
    public void initNoteDao(){
    	noteDao=ctx.getBean("noteDao",NoteDao.class);
    }
    @Test
    public void findNotesByNotebookIdTest(){
    	String notebookId="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
    	List<Map<String, Object>> list=noteDao.findNotesByNotebookId(notebookId);
    	for(Map<String, Object> map:list){
    		System.out.println(map);
    	}
    }
    @Test
    public void findNoteByIdtest(){
    	String noteid="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
    	Note note=noteDao.findNoteById(noteid);
    	System.out.println(note);
    }
    @Test
    public void addNote(){
    	String id=UUID.randomUUID().toString();
    	String userId="1234";
    	String notebookId="1234";
    	String statusId="";
    	String typeId="";
    	String body="";
    	long time=System.currentTimeMillis();
    	Note note=new Note(id,notebookId,userId,statusId,typeId,body,body, time,time);
    	int n=noteDao.addNote(note);
    	System.out.println(n);
    }
    @Test
    public void findUpdataNote(){
    	Note note=new Note();
    	String noteId="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
    	note.setId(noteId);
    	note.setTitle("Test");
    	note.setBody("Test123");
    	note.setLastModifyTime(System.currentTimeMillis());
    	int n=noteDao.updateNote(note);
    	System.out.println(n);
    }
}
