package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class testMd5 {
	@Test
   public void test(){
	   String str="123456";
	   String md5=DigestUtils.md5Hex(str);
	   System.out.println(md5);
	   String salt="今天吃了吗?";
	   md5=DigestUtils.md5Hex(salt+str);
	   System.out.println(md5);
	   
   }
   
}
