package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_Dependencies {
	// Sử dụng khi testcase bị phụ thuộc nhau
	// vì tcs trên fail thì tcs dưới cũng fail
	
	@Test()
	public void Create_New_Account() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "Create_New_Account")
	public void View_Account() {

	}

	@Test(dependsOnMethods = "View_Account")
	public void Edit_Account() {

	}

	@Test(dependsOnMethods = "Edit_Account")
	public void Move_Account() {

	}

	@Test(dependsOnMethods = "Move_Account")
	public void Delete_Account() {

	}

}
