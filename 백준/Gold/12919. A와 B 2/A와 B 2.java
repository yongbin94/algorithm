import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<String> q = new ArrayDeque<>();
        q.offer(br.readLine());
        String G = br.readLine();
        String R = new StringBuilder(G).reverse().toString();
        while(!q.isEmpty()) {
            String S = q.poll();
            String A = new StringBuilder(S).append("A").toString();
            String B = new StringBuilder(S).append("B").reverse().toString();
            if(G.length() == A.length()) {
                if(G.equals(A) || G.equals(B)) {
                    System.out.println(1);
                    return;
                }
            } else {
                if(G.contains(A) || R.contains(A))
                    q.offer(A);
                if(G.contains(B) || R.contains(B))
                    q.offer(B);
            }
        }
        System.out.println(0);
    }
}