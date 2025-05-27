import java.io.*;

public class Main {
    static final int TIME = 12 * 60;
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            A[n] = Integer.parseInt(input.substring(0, 2)) * 60 + Integer.parseInt(input.substring(3, 5));
        }
        int answer = Integer.MAX_VALUE;
        for (int t = 1; t <= TIME; t++) {
            boolean[] B = new boolean[TIME];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                int v = ((A[i] - t * i) % TIME + TIME) % TIME;
                if (B[v]) continue;
                B[v] = true;
                cnt++;
            }
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);
    }
}