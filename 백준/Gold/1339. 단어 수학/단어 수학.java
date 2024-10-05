import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] A = new long[26];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int i = 0; i < input.length(); i++) {
                int idx = input.charAt(i) - 'A';
                long value = 1;
                for (int j = i + 1; j < input.length(); j++)
                    value *= 10;
                A[idx] += value;
            }
        }
        Arrays.sort(A);
        long answer = 0;
        for (int i = 0; i < 10; i++)
            answer += A[25 - i] * (9 - i);
        System.out.println(answer);
    }
}