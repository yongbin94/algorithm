import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            if (v == C) {
                System.out.println(1);
                return;
            }
            list.add(v);
        }
        Collections.sort(list);
        System.out.println(solution() ? 1 : 0);
    }

    private static boolean solution() {
        int i = 0, j = N - 1;
        while (i < j) {
            int v = list.get(i) + list.get(j);
            if (v > C)
                j--;
            else if (v < C) {
                int idx = list.indexOf(C - v);
                if (idx >= 0 && idx != i && idx != j)
                    return true;
                i++;
            } else
                return true;

        }
        return false;
    }
}