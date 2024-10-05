import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N+1];
        boolean[] earlyadopter = new boolean[N+1];
        int[] in = new int[N+1];
        List<Integer>[] list = new ArrayList[N+1];
        for(int i = 1 ; i <= N; i++)
            list[i] = new ArrayList<>();

        for(int n  = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            in[s]++;
            in[e]++;
            list[e].add(s);
            list[s].add(e);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            if(in[i] == 1) {
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int s = q.poll();
            visited[s] = true;
            for(int i : list[s]){
                if(!visited[i]){
                    if(!earlyadopter[s])
                        earlyadopter[i] = true;
                    if(--in[i] == 1){
                        q.offer(i);
                    }
                }
            }
        }
        int result = 0;
        for(int n = 1; n <= N; n++)
            if(earlyadopter[n])
                result++;
        System.out.println(result);
    }
}
