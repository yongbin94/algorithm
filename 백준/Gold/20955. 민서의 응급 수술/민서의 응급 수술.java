import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        int answer = 0;
        for(int n = 1; n <= N; n++)
            parent[n] = n;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if(!union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())))
                answer++;
        }
        for(int i = 1; i <= N; i++)
            if(union(1, i))
                answer++;
        System.out.println(answer);

    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if( a == b )
            return false;
        parent[b] = a;
        return true;
    }

    private static int find(int a) {
        if(a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }
}
