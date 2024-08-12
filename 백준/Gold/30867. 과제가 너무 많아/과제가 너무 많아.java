import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        String S = br.readLine();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < L; i++) {
            char c = S.charAt(i);
            if (c == 'w') {
                if (cnt < N)
                    cnt++;
                else
                    sb.append('w');
                continue;
            }
            if (c != 'h') {
                while (cnt > 0) {
                    sb.append('w');
                    cnt--;
                }
            }
            sb.append(c);
        }
        while (cnt > 0) {
            sb.append('w');
            cnt--;
        }
        System.out.println(sb);
    }
}