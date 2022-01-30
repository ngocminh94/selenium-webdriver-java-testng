package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Assert {
	// Kiểu dữ liệu nguyên thủy sẽ có gtri mặc định nếu chưa khởi tạo (đối vs biến global, nếu là biến local thì ko))
	Object studentAddress = null;
	
  @Test()
  public void TC_01_Assert() {
	  String studentName = "Min Trinh";
	  
	  
	  // Những hàm trả về kiểu boolean: isDisplayed/ isEnabled/ isSelected/ isMultiple 
	  // --> hay dùng assertTrue/ assertFalse 
	  
	  // Verify 1 điều kiện trả về là đúng
	  Assert.assertTrue(studentName.contains("Min"));
	  
	  // Verify 1 điều kiện trả về là sai
	  Assert.assertFalse(studentName.contains("Ngoc"));
	  
	  // Verify 2 điều kiện bằng nhau
	  Assert.assertEquals(studentName, "Min Trinh");
	  
	  Assert.assertNotEquals(studentName, "Min Min");
	  
	  
	  Assert.assertNull(studentAddress);
	  
	  studentAddress = "Lê Văn Lương";
	  
	  Assert.assertNotNull(studentAddress);
	  
  }

}
