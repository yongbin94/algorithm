import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] E;
    static boolean[] V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = new ArrayList[N];
        V = new boolean[N];
        Arrays.setAll(E, e -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            E[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }
        br.readLine();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens())
            V[Integer.parseInt(st.nextToken()) - 1] = true;
        System.out.println(!V[0] && recur(0) ? "yes" : "Yes");

    }

    private static boolean recur(int i) {
        V[i] = true;
        if (E[i].isEmpty())
            return true;
        for (int n : E[i])
            if (!V[n])
                if(recur(n))
                    return true;
        return false;
    }
}