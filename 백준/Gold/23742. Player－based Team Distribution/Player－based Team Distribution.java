import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] A = new long[N];
        long sum = 0;
        int cnt = 0;
        for (int n = 0; n < N; n++) {
            A[n] = Long.parseLong(st.nextToken());
            if (A[n] >= 0) {
                sum += A[n];
                cnt++;
            }
        }
        Arrays.sort(A);
        long S = Arrays.stream(A).sum();
        long answer = sum * cnt + S - sum;
        for (int i = N - cnt - 1; i >= 0; i--) {
            sum += A[i];
            cnt++;
            if (sum * cnt + S - sum <= answer)
                break;
            answer = sum * cnt + S - sum;
        }
        System.out.println(answer);
    }
}
