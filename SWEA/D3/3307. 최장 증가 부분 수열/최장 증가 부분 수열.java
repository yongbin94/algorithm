import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()), tc = 0;
        while(tc++ < T) {
            br.readLine();
            Queue<Integer> q = new ArrayDeque<>();
            Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(q::offer);
            int result = 0;
            while(!q.isEmpty()) {
                int prev = Integer.MAX_VALUE;
                for(int i = 0, size = q.size(); i < size; i++){
                    int v = q.poll();
                    prev = Math.min(v, prev);
                    if(prev != v)
                        q.offer(v);
                }
                result++;
            }
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
