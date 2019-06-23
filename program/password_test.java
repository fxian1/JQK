import java.util.Scanner;
import java.io.Console;

public final class password_test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Console con = System.console();
		
		String scan_in = sc.nextLine();
		System.out.println(scan_in);

		char[] input = con.readPassword();
		String password = String.valueOf(input);
		System.out.println(input);

	}
}