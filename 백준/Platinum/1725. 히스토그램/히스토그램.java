import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long max = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(-1, 0));
        for (int i = 0; i < N; i++) {
            int x = i;
            int y = Integer.parseInt(br.readLine());
            while (stack.peek().y > y) {
                Point p = stack.pop();
                x = p.x;
                max = Math.max(max, (long) (i - p.x) * p.y);
            }
            if (stack.peek().y != y)
                stack.push(new Point(x, y));
        }
        while (stack.peek().x >= 0) {
            Point p = stack.pop();
            max = Math.max(max, (long) (N - p.x) * p.y);
        }
        System.out.println(max);
    }
}
