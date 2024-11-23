import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        long sum = 0;
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            long v = Long.parseLong(st.nextToken());
            sum += v;
            answer += map.getOrDefault(sum - K, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        System.out.println(answer);
    }
}
