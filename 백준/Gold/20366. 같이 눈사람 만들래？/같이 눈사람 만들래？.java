import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static List<Snowman> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            A[n] = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                list.add(new Snowman(A[i] + A[j], i, j));
        Collections.sort(list);
        int answer = Integer.MAX_VALUE;
        for (int i = 0, size = list.size(); i < size; i++) {
            Snowman a = list.get(i);
            for (int j = i + 1; j < size; j++) {
                Snowman b = list.get(j);
                if(a.i != b.i && a.i != b.j && a.j != b.i && a.j != b.j) {
                    answer = Math.min(answer, b.n - a.n);
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static class Snowman implements Comparable<Snowman> {
        int n, i, j;

        public Snowman(int n, int i, int j) {
            this.n = n;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Snowman o) {
            return this.n - o.n;
        }
    }
}