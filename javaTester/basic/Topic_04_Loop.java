package basic;

import java.util.ArrayList;
import java.util.List;

public class Topic_04_Loop {

	public static void main(String[] args) {
		// List<String> students = new ArrayList<>();
		// students.add("Nguyen Van A");
		// students.add("Nguyen Van B");
		// students.add("Nguyen Van C");

		// for (String student : students) {
		// System.out.println(student);
		// }

		int number = 20;
		for (int i = 1; i <= number; i++) {
			System.out.println(i);
			// Lần lặp đầu tiên i = 1
			// Kiểm tra i có < number hay ko: i = 1 <= 20
			// -> thỏa mãn đkiện -> chưa thoát khỏi vòng lặp
			// In ra i = 1
			// Tăng i lên 1 đơn vị: i++ -> i = 2

			// ...

			// Lần lặp 20 thì i = 20
			// Kiểm tra i có < number hay ko: i = 20 <= 20
			// -> thỏa mãn đkiện -> chưa thoát khỏi vòng lặp
			// In ra i = 20
			// Tăng i lên 1 đơn vị: i++ -> i = 21
			
			// Lần lặp 21 thì i = 21
			// Kiểm tra i có < number hay ko: i = 21 > 20
			// -> ko thỏa mãn đkiện -> thoát khỏi vòng lặp
			
			if (i==10) {
				break;
			}
			
		}
	}

}
