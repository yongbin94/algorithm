import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, result;
    static int[][] A;
    static boolean[] selected;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][];
        if (K < 5) {
            System.out.println(0);
            return;
        }
        numbers = new int[K];
        selected = new boolean[26];
        for (char c : "acint".toCharArray())
            selected[c - 'a'] = true;
        K -= 5;
        for (int n = 0; n < N; n++)
            A[n] = br.readLine().chars().distinct().sorted().filter(v -> "acint".indexOf(v) == -1).map(v -> v - 'a').toArray();
        comb(0, 1);
        System.out.println(result);
    }

    private static void comb(int cnt, int start) {
        if(cnt == K){
            int sum = 0;
            outer: for(int n = 0; n < N; n++){
                for(int i = 0; i < A[n].length; i++)
                    if(!selected[A[n][i]])
                        continue outer;
                sum++;
            }
            result = Math.max(result, sum);
            return;
        }
        for(int i = start; i < 26; i++) {
            if(selected[i])
                continue;
            selected[i] = true;
            comb(cnt+1, i+1);
            selected[i] = false;
        }
    }
}
