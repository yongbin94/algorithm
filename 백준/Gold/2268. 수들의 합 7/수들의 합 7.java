import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static int startIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (1 << (treeHeight + 1));
        startIndex = treeSize / 2;
        tree = new long[treeSize];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0) {
                if (b > c) {
                    int temp = b;
                    b = c;
                    c = temp;
                }
                sb.append(select(startIndex + b - 1, startIndex + c - 1)).append("\n");
            } else {
                update(startIndex + b - 1, c);
            }
        }
        System.out.println(sb);
    }

    private static long select(int i, int j) {
        long sum = 0;
        while (i <= j) {
            if (i % 2 == 1) 
                sum += tree[i++];
            if (j % 2 == 0) 
                sum += tree[j--];
            i /= 2;
            j /= 2;
        }
        return sum;
    }

    private static void update(int i, long k) {
        long diff = k - tree[i];
        while (i > 0) {
            tree[i] += diff;
            i /= 2;
        }
    }
}