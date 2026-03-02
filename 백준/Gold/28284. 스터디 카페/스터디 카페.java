import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(A);
        
        long[] min = new long[N + 1];
        long[] max = new long[N + 1];
        for (int i = 0; i < N; i++) {
            min[i + 1] = min[i] + A[i];
            max[i + 1] = max[i] + A[N - 1 - i];
        }
        
        List<Info> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Info(s, 1));
            list.add(new Info(e + 1, -1));
        }
        
        Collections.sort(list);
        
        long minTotal = 0;
        long maxTotal = 0;
        int curr = 0;
        
        for (int i = 0; i < list.size() - 1; i++) {
            curr += list.get(i).w;
            long d = list.get(i + 1).t - list.get(i).t;
            if (curr > 0 && d > 0) {
                minTotal += d * min[curr];
                maxTotal += d * max[curr];
            }
        }
        
        System.out.println(minTotal + " " + maxTotal);
    }
    
    static class Info implements Comparable<Info> {
        int t, w;
        Info(int t, int w) {
            this.t = t;
            this.w = w;
        }
        @Override
        public int compareTo(Info o) {
            return this.t - o.t;
        }
    }
}