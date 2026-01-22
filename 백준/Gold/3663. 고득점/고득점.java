import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String S = br.readLine();
            sb.append(solve(S)).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve(String S) {
        int len = S.length();
        int total = 0;
        int min = len - 1;
        
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            total += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < len && S.charAt(next) == 'A') {
                next++;
            }

            min = Math.min(min, Math.min(i * 2 + (len - next), (len - next) * 2 + i));
        }

        return total + min;
    }
}