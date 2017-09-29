package guo.cn.note.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guo.cn.note.entity.Note;
import guo.cn.note.service.NotesService;
import guo.cn.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {
        @Resource
        private NotesService notesService;
        @RequestMapping("/list.do")
        @ResponseBody
        public JsonResult list(String notebookId){
        	List<Map<String, Object>> list=notesService.listNotes(notebookId);
        	return new JsonResult(list);
        }
        @RequestMapping("/load.do")
        @ResponseBody
        public JsonResult load(String noteId){
        	Note note=notesService.getNote(noteId);
        	return new JsonResult(note);
        }
        @RequestMapping("/add.do")
        @ResponseBody
        public JsonResult add(String userId,String notebookId,String title){
        	Note note=notesService.addNote(userId, notebookId, title);
        	return new JsonResult(note);
        }
        @RequestMapping("/update.do")
        @ResponseBody
        public JsonResult update(String noteId,String body,String title){
        	Boolean success=notesService.update(noteId, title, body);
        	return new JsonResult(success);
        }
        
        
}
