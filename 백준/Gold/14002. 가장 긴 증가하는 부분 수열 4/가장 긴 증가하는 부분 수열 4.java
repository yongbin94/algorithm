import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Point[] A = new Point[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Point> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++)
            q.offer(new Point(Integer.parseInt(st.nextToken()),i));
        int cnt = 0;
        while(!q.isEmpty()) {
            cnt++;
            int prev = Integer.MAX_VALUE;
            for(int i = 0, size = q.size(); i <size; i++) {
                Point p = q.poll();
                if(prev >= p.x) {
                    prev = p.x;
                    A[p.y] = new Point(cnt, p.x);
                    continue;
                }
                q.offer(p);
            }
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = N - 1; i >= 0; i--) {
            if(A[i].x == cnt) {
                stack.push(A[i].y);
                cnt--;
            }
        }
        System.out.println(stack.size());
        while(!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb);
    }
}
