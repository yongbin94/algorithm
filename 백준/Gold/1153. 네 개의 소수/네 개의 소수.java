import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N + 1];
        List<Integer> primeList = new ArrayList<>();
        Set<Integer> primeSet = new HashSet<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i])
                continue;
            primeList.add(i);
            primeSet.add(i);
            for (int j = i; j <= N; j += i) {
                isPrime[j] = true;
            }
        }
        int M = primeList.size();
        for (int i = 0; i < M; i++) {
            for (int j = i; j < M; j++) {
                for (int k = j; k < M; k++) {
                    int sum = primeList.get(i) + primeList.get(j) + primeList.get(k);
                    int value = N - sum;
                    if (primeSet.contains(value)) {
                        if (primeSet.contains(value)) {
                            List<Integer> answer = Arrays.asList(primeList.get(i), primeList.get(j), primeList.get(k), value);
                            Collections.sort(answer);
                            System.out.println(answer.get(0) + " " + answer.get(1) + " " + answer.get(2) + " " + answer.get(3));
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}