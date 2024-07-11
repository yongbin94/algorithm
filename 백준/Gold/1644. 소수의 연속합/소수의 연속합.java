import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        boolean[] D = new boolean[N + 1];
        for (int i = 2; i <= Math.sqrt(N); i++) {
            int cnt = 2;
            while (i * cnt <= N)
                D[i * cnt++] = true;
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(int i = 2; i <= N; i++)
            if(!D[i])
                list.add(i + list.get(list.size() - 1));
        int i = 0, j = 0, answer = 0;
        while(j < list.size()) {
            if(i == j) {
                j++;
                continue;
            }
            int v = list.get(j) - list.get(i);
            if(v <= N) {
                if(v == N)
                    answer++;
                j++;
            }
            else
                i++;
        }
        System.out.println(answer);
    }
}