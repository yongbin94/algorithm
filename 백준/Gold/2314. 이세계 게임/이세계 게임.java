import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = 0;
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 4; j++) {
                A <<= 1;
                A |= input.charAt(j) == 'P' ? 1 : 0;
            }
        }
        br.readLine();
        int B = 0;
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 4; j++) {
                B <<= 1;
                B |= input.charAt(j) == 'P' ? 1 : 0;
            }
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        set.add(B);
        q.add(B);
        int res = 0;
        while (true) {
            for (int n = 0, size = q.size(); n < size; n++) {
                int u = q.poll();
                if (u == A) {
                    System.out.println(res);
                    return;
                }
                for (int i = 0; i < 15; i++) {
                    if (i % 4 != 3) {
                        int v = u;
                        if (((u >> i) & 1) != ((u >> i + 1) & 1)) {
                            v ^= (1 << i) | (1 << i + 1);
                        }
                        if (!set.contains(v)) {
                            set.add(v);
                            q.offer(v);
                        }
                    }
                    if (i / 4 != 3) {
                        int v = u;
                        if (((u >> i) & 1) != ((u >> i + 4) & 1)) {
                            v ^= (1 << i) | (1 << i + 4);
                        }
                        if (!set.contains(v)) {
                            set.add(v);
                            q.offer(v);
                        }
                    }
                }
            }
            res++;
        }
    }
}