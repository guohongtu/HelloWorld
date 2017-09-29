package test;

import org.junit.Before;
import org.junit.Test;

import guo.cn.note.entity.Note;
import guo.cn.note.service.NotesService;

public class NoteService extends BaseTest {
		NotesService notesService;
	
	@Before
	public void initNoteService(){
			notesService=ctx.getBean("notesService",NotesService.class);
	}
	@Test
	public void findNoteService(){
		String noteId="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		Note note=notesService.getNote(noteId);
		System.out.println(note);
	}
	@Test
	public void addNoteTest(){
		String userId="03590914-a934-4da9-ba4d-b41799f917d1";
		String notebookId="885bbeab-ab12-4048-adb2-c6fd9c3e9be8";
		String title="乾坤大挪移";
		Note note=notesService.addNote(userId, notebookId, title);
		System.out.println(note);
	}
	@Test
	public void testUpdate(){
		String id="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		String title="Test";
		String body="今天天气不错";
		boolean b=notesService.update(id, title, body);
		Note note=notesService.getNote(id);
		System.out.println(b);
		System.out.println(note);
	}
}
