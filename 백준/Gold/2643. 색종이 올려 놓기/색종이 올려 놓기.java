import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Paper> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Paper(Math.max(a, b), Math.min(a, b)));
        }

        list.sort((o1, o2) -> o1.r == o2.r ? o2.c - o1.c : o2.r - o1.r);

        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            Paper curr = list.get(i);
            for (int j = 0; j < i; j++) {
                Paper prev = list.get(j);
                if (prev.r >= curr.r && prev.c >= curr.c) {
                    curr.w = Math.max(curr.w, prev.w + 1);
                }
            }
            answer = Math.max(answer, curr.w);
        }

        System.out.println(answer);
    }

    private static class Paper {
        int r, c, w;

        public Paper(int r, int c) {
            this.r = r;
            this.c = c;
            this.w = 1;
        }
    }
}