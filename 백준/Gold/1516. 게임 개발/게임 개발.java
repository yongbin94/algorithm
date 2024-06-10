import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[N + 1];
        int[] in = new int[N + 1];
        int[] time = new int[N + 1];
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        for (int s = 1; s <= N; s++) {
            st = new StringTokenizer(br.readLine());
            time[s] = Integer.parseInt(st.nextToken());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1)
                    break;
                list[e].add(s);
                in[s]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 0, size = list[v].size(); i < size; i++) {
                int e = list[v].get(i);
                result[e] = Math.max(result[e], result[v] + time[v]);
                if (--in[e] == 0)
                    q.offer(e);
            }
        }

        for (int i = 1; i <= N; i++)
            System.out.println(result[i] + time[i]);
    }
}
