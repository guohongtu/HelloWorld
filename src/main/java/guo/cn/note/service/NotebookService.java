package guo.cn.note.service;

import java.util.List;
import java.util.Map;

public interface NotebookService {
       List<Map<String, Object>> listNotebook(String userId)
       throws UserNotFoundException;

	
}
