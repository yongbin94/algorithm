import java.io.*;
import java.util.*;

public class Main {
    static long[][] A = new long[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int n = 0; n < 8; n++) {
            A[n] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        br.readLine().chars().forEach(ch -> play("URDL".indexOf(ch)));
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(A[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void play(int d) {
        if (d % 2 == 0) {
            for (int c = 0; c < 8; c++) {
                int limit = (d == 0 ? -1 : 8);
                for (int i = 0; i < 8; i++) {
                    int r = (d == 0 ? i : 7 - i);
                    if (A[r][c] == 0) continue;
                    int cr = r;
                    while (true) {
                        int nr = cr + (d == 0 ? -1 : +1);
                        if (nr < 0 || nr >= 8) break;
                        if (A[nr][c] == 0) {
                            A[nr][c] = A[cr][c];
                            A[cr][c] = 0;
                        } else if (A[nr][c] == A[cr][c]) {
                            if (nr != limit) {
                                A[nr][c] <<= 1;
                                A[cr][c] = 0;
                                limit = nr;
                            }
                            break;
                        } else {
                            break;
                        }
                        cr = nr;
                    }
                }
            }
        } else {
            for (int r = 0; r < 8; r++) {
                int limit = (d == 1 ? 8 : -1);
                for (int i = 0; i < 8; i++) {
                    int c = (d == 1 ? 7 - i : i);
                    if (A[r][c] == 0) continue;
                    int cc = c;
                    while (true) {
                        int nc = cc + (d == 1 ? +1 : -1);
                        if (nc < 0 || nc >= 8) break;
                        if (A[r][nc] == 0) {
                            A[r][nc] = A[r][cc];
                            A[r][cc] = 0;
                        } else if (A[r][nc] == A[r][cc]) {
                            if (nc != limit) {
                                A[r][nc] <<= 1;
                                A[r][cc] = 0;
                                limit = nc;
                            }
                            break;
                        } else {
                            break;
                        }
                        cc = nc;
                    }
                }
            }
        }
    }
}