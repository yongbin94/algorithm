import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while(st.hasMoreTokens()) {
            A[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }
        Arrays.sort(A);
        int count = 0;
        for(int n = 0; n < N; n++) {
            int left = 0;
            int right = N - 1;
            while(left < right) {
                int sum = A[left] + A[right];
                if(A[n] == sum) {
                    if(left == n) {
                        left++;
                        continue;
                    } else if(right == n) {
                        right--;
                        continue;
                    }
                    count++;
                    break;
                } else if(A[n] > sum) {
                    left++;
                } else
                    right--;
            }
        }
        System.out.println(count);
    }
}
