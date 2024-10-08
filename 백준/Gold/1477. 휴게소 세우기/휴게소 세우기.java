import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        A[N] = L;
        Arrays.sort(A);

        int left = 1;
        int right = L;
        int answer = 0;
        outer:
        while (left <= right) {
            int mid = (right + left) / 2;
            int curr = 0;
            int count = 0;
            for (int a : A) {
                while (curr + mid < a) {
                    curr += mid;
                    if (++count > M) {
                        left = mid + 1;
                        continue outer;
                    }
                }
                curr = a;
            }
            right = mid - 1;
            answer = mid;
        }
        System.out.println(answer);
    }
}