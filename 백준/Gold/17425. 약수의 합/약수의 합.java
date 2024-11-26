import java.io.*;

public class Main {
    static final int N = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] A = new long[N];
        long[] B = new long[N];
        for (int n = 1; n < N; n++) {
            for (int i = 1; n * i < N; i++)
                A[n * i] += n;
            B[n] = B[n - 1] + A[n];
        }
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0)
            sb.append(B[Integer.parseInt(br.readLine())]).append("\n");
        System.out.println(sb);
    }
}