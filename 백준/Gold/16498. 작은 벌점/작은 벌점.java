import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int[] A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        answer = Integer.MAX_VALUE;
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        Arrays.sort(C);
        for (int a : A) {
            for (int b : B) {
                int min = Math.min(a, b);
                int max = Math.max(a, b);
                int i = Arrays.binarySearch(C, min);
                if (i >= 0) answer = Math.min(answer, max - min);
                else {
                    i = -i - 1;
                    if (i < C.length) answer = Math.min(answer, Math.max(max, C[i]) - Math.min(min, C[i]));
                    if (i - 1 >= 0) answer = Math.min(answer, Math.max(max, C[i - 1]) - Math.min(min, C[i - 1]));
                }
            }
        }
    }
}