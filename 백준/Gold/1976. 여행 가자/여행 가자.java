import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int parents[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        br.readLine();
        parents = new int[N+1];
        for(int i = 1; i <= N; i++)
            parents[i] = i;
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m <= N; m++) {
                if(Integer.parseInt(st.nextToken()) == 1)
                    union(n, m);
            }
        }
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            int curr = Integer.parseInt(st.nextToken());
            if (union(prev, curr)){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot)
            return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int a) {
        if(parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }
}
