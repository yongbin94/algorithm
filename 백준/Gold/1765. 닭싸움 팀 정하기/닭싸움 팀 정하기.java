import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] T;
    static List<Integer>[] E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        E = new ArrayList[N + 1];
        for (int n = 1; n <= N; n++) {
            T[n] = n;
            E[n] = new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            boolean isFriend = st.nextToken().charAt(0) == 'F';
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (isFriend)
                union(a, b);
            else {
                E[a].add(b);
                E[b].add(a);
            }
        }
        for (int n = 1; n <= N; n++) {
            for(int i = 0; i < E[n].size(); i++) {
                int e = E[n].get(i);
                for(int j : E[e])
                    union(n, j);
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= N; i++)
            set.add(find(i));
        System.out.println(set.size());
    }

    private static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        T[B] = A;
    }

    private static int find(int a) {
        if (a == T[a])
            return a;
        return T[a] = find(T[a]);
    }
}