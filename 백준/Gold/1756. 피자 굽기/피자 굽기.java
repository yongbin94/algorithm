import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens())
            stack.push(Math.min(stack.isEmpty() ? Integer.MAX_VALUE : stack.peek(), Integer.parseInt(st.nextToken())));
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()) {
                if(stack.pop() >= v) {
                    if(--N == 0) {
                        System.out.println(stack.size() + 1);
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(0);
    }
}