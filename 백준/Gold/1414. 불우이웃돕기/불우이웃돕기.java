import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Edge> edgeList = new ArrayList<>();
        int result = 0;
        for(int n = 0; n < N; n++){
            char[] cArr = br.readLine().toCharArray();
            for(int m = 0; m < N; m++){
                if(cArr[m] == '0')
                    continue;
                int w = cArr[m] >= 'a' ? cArr[m] - 'a' + 1 : cArr[m] - 'A' + 27;
                result += w;
                edgeList.add(new Edge(n, m, w));
            }
        }
        Collections.sort(edgeList);

        parents = new int[N];
        for(int i = 0; i < N; i++)
            parents[i] = i;

        int cnt = 0;
        for(Edge edge : edgeList){
            if(!union(edge.from, edge.to))
                continue;
            result -= edge.weight;
            if(++cnt == N - 1)
                break;
        }
        if(cnt != N - 1)
            result = -1;
        System.out.println(result);
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
