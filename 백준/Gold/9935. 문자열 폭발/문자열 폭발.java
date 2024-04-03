import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		int[] F = new int[P.length()];

		for (int i = 1, j = 0; i < P.length(); i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j))
				j = F[j - 1];
			F[i] = P.charAt(i) == P.charAt(j) ? ++j : 0;
		}

		Stack<Character> charStack = new Stack<>();
		Stack<Integer> jStack = new Stack<>();
		
		for (int i = 0, j = 0; i < S.length(); i++) {
			if (S.charAt(i) != P.charAt(j)) {
				jStack.push(j);
				while (j > 0 && S.charAt(i) != P.charAt(j))
					j = F[j - 1];
			}
			if (S.charAt(i) == P.charAt(j)) {
				if (++j == P.length()) {
					for (int k = 1; k < P.length(); k++)
						charStack.pop();
					
					j = jStack.isEmpty() ? 0 : jStack.pop();
					if(i + 1 != S.length()) {
						int temp = j;
						while( j > 0 && S.charAt(i + 1) != P.charAt(j))
							j = F[j -1];
						if(temp != j)
							jStack.push(temp - j);
					}
				} else 
					charStack.push(S.charAt(i));
			} else {
				charStack.push(S.charAt(i));
				j = 0;
				if(jStack.peek() != 0)
					jStack.push(j);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!charStack.isEmpty())
			sb.append(charStack.pop());
		System.out.println(sb.toString().equals("") ? "FRULA" : sb.reverse());
	}
}