import java.util.*;

public class Main {
    public static void main(String[] args) {
        String S = new Scanner(System.in).next();
        int[] A = new int[26];
        long[] F = new long[S.length() + 1];
        F[0] = 1;
        for (int i = 1; i <= S.length(); i++) {
            F[i] = i * F[i - 1];
        }
        for (char c : S.toCharArray()) {
            A[c - 'A']++;
        }
        long answer = 1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int n = c - 'A';

            for (int j = 0; j < n; j++) {
                if (A[j] == 0) continue;
                A[j]--;
                long count = F[S.length() - 1 - i];
                for (int k = 0; k < 26; k++) {
                    count /= F[A[k]];
                }
                answer += count;
                A[j]++;
            }
            A[n]--;
        }
        System.out.println(answer);
    }
}