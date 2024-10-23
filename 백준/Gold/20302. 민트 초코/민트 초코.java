import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] isPrime;
    static List<Integer> primeList;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[100_001];
        isPrime = new boolean[100_001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        primeList = new ArrayList<>();
        for (int i = 2; i <= 50_000; i++) {
            if (isPrime[i]) {
                primeList.add(i);
                int tmp = i * 2;
                while (tmp <= 100_000) {
                    isPrime[tmp] = false;
                    tmp += i;
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        A[v < 0 ? v * -1 : v]++;
        while (st.hasMoreTokens()) {
            boolean flag = st.nextToken().charAt(0) == '*';
            v = Integer.parseInt(st.nextToken());
            A[v < 0 ? v * -1 : v] += flag ? 1 : -1;
        }

        if(A[0] > 0){
            System.out.println("mint chocolate");
            return;
        }
        
        for (int i = 100_000; i >= 0; i--) {
            if (A[i] == 0)
                continue;
            if (isPrime[i] && A[i] < 0) {
                System.out.println("toothpaste");
                return;
            }
            for (int prime : primeList) {
                if (i % prime == 0) {
                    A[prime] += A[i];
                    A[i / prime] += A[i];
                    A[i] = 0;
                    break;
                }
            }
        }
        System.out.println("mint chocolate");
    }
}