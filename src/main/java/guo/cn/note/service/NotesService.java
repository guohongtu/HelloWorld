package guo.cn.note.service;

import java.util.List;
import java.util.Map;

import guo.cn.note.entity.Note;

public interface NotesService {
    List<Map<String, Object>> listNotes(String notebookId)
    throws NotebookNoteFoundException;
    Note getNote(String noteId) throws NoteNotFoundException;
    public Note addNote(String userId,String notebookId,String title);
    boolean update(String noteId,String title,String body ) throws NoteNotFoundException;
}
