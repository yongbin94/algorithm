import java.io.*;
import java.util.*;

public class Main {
    static int N, D, K, C, count, answer;
    static int[] sushi, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sushi = new int[N + K];
        tmp = new int[D + 1];
        for (int i = 0; i < N; i++)
            sushi[i] = Integer.parseInt(br.readLine());
        for (int i = N; i < N + K; i++)
            sushi[i] = sushi[i - N];
        for (int i = 0; i < K; i++)
            if (tmp[sushi[i]]++ == 0)
                count++;
        for (int i = 0; i < N; i++) {
            if (--tmp[sushi[i]] == 0)
                count--;
            if (tmp[sushi[i + K]]++ == 0)
                count++;
            answer = Math.max(answer, tmp[C] == 0 ? count + 1 : count);
        }
        System.out.println(answer);
    }
}