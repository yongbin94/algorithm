public class Main {
    public static void main(String[] args) {
        int N = new java.util.Scanner(System.in).nextInt();
        int[] A = new int[N + 1];
        while (N > 0) {
            int n = 1;
            while (n <= N) n <<= 1;
            for (int i = n - N; i <= N; i++) {
                A[i] = n - i;
            }
            N = n - N - 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < A.length; i++) {
            sb.append(A[i]).append("\n");
        }
        System.out.println(sb);
    }
}