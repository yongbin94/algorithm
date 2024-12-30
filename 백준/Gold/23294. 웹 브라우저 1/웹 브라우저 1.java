import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, C;
    static int[] CAP;
    static int curr, c;
    static Deque<Integer> prev, next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        CAP = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++)
            CAP[n] = Integer.parseInt(st.nextToken());
        prev = new ArrayDeque<>();
        next = new ArrayDeque<>();
        curr = 0;
        c = 0;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            switch (cmd) {
                case 'B':
                    backward();
                    break;
                case 'F':
                    frontward();
                    break;
                case 'A':
                    access(Integer.parseInt(st.nextToken()));
                    break;
                case 'C':
                    compress();
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(curr).append("\n");
        if (prev.isEmpty())
            sb.append("-1");
        while (!prev.isEmpty())
            sb.append(prev.pollLast()).append(" ");
        sb.append("\n");
        if (next.isEmpty())
            sb.append("-1");
        while (!next.isEmpty())
            sb.append(next.poll()).append(" ");
        System.out.println(sb);
    }

    private static void backward() {
        if (prev.isEmpty())
            return;
        next.offerFirst(curr);
        curr = prev.pollLast();
    }

    private static void frontward() {
        if (next.isEmpty())
            return;
        prev.offer(curr);
        curr = next.poll();
    }

    private static void access(int page) {
        c += CAP[page];
        if (curr != 0)
            prev.offer(curr);
        curr = page;
        while (!next.isEmpty())
            c -= CAP[next.poll()];
        while (C < c)
            c -= CAP[prev.pollFirst()];
    }

    private static void compress() {
        Deque<Integer> tmp = new ArrayDeque<>();
        if (!prev.isEmpty())
            tmp.offer(prev.poll());
        while (!prev.isEmpty()) {
            if (tmp.peekLast().equals(prev.peek())) {
                c -= CAP[prev.poll()];
                continue;
            }
            tmp.offer(prev.poll());
        }
        prev = tmp;
    }
}