import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String S = br.readLine();
			if (S.equals("end"))
				return;
			int x = 0;
			int o = 0;
			int d = 0;
			for (char c : S.toCharArray()) {
				if (c == 'X')
					x++;
				else if (c == 'O')
					o++;
				else
					d++;
			}
			if (!(x == o || x == o + 1)) {
				System.out.println("invalid");
				continue;
			}
			int bingoX = 0;
			int bingoO = 0;
			for (int i = 0; i < 3; i++) {
				if (S.charAt(0 + i * 3) == S.charAt(1 + i * 3) && S.charAt(1 + i * 3) == S.charAt(2 + i * 3)) {
					if (S.charAt(0 + i * 3) == 'X')
						bingoX++;
					else if(S.charAt(0 + i * 3) == 'O')
						bingoO++;
				}
				if (S.charAt(0 + i) == S.charAt(3 + i) && S.charAt(3 + i) == S.charAt(6 + i)) {
					if (S.charAt(0 + i) == 'X')
						bingoX++;
					else if(S.charAt(0 + i) == 'O')
						bingoO++;
				}
			}
			if (S.charAt(0) == S.charAt(4) && S.charAt(4) == S.charAt(8)) {
				if (S.charAt(0) == 'X')
					bingoX++;
				else if(S.charAt(0) == 'O')
					bingoO++;
			}

			if (S.charAt(2) == S.charAt(4) && S.charAt(4) == S.charAt(6)) {
				if (S.charAt(2) == 'X')
					bingoX++;
				else if(S.charAt(2) == 'O')
					bingoO++;
			}
			
			if((bingoX > 0 && bingoO > 0) || (bingoX > 0 && x == o) || (bingoO > 0 && x > o) || (bingoX + bingoO == 0 && d > 0)) {
				System.out.println("invalid");
				continue;
			}
			System.out.println("valid");
		}

	}
}