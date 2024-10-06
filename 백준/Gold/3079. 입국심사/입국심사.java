import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        int max = 0;
        for (int n = 0; n < N; n++) {
            int v = Integer.parseInt(br.readLine());
            list.add(v);
            max = Math.max(max, v);
        }
        long answer = Long.MAX_VALUE;
        long left = 1;
        long right = (long) max * M;
        outer:
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int v : list) {
                count += mid / v;
                if (count >= M) {
                    answer = Math.min(answer, mid);
                    right = mid - 1;
                    continue outer;
                }
            }
            left = mid + 1;
        }
        System.out.println(answer);
    }
}