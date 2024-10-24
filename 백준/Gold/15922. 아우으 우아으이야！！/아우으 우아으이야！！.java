import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int i, j = Integer.MIN_VALUE;
        int answer = 0;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp > j) {
                answer += tmp - Math.max(i, j);
                j = tmp;
            }
        }
        System.out.println(answer);
    }
}
