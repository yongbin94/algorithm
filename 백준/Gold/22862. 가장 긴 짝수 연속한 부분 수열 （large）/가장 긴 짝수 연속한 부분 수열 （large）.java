import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            S[i] = Integer.parseInt(st.nextToken());
        int i = 0, j = 0, count = 0, answer = 0;
        while (j < N) {
            if (count < K) {
                if (S[j] % 2 != 0)
                    count++;
                j++;
                answer = Math.max(answer, j - i - count);
            } else if(S[j] % 2 == 0) {
                j++;
                answer = Math.max(answer, j - i - count);
            } else {
                if(S[i] % 2 != 0)
                    count--;
                i++;
            }
        }
        System.out.println(answer);
    }
}