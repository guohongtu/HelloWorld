package guo.cn.note.dao;

import java.util.List;
import java.util.Map;

public interface NotebookDao {

	List<Map<String, Object>> findNotebookByUserId(String userId);
    int countNotebookById(String notebookId);
}
