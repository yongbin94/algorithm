import java.io.*;
import java.util.*;

public class Main {
    static Set<Long> set;
    static Queue<Long> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = new ArrayDeque<>();
        q.offer(Long.parseLong(br.readLine()));
        set= new HashSet<>();
        int time = 0;
        while (!q.isEmpty()) {
            for (int i = 0, size = q.size(); i < size; i++) {
                long n = q.poll();
                if (n == 1) {
                    System.out.println(time);
                    return;
                }
                if (n % 3 == 0 && !set.contains(n / 3)) {
                    set.add(n / 3);
                    q.offer(n / 3);
                }
                if (n % 2 == 0 && !set.contains(n / 2)) {
                    set.add(n / 2);
                    q.offer(n / 2);
                }
                if (!set.contains(n - 1)) {
                    set.add(n - 1);
                    q.offer(n - 1);
                }
            }
            time++;
        }
    }
}