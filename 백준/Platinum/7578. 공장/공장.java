import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int treeSize = 1;
        while(treeSize < N * 2 + 1)
            treeSize <<= 1;
        tree = new int[treeSize];
        int startIndex = treeSize / 2;
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++)
            map.put(Integer.parseInt(st.nextToken()), i);

        long answer = 0;
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int i = map.get(Integer.parseInt(st.nextToken()));
            answer += select(startIndex + i, treeSize - 1);
            update(startIndex + i);
        }
        System.out.println(answer);
    }

    private static int select(int i, int j) {
        int sum = 0;
        while(i <= j) {
            if(i % 2 == 1)
                sum += tree[i++];
            if(j % 2 == 0)
                sum += tree[j--];
            i /= 2;
            j /= 2;
        }
        return sum;
    }

    private static void update(int i) {
        while(i > 0) {
            tree[i]++;
            i /= 2;
        }
    }
}