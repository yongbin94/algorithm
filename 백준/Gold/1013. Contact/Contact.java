import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            sb.append(solution(br.readLine()) ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }

    private static boolean solution(String S) {
        int L = S.length();
        for (int i = 0; i < L; ) {
            if (S.charAt(i) == '0') {
                if (L > i + 1 && S.charAt(i + 1) == '1') i += 2;
                else return false;
            } else {
                if (L <= i + 2 || S.charAt(i + 1) == '1' || S.charAt(i + 2) == '1') return false;
                else {
                    i += 2;
                    while (S.charAt(i) == '0') if (L <= ++i) return false;
                    if (L <= ++i) break;
                    while (S.charAt(i) == '1') {
                        if (L > i + 3 && S.charAt(i + 1) == '0' && S.charAt(i + 2) == '0') break;
                        if (L <= ++i) break;
                    }
                }
            }
        }
        return true;
    }
}