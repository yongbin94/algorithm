import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        Arrays.fill(A, Integer.MAX_VALUE);
        A[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            int i = Arrays.binarySearch(A, v);
            if (i >= 0) continue;
            i = -i - 1;
            A[i] = v;
            answer = Math.max(answer, i);
        }
        System.out.println(answer);
    }
}
