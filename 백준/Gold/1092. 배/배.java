import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] cnt = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(A);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int v = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N; i++) {
                if (A[i] >= v) {
                    cnt[i]++;
                    break;
                }
                if(i == N - 1) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        int time = 0;
        int tmp = 0;
        for (int i = N - 1; i >= 0; i--) {
            tmp += time;
            while (cnt[i] > tmp) {
                tmp += N - i;
                time++;
            }
            tmp -= cnt[i];
        }
        System.out.println(time);
    }
}