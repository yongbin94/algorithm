import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        A = new int[N];
        for(int n = 0; n < N; n++)
            parent[n] = n;
        while(M-- > 0) {
            st =  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a,b);
        }
        for(int n = 0; n < N; n++)
            find(n);
        System.out.println((Arrays.stream(parent).distinct().count() > 1 || Arrays.stream(A).filter(v -> v % 2 == 1).limit(3).count() % 2 != 0) ? "NO" : "YES");
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        A[a]++;
        A[b]++;
        if(rootA != rootB)
            parent[rootB] = rootA;
    }

    private static int find(int a) {
        if(parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
}