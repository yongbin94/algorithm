import java.io.*;
import java.util.*;

public class Main {
    static int N, I;
    static int[] A;
    static boolean[] V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(br.readLine());
        V = new boolean[N + 1];
        for (I = 1; I <= N; I++)
            recur(I);
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)
            if (V[i])
                q.offer(i);
        StringBuilder sb = new StringBuilder();
        sb.append(q.size()).append("\n");
        while (!q.isEmpty())
            sb.append(q.poll()).append("\n");
        System.out.println(sb);
    }

    private static boolean recur(int i) {
        if (V[i])
            return false;
        V[i] = true;
        if (I == A[i])
            return true;
        if (recur(A[i]))
            return true;
        V[i] = false;
        return false;
    }
}