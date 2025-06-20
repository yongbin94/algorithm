public class Main {
    public static void main(String[] args) {
        String input = new java.util.Scanner(System.in).next();
        int L = input.length();
        int[] A = new int[L], B = new int[L];
        boolean[] X = new boolean[L], Y = new boolean[L];
        for (int i = 1; i < L; i++) {
            A[i] = A[i - 1] + (input.charAt(i - 1) == '(' ? 1 : -1);
            X[i] = X[i - 1] | (A[i] < 0);
            B[L - i - 1] = B[L - i] + (input.charAt(L - i) == ')' ? 1 : -1);
            Y[L - i - 1] = Y[L - i] | (B[L - i - 1] < 0);
        }
        int res = 0;
        for (int i = 0; i < L; i++) {
            if (X[i] | Y[i]) continue;
            if ((A[i] - B[i] + (input.charAt(i) == ')' ? 1 : -1)) == 0) res++;
        }
        System.out.println(res);
    }
}