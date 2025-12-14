import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        long D = Long.parseLong(st.nextToken());

        long answer = Long.MAX_VALUE;
        for (long a = 0; a < C; a++) {
            long c = Math.max(0, (N - a * A + C - 1) / C);
            answer = Math.min(answer, a * B + c * D);
        }

        for (long c = 0; c < A; c++) {
            long a = Math.max(0, (N - c * C + A - 1) / A);
            answer = Math.min(answer, a * B + c * D);
        }
        System.out.println(answer);
    }
}