import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Set<Integer>[] edges;
    static int[] out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new HashSet[N + 1];
        Arrays.setAll(edges, v -> new HashSet<>());
        out = new int[N + 1];

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
            out[a]++;
            out[b]++;
        }
        edges[0].add(1);
        out[0]++;
        out[1]++;

        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            while (out[curr] == 0) {
                curr = stack.pop();
            }
            if (!edges[curr].contains(next)) {
                System.out.println(0);
                return;
            }
            out[curr]--;
            out[next]--;
            stack.push(curr);
            curr = next;
        }
        System.out.println(1);
    }
}