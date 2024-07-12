import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] C = new int[20];
        for (int n = 0; n < N; n++)
            for (int v = Integer.parseInt(br.readLine()), cnt = 0; v > 0; v /= 2)
                C[cnt++] += v % 2;
        long answer = 0;
        for (int i = 0; i < 20; i++)
            answer += (1L << i) * (N - C[i]) * C[i];
        System.out.println(answer);
    }
}
