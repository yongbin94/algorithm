import java.io.*;

public class Main {
    static StringBuilder sb;
    static int N;
    static int[] A, selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            N = str.length();
            A = new int[26];
            selected = new int[N];
            for (int n = 0; n < N; n++) {
                A[str.charAt(n) - 'a']++;
            }
            recur(0);
        }
        System.out.println(sb);
    }

    private static void recur(int i) {
        if (i == N) {
            for (int n = 0; n < N; n++) {
                sb.append((char) (selected[n] + 'a'));
            }
            sb.append("\n");
            return;
        }
        for (int a = 0; a < 26; a++) {
            if (A[a] == 0) continue;
            selected[i] = a;
            A[a]--;
            recur(i + 1);
            A[a]++;
        }
    }
}