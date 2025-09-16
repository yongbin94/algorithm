import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] A = new Point[N];
        for (int n = 0; n < N; n++) {
            A[n] = new Point(new StringTokenizer(br.readLine()));
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        if (cross(A[i], A[j], A[k])
                                || cross(A[j], A[k], A[l])
                                || cross(A[k], A[l], A[i])
                                || cross(A[l], A[i], A[j])) continue;
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer / 4);
    }

    private static boolean cross(Point a, Point b, Point c) {
        return (b.r - a.r) * (c.c - b.c) - (b.c - a.c) * (c.r - b.r) >= 0;
    }

    private static class Point {
        long r, c;

        public Point(StringTokenizer st) {
            r = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
        }
    }
}