package basic;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_Type {

	public static void main(String[] args) {

		// 1. Kiểu dữ liệu nguyên thủy (Primitive type)
		// Number
		// Integer: số nguyên ko dấu
		// byte/short/int/long

		byte bNumber = 5;
		short sNumber = 100;
		int studentNumber = 1000;
		long timeout = 20000;

		// Số thực (có dấu)
		// float/double
		float studentPoint = 8.5f;
		double employeeSalary = 28.5d;

		// Char
		// char (kí tự)
		char c = 'Q';
		char specialChar = '$';

		// Boolean
		// boolean
		boolean status = true;
		status = false;

		// 2. Kiểu dữ liệu tham chiếu (Reference Type)
		// String
		String studentName = "Min";
		String studentAddress = new String("35 Lê Văn Lương");

		// Array (tập hợp kiểu dữ liệu giống nhau)
		String[] studentNames = { "Nguyen Van A", "Le Van B", "Do Van C" };

		// Class
		WebDriver driver = new FirefoxDriver();

		Actions action = new Actions(driver);

		// Interface
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Collection (Set/Queue/List)
		// 1 element
		WebElement emailTextbox = driver.findElement(By.id(""));

		// n elements
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		Set<String> allWindows = driver.getWindowHandles();
	}

}
