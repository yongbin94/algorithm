import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, B, C;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            A = new int[N + 1];
            for (int i = 1; i <= N; i++)
                A[i] = Integer.parseInt(st.nextToken());
            B = new int[N + 1];
            C = new int[N + 1];
            visited = new boolean[N + 1];
            int time = 1, answer = N;
            for (int i = 1; i <= N; i++) {
                if (B[i] != 0)
                    continue;
                B[i] = time;
                int next = A[i], count = 0;
                while (true) {
                    if (B[next] == time) {
                        answer -= count - C[next] + 1;
                        break;
                    }
                    if(B[next] != 0)
                        break;
                    B[next] = time;
                    C[next] = ++count;
                    next = A[next];
                }
                time++;
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}