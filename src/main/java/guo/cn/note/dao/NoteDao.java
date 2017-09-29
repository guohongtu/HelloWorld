package guo.cn.note.dao;

import java.util.List;
import java.util.Map;

import guo.cn.note.entity.Note;

public interface NoteDao {
	List<Map<String, Object>> findNotesByNotebookId(String notebookId);
	Note findNoteById(String noteId);
	int addNote(Note note);
	int updateNote(Note note);
	
}
