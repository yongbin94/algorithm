import java.io.*;

public class Main {
	static long[] L = new long[41];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        L[1] = 5;
        L[2] = 13;
        int n = 2;
        while (L[n] < M) {
            n++;
            L[n] = L[n - 1] + 1 + L[n - 2];
        }

        recur(n, M);
    }

    private static void recur(int n, int m) {
        if (n == 1) {
            char c = "Messi".charAt(m - 1);
            System.out.println(c == ' ' ? "Messi Messi Gimossi" : c);
            return;
        }
        if (n == 2) {
            char c = "Messi Gimossi".charAt(m - 1);
            System.out.println(c == ' ' ? "Messi Messi Gimossi" : c);
            return;
        }

        long prev = L[n - 1];

        if (m <= prev) {
            recur(n - 1, m);
        } else if (m == prev + 1) {
            System.out.println("Messi Messi Gimossi");
        } else {
            recur(n - 2, (int) (m - (prev + 1)));
        }
    }
}
