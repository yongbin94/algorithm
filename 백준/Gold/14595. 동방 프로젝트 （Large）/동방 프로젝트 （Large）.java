import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if (M == 0) {
            System.out.println(N);
            return;
        }
        
        int[][] A = new int[M][2];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, (a, b) -> a[0] - b[0]);

        int res = 0;
        int currS = A[0][0];
        int currE = A[0][1];

        for (int i = 1; i < M; i++) {
            int nextS = A[i][0];
            int nextE = A[i][1];

            if (nextS <= currE) {
                currE = Math.max(currE, nextE);
            } else {
                res += (currE - currS);
                currS = nextS;
                currE = nextE;
            }
        }
        res += (currE - currS);

        System.out.println(N - res);
    }
}