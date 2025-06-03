import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] A = {
            "###...#.###.###.#.#.###.###.###.###.###",
            "#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
            "#.#...#.###.###.###.###.###...#.###.###",
            "#.#...#.#.....#...#...#.#.#...#.#.#...#",
            "###...#.###.###...#.###.###...#.###.###"
    };
    static boolean[][] B;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        B = new boolean[N][10];
        selected = new int[N];
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int n = 0; n < N; n++) {
                        B[n][j] |= ((input.charAt(n * 4 + k) == '#') && (A[i].charAt(j * 4 + k) == '.'));
                    }
                }
            }
        }
        int[] C = new int[N];
        int[] S = new int[N];
        for (int n = 0; n < N; n++) {
            for (int i = 0; i < 10; i++) {
                if (B[n][i]) continue;
                C[n]++;
                S[n] += i;
            }
            if (C[n] == 0) {
                System.out.println(-1);
                return;
            }
        }
        int cnt = Arrays.stream(C).reduce(1, (a, b) -> a * b);
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += S[i] * ((long) Math.pow(10, N - 1 - i)) * (cnt / C[i]);
        }
        System.out.println(String.format("%.6f", (double) sum / cnt).replaceAll("0+$", "").replaceAll("\\.$", ".0"));
    }
}