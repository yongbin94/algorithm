import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Point[] nodeArr = new Point[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeArr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        boolean[] visited = new boolean[N + 1];
        double[] minEdge = new double[N + 1];
        Arrays.fill(minEdge, Double.MAX_VALUE);
        List<Integer>[] edgeList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            edgeList[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edgeList[s].add(e);
            edgeList[e].add(s);
        }

        double result = 0;
        int cnt = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(visited[edge.e])
                continue;
            visited[edge.e] = true;
            result += edge.w;
            if(++cnt == N)
                break;
            Point nodeA = nodeArr[edge.e];
            for(int e : edgeList[edge.e])
                if(!visited[e]){
                    minEdge[e] = 0;
                    pq.offer(new Edge(e, 0));
                }
            for (int i = 2; i <= N; i++) {
                if(visited[i] || edge.e == i)
                    continue;
                Point nodeB = nodeArr[i];
                double distance = Math.sqrt(Math.pow(nodeA.x - nodeB.x, 2) + Math.pow(nodeA.y - nodeB.y, 2));
                if(minEdge[i] <= distance)
                    continue;
                minEdge[i] = distance;
                pq.offer(new Edge(i, distance));
            }

        }
        System.out.printf("%.2f", Math.round(result * 100) / 100.0);
    }

    static class Edge implements Comparable<Edge> {
        int e;
        double w;

        public Edge(int e, double w) {
            super();
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}