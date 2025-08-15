import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long A;
    static Room[] R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        R = new Room[N];
        for (int n = 0; n < N; n++) {
            R[n] = new Room(new StringTokenizer(br.readLine()));
        }
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 1, r = Long.MAX_VALUE;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (play(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean play(long H) {
        long a = A;
        long h = H;
        for (Room r : R) {
            if (r.t) {
                h -= ((r.h - 1) / a) * r.a;
                if (h < 1) return false;
            } else {
                a += r.a;
                h = Math.min(H, h + r.h);
            }
        }
        return true;
    }

    private static class Room {
        boolean t;
        long a, h;

        public Room(StringTokenizer st) {
            t = st.nextToken().charAt(0) == '1';
            a = Long.parseLong(st.nextToken());
            h = Long.parseLong(st.nextToken());
        }
    }
}