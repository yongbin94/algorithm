import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int treeSize = 1;
        while (treeSize <= N * 2)
            treeSize <<= 1;
        int startIndex = treeSize / 2 - 1;
        tree = new int[treeSize + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int end = startIndex + Integer.parseInt(st.nextToken()) - 1;
            int max = select(startIndex, end);
            update(end, max + 1);
        }
        System.out.println(N - tree[1]);
    }

    private static int select(int i, int j) {
        int max = 0;
        while (i <= j) {
            if (i % 2 == 1) {
                max = Math.max(max, tree[i]);
                i++;
            }
            if (j % 2 == 0) {
                max = Math.max(max, tree[j]);
                j--;
            }
            i /= 2;
            j /= 2;
        }
        return max;
    }

    private static void update(int i, int max) {
        while (i > 0) {
            tree[i] = Math.max(tree[i], max);
            i /= 2;
        }
    }
}