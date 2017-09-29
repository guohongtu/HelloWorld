package test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import guo.cn.note.dao.NotebookDao;

public class NotebookDaoTest extends BaseTest{
	@Resource
	NotebookDao notebookDao;
	@Before
	public void initDao(){
		notebookDao=ctx.getBean("notebookDao",NotebookDao.class);
	}
	@Test
	public void testFindNotebook(){
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String, Object>> list=notebookDao.findNotebookByUserId(userId);
		for(Map<String, Object> map:list){
			System.out.println(map);
		}
  }
   @Test
   public void testCountNotebookById(){
	   String notebookId="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
	   int n=notebookDao.countNotebookById(notebookId);
	   System.out.println(n);
   }
}
