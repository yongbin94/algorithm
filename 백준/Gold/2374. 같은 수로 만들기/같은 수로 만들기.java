import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer = 0;
        long prev = Long.parseLong(br.readLine());
        long max = prev;
        while (N-- > 1) {
            long curr = Long.parseLong(br.readLine());
            max = Math.max(max, curr);

            if (prev < curr) {
                answer += curr - prev;
            }
            prev = curr;
        }
        answer += max - prev;
        System.out.println(answer);
    }
}