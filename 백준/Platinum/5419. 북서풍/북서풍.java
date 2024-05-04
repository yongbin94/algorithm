import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Point> input = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.x, o2.x));
            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                input.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o2.y == o1.y ? Integer.compare(o1.x, o2.x) : Integer.compare(o2.y, o1.y));

            int value = Integer.MIN_VALUE, index = 0;
            ;
            while (!input.isEmpty()) {
                Point p = input.poll();
                pq.offer(new Point(p.x == value ? index : ++index, p.y));
                value = p.x;
            }
            int treeSize = 1;
            while (treeSize <= index * 2)
                treeSize <<= 1;
            tree = new int[treeSize + 1];
            int startIndex = treeSize / 2 - 1;
            long answer = 0;
            while (!pq.isEmpty()) {
                Point p = pq.poll();
                answer += select(startIndex + 1, startIndex + p.x);
                update(startIndex + p.x);
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int select(int i, int j) {
        int result = 0;
        while (i <= j) {
            if (i % 2 == 1)
                result +=  tree[i++];
            if (j % 2 == 0)
                result += tree[j--];
            i /= 2;
            j /= 2;
        }
        return result;
    }

    private static void update(int i) {
        while (i > 0) {
            tree[i]++;
            i /= 2;
        }
    }
}
