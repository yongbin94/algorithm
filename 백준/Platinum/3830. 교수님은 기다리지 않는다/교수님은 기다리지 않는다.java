import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] L;
    static int[] W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0) {
                System.out.println(sb);
                return;
            }
            L = new ArrayList[N + 1];
            W = new int[N + 1];
            Arrays.setAll(L, n -> new ArrayList<>(List.of(n)));
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (cmd == '!') {
                    List<Integer> A = L[a];
                    List<Integer> B = L[b];
                    if (A.equals(B)) continue;
                    int w = Integer.parseInt(st.nextToken()) + W[a] - W[b];
                    if (A.size() < B.size()) {
                        List<Integer> tmp = A;
                        A = B;
                        B = tmp;
                        w *= -1;
                    }
                    for (int i : B) {
                        W[i] += w;
                        L[i] = A;
                    }
                    A.addAll(B);
                    B.clear();
                } else {
                    sb.append(!L[a].equals(L[b]) ? "UNKNOWN" : W[b] - W[a]).append("\n");
                }
            }
        }
    }
}