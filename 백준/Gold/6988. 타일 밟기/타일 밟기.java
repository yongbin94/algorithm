import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] hasTile = new boolean[INF];
        int[] tiles = new int[N];
        for (int n = 0; n < N; n++) {
            int v = Integer.parseInt(st.nextToken());
            tiles[n] = v;
            hasTile[v] = true;
        }
        long answer = 0;
        for (int n = 0; n < N; n++) {
            for (int m = n + 1; m < N; m++) {
                int curr = tiles[n];
                int diff = tiles[m] - curr;
                if (curr - diff > 0 && hasTile[curr - diff]) continue;
                long sum = curr;
                int i = 1;
                while (curr + diff * i < INF && hasTile[curr + diff * i]) {
                    sum += curr + diff * i++;
                }
                if (i > 2) answer = Math.max(answer, sum);
            }
        }
        System.out.println(answer);
    }
}