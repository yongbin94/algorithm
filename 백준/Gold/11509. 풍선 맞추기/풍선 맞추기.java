import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[1_000_001];
        int cnt = 0;
        while (N-- > 0) {
            int h = Integer.parseInt(st.nextToken());
            if (A[h] > 0) {
                A[h]--;
            } else {
                cnt++;
            }
            A[h - 1]++;
        }
        System.out.println(cnt);
    }
}