import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M, G;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        M = sc.nextInt();
        G = sc.nextInt();
        int answer = INF;
        answer = Math.min(answer, solution(a, b));
        answer = Math.min(answer, solution(b, a));
        System.out.println(answer == INF ? -1 : answer);
    }

    private static int solution(int a, int b) {
        int res = 0;
        if (M % 2 == a % 2)
            return INF;
        int min = Math.min(a, Math.min(M, G));
        int max = Math.max(a, Math.max(M, G));
        if (b >= min && b <= max) {
            if (b < a) {
                if (min == 1 || (b >= M && (M % 2 != b % 2)))
                    return INF;
                res += b - min + 1;
            } else {
                if (max == N || (b <= M && (M % 2 != b % 2)))
                    return INF;
                res += max - b + 1;
            }
        }
        res += Math.abs(a - M);
        res += Math.abs(M - G);
        return res;
    }
}