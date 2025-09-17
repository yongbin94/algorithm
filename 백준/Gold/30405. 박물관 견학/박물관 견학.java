import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N * 2];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            A[n * 2] = Integer.parseInt(st.nextToken());
            for (int i = 2; i < k; i++) st.nextToken();
            A[n * 2 + 1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        System.out.println(A[N - 1]);
    }
}