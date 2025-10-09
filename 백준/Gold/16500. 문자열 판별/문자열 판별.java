import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] A = new String[N];
        for (int n = 0; n < N; n++) {
            A[n] = br.readLine();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean[] B = new boolean[S.length() + 1];
        pq.offer(0);
        while (!pq.isEmpty()) {
            int i = pq.poll();
            if (i == S.length()) {
                System.out.println(1);
                return;
            }
            a: for (String a : A) {
                int l = i + a.length();
                if (l > S.length() || B[l]) continue;
                for (int j = 0; j < a.length(); j++) {
                    if (S.charAt(i + j) != a.charAt(j)) continue a;
                }
                pq.offer(l);
                B[l] = true;
            }
        }
        System.out.println(0);
    }
}