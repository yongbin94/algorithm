import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Set<Integer>[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new HashSet[N + 1];
        for (int n = 1; n <= N; n++) {
            S[n] = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0, size = Integer.parseInt(st.nextToken()); i < size; i++) {
                S[n].add(Integer.parseInt(st.nextToken()));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().charAt(0) == '1') {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (S[a].size() < S[b].size()) {
                    S[0] = S[a];
                    S[a] = S[b];
                    S[b] = S[0];
                }
                S[a].addAll(S[b]);
                S[b].clear();
            } else {
                sb.append(S[Integer.parseInt(st.nextToken())].size()).append("\n");
            }
        }
        System.out.println(sb);
    }
}