import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        int answer = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 'B') q.add(i);
            else if (A[i] == 'C' && !q.isEmpty()) {
                q.poll();
                answer++;
            }
        }
        for (int i = 0; i < A.length && !q.isEmpty(); i++) {
            if (A[i] != 'A') continue;
            while (!q.isEmpty() && q.peek() < i) q.poll();
            if (q.isEmpty()) break;
            q.poll();
            answer++;
        }
        System.out.println(answer);
    }
}