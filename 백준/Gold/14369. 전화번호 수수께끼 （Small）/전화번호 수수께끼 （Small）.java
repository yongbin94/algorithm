import java.io.*;
import java.util.*;

public class Main {
    static int[] A, N;
    static int[][] C = {{'Z', 0}, {'G', 8}, {'X', 6}, {'W', 2}, {'U', 4}, {'F', 5}, {'R', 3}, {'V', 7}, {'O', 1}, {'N', 9}};
    static String[] S = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            A = new int[26];
            N = new int[10];
            String input = br.readLine();
            for (char c : input.toCharArray())
                A[c - 'A']++;
            for (int[] a : C) {
                while (A[a[0] - 'A'] > 0) {
                    N[a[1]]++;
                    for (char c : S[a[1]].toCharArray())
                        A[c - 'A']--;
                }
            }
            sb.append("Case #").append(tc).append(": ");
            for (int i = 0; i < 10; i++)
                while (N[i]-- > 0)
                    sb.append(i);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
