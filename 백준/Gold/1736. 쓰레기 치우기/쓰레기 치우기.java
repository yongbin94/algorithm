import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        TreeSet<Integer> ts = new TreeSet<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                if (st.nextToken().charAt(0) != '1') continue;
                if (ts.floor(m) != null) ts.remove(ts.floor(m));
                ts.add(m);
            }
        }
        System.out.println(ts.size());
    }
}