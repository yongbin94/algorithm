import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        Side[] S = new Side[6];
        Arrays.setAll(S, v -> new Side());
        for (int i = 0; i < 6; i++) {
            S[i].link(S[(i + 1) % 6]);
        }
        long inside = N >= 6 ? 1 : 0;
        for (int n = 6; n < N; n++) {
            inside += Arrays.stream(S).max((a, b) -> a.v - b.v).get().expand();
        }
        System.out.println((long) N + inside);
    }

    private static class Side {
        int v = 0;
        Side prev, next;

        public void link(Side o) {
            this.prev = o;
            o.next = this;
        }

        public int expand() {
            prev.v++;
            next.v++;
            return v--;
        }
    }
}