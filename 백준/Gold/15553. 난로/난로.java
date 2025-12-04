import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[N - 1];
        int prev = Integer.parseInt(br.readLine());
        int answer = prev;
        for (int n = 0; n < N - 1; n++) {
            int curr = Integer.parseInt(br.readLine());
            A[n] = curr - prev;
            prev = curr;
        }
        answer = prev - answer + 1;
        Arrays.sort(A);
        for (int i = N - K; i < N - 1; i++) {
            answer -= A[i] - 1;
        }
        System.out.println(answer);
    }
}