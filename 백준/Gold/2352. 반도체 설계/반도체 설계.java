import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int treeSize = 1;
        while(treeSize <= N * 2)
            treeSize <<= 1;
        tree = new int[treeSize];
        int startIndex = treeSize / 2;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken()) - 1;
            int value = select(startIndex, startIndex + i - 1);
            update(startIndex + i, value + 1);
        }
        System.out.println(select(startIndex, startIndex + N));
    }
    
    private static int select(int i, int j) {
        int res = 0;
        while(i <= j) {
            if(i % 2 == 1)
                res = Math.max(res, tree[i++]);
            if(j % 2 == 0)
                res = Math.max(res, tree[j--]);
            i /= 2;
            j /= 2;
        }
        return res;
    }

    private static void update(int index, int v) {
        while(index > 0) {
            tree[index] = Math.max(tree[index], v);
            index /= 2;
        }
    }
}
