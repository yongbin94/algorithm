import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");
        char[] A = new char[6];
        int[] N = new int[6];
        boolean[] C = new boolean[6];
        for (int i = 0; i < 6; i++) {
            String input = st.nextToken();
            A[i] = input.charAt(0);
            N[i] = input.charAt(0) - '0';
            if (N[i] > 10) N[i] -= 39;
            C[i] = input.charAt(1) == 'b';
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Math.abs(N[b[0]] - N[b[1]]) % 13 == 1 && Math.abs(N[a[0]] - N[a[1]]) % 13 != 1 ? 1
                        : Math.abs(N[b[0]] - N[b[1]]) % 13 != 1 && Math.abs(N[a[0]] - N[a[1]]) % 13 == 1 ? -1
                        : N[b[0]] == N[b[1]] && N[a[0]] != N[a[1]] ? 1
                        : N[b[0]] != N[b[1]] && N[a[0]] == N[a[1]] ? -1
                        : C[b[0]] == C[b[1]] && C[a[0]] != C[a[1]] ? 1
                        : C[b[0]] != C[b[1]] && C[a[0]] == C[a[1]] ? -1
                        : Math.max(N[b[0]], N[b[1]]) != Math.max(N[a[0]], N[a[1]]) ? Math.max(N[b[0]], N[b[1]]) - Math.max(N[a[0]], N[a[1]])
                        : Math.min(N[b[0]], N[b[1]]) != Math.min(N[a[0]], N[a[1]]) ? Math.min(N[b[0]], N[b[1]]) - Math.min(N[a[0]], N[a[1]])
                        : C[b[0]] && N[b[0]] == Math.max(N[b[0]], N[b[1]]) ? 1
                        : C[b[1]] && N[b[1]] == Math.max(N[b[0]], N[b[1]]) ? 1 : -1
        );
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                pq.offer(new int[]{i, j});
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            sb.append(A[a[0]]).append(C[a[0]] ? 'b' : 'w');
            sb.append(A[a[1]]).append(C[a[1]] ? 'b' : 'w');
            sb.append("\n");
        }
        System.out.println(sb);
    }
}