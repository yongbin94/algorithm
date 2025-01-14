import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] A = new int[10001];
        A[1] = 1;
        A[2] = 2;
        A[3] = 3;
        for (int n = 4; n <= 10000; n++)
            A[n] = 1 + (n / 2) + A[n - 3];
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0)
            sb.append(A[Integer.parseInt(br.readLine())]).append("\n");
        System.out.println(sb);
    }
}