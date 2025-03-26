public class Main {
	public static void main(String[] args) {
    long a = new java.util.Scanner(System.in).nextLong() % 5;
		System.out.println(a == 0 || a == 2 ? "CY" : "SK");
	}
}