import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            arr[n] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        int answer = 0;
        long max = 0;
        list.add(A % 2 == 1 ? A : A + 1);
        list.add(B % 2 == 1 ? B : B - 1);
        for (int v : list) {
            int tmp = Integer.MAX_VALUE;
            for (int a : arr)
                tmp = Math.min(tmp, Math.abs(v - a));
            if (max < tmp) {
                answer = v;
                max = tmp;
            }
        }
        for (int n = 1; n < N; n++) {
            int a = (arr[n] - arr[n - 1]) / 2;
            if (a % 2 == 0)
                a--;
            if (max < a) {
                int v = arr[n - 1] + a;
                if (v > A && v < B) {
                    answer = v;
                    max = a;
                }
            }
        }
        System.out.println(answer);
    }
}