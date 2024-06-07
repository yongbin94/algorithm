import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine()), n = 0, result = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(n++ < N)
            pq.offer(Integer.parseInt(br.readLine()));
        while(pq.size() != 1){
            int sum = pq.poll() + pq.poll();
            result += sum;
            pq.offer(sum);
        }
        System.out.println(result);
    }
}
