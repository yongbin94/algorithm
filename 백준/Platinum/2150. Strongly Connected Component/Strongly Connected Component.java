import java.io.*;
import java.util.*;

public class Main {
    static int N, M, num;
    static List<Integer>[] edges, reverse;
    static PriorityQueue<int[]> pq;
    static TreeMap<Integer, String> treeMap;
    static boolean[] visited;
    static List<Integer> tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        reverse = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        treeMap = new TreeMap<>();
        num = 1;
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            reverse[b].add(a);
        }
        for (int n = 1; n <= N; n++)
            if(!visited[n])
                dfs1(n);
        visited = new boolean[N + 1];
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            if(visited[arr[1]])
                continue;
            tmp = new ArrayList<>();
            dfs2(arr[1]);
            tmp.sort((o1, o2) -> o1 - o2);
            StringBuilder sb = new StringBuilder();
            for(int i : tmp)
                sb.append(i).append(" ");
            sb.append("-1\n");
            treeMap.put(tmp.get(0), sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(treeMap.size()).append("\n");
        while(!treeMap.isEmpty())
            sb.append(treeMap.pollFirstEntry().getValue());
        System.out.println(sb);
    }

    private static void dfs2(int n) {
        visited[n] = true;
        tmp.add(n);
        for(int edge : reverse[n]) {
            if(visited[edge])
                continue;
            dfs2(edge);
        }
    }

    private static void dfs1(int n) {
        visited[n] = true;
        List<Integer> list = edges[n];
        list.sort((o1, o2) -> o2 - o1);
        for (int edge : list) {
            if (visited[edge])
                continue;
            dfs1(edge);
        }
        pq.offer(new int[]{num++, n});
    }
}