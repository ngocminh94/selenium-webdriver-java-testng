package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enabled {
	// Sort: alphabet
	
	@Test(priority = 1)
	public void Create_New_Account() {

	}

	@Test(description = "Diễn giải testcase ra đây cho dễ hiểu nhé")
	public void View_Account() {

	}

	@Test(priority = 3)
	public void Edit_Account() {

	}

	@Test(priority = 4)
	public void Move_Account() {

	}

	@Test(enabled = false)
	public void Delete_Account() {

	}

}
