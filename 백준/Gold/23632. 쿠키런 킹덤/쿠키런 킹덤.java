import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] in = new int[N + 1];
        boolean[] R = new boolean[N + 1];
        boolean[] V = new boolean[N + 1];
        for (int m = 0; m < M; m++)
            V[Integer.parseInt(st.nextToken())] = true;
        List<Integer>[] resList = new ArrayList[N + 1];
        List<Integer>[] reqList = new ArrayList[N + 1];
        Arrays.setAll(resList, v -> new ArrayList<>());
        Arrays.setAll(reqList, v -> new ArrayList<>());
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            if (V[n])
                for (int i = 0; i < size; i++)
                    R[Integer.parseInt(st.nextToken())] = true;
            else
                for (int i = 0; i < size; i++)
                    resList[n].add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < N - M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0, size = Integer.parseInt(st.nextToken()); j < size; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (R[v])
                    continue;
                reqList[v].add(n);
                in[n]++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int n = 1; n <= N; n++) {
            if (!V[n] && in[n] == 0) {
                q.offer(n);
            }
        }
        for (int t = 0; t < T; t++) {
            for (int i = 0, size = q.size(); i < size; i++) {
                int v = q.poll();
                V[v] = true;
                for (int res : resList[v]) {
                    if (R[res])
                        continue;
                    R[res] = true;
                    for (int req : reqList[res])
                        if (--in[req] == 0)
                            q.offer(req);
                }
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int n = 1; n <= N; n++) {
            if (V[n]) {
                cnt++;
                sb.append(n).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}