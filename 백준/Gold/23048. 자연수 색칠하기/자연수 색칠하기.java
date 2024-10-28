public class Main {
    public static void main(String[] args) {
        int N = new java.util.Scanner(System.in).nextInt(), t = 0;
        int[] P = new int[N + 1];
        P[1] = ++t;
        for(int n = 2; n <= N; n++) {
            if(P[n] != 0)
                continue;
            int i = n;
            P[n] = ++t;
            while(i <= N) {
                if(P[i] == 0)
                    P[i] = P[n];
                i += n;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t).append("\n");
        for(int n = 1; n <= N; n ++)
            sb.append(P[n]).append(" ");
        System.out.println(sb);
    }
}