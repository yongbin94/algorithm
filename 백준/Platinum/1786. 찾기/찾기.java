import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S = br.readLine();
        String P = br.readLine();

        int[] F = new int[P.length()];
        int j = 0;
        for (int i = 1; i < P.length(); i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j)) {
                j = F[j - 1];
            }
            if (P.charAt(i) == P.charAt(j)) {
                F[i] = ++j;
            }
        }

        int cnt = 0;
        j = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == P.charAt(j)) {
                if (++j == P.length()) {
                    cnt++;
                    sb.append(i - P.length() + 2).append(" ");
                    i -= j;
                    j = j > 0 ? F[j - 1] : 0;
                    i += j + 1;
                }
            } else {
                i -= j;
                j = j > 0 ? F[j - 1] : 0;
                i += j;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}