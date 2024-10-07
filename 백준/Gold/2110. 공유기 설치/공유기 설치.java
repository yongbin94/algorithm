import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Long> list = new ArrayList<>();
        for (int n = 0; n < N; n++)
            list.add(Long.parseLong(br.readLine()));
        Collections.sort(list);
        long left = 1;
        long right = list.get(list.size() - 1);
        long answer = 0;
        outer:
        while (left <= right) {
            long mid = (right + left) / 2;
            int count = 1;
            long curr = list.get(0);
            for (int n = 1; n < N; n++)
                if (curr + mid < list.get(n)) {
                    if (++count >= M) {
                        left = mid + 1;
                        continue outer;
                    }
                    curr = list.get(n);
                }
            answer = mid;
            right = mid - 1;
        }
        System.out.println(answer);
    }
}