import java.io.*;
import java.util.*;

public class Main {
    static int[] A;
    static Info[] B;
    static final int[] C = {1, 5, 10, 25};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        A = new int[4];
        B = new Info[X + 1];
        for (int x = 0; x <= X; x++)
            B[x] = new Info(-1, 0, 0, 0);
        B[0].a = 0;
        for (int i = 0; i < 4; i++)
            A[i] = Integer.parseInt(st.nextToken());
        for (int a = 0; a < 4; a++) {
            int c = C[a];
            for (int x = X; x >= 0; x--) {
                for (int i = 1; i <= A[a]; i++) {
                    if (x + i * c > X)
                        break;
                    if (B[x].getCnt() == -1)
                        continue;
                    if (B[x + i * c].getCnt() < B[x].getCnt() + i) {
                        B[x + i * c].a = B[x].a + (a == 0 ? i : 0);
                        B[x + i * c].b = B[x].b + (a == 1 ? i : 0);
                        B[x + i * c].c = B[x].c + (a == 2 ? i : 0);
                        B[x + i * c].d = B[x].d + (a == 3 ? i : 0);
                    }
                }
            }
        }
        System.out.printf("%s %s %s %s%n", B[X].a == -1 ? 0 : B[X].a, B[X].b, B[X].c, B[X].d);
    }

    private static class Info {
        int a, b, c, d;

        public Info(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public int getCnt() {
            return a + b + c + d;
        }
    }
}