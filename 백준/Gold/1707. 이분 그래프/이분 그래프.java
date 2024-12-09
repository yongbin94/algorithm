import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean visited[];
    static boolean isEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()), tc = 0;
        while (tc++ < T) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            check = new int[V + 1];
            isEven = true;
            for (int i = 1; i <= V; i++)
                A[i] = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                A[s].add(e);
                A[e].add(s);
            }
            for (int i = 1; i <= V; i++) {
                if (isEven)
                    dfs(i);
                else break;
            }
            check[1] = 0;
            System.out.println(isEven ? "YES" : "NO");
        }
    }

    private static void dfs(int node) {
        visited[node] = true;
        for(int i : A[node]){
            if(!visited[i]){
                check[i] = (check[node] + 1) % 2;
                dfs(i);
            } else if(check[node] == check[i]){
                isEven = false;
            }
        }
    }
}
