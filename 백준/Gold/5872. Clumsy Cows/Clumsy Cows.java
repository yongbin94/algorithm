import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int v = 0, a = 0;
		for (char c : str.toCharArray()) {
			v += c == '(' ? 1 : -1;
			if (v < 0) {
				v = 1;
				a++;
			}
		}
		System.out.println(a + v / 2);
	}
}