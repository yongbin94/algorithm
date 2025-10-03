import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            String input = br.readLine();
            int[] A = new int[128];
            int l = 0, r = 0, cnt = 0, res = 0;
            while (r < input.length()) {
                int i = input.charAt(r++);
                if (A[i]++ == 0) cnt++;
                while (cnt > N) {
                    int j = input.charAt(l++);
                    if (--A[j] == 0) cnt--;
                }
                res = Math.max(res, r - l);
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
