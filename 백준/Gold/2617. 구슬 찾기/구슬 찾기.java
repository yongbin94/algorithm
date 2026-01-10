import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] A = new boolean[N + 1][N + 1];
        int mid = (N + 1) / 2;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (A[i][k] && A[k][j]) A[i][j] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int a = 0;
            int b = 0;
            for (int j = 1; j <= N; j++) {
                if (A[i][j]) a++;
                else if (A[j][i]) b++;
            }
            if (a >= mid || b >= mid) answer++;
        }

        System.out.println(answer);
	}
}
