public class Main {
    public static void main(String[] args) throws java.io.IOException {
        String s = new java.util.Scanner(System.in).next();
        int n = 0, p = 0;
        for (char c : s.toCharArray()) {
            n += c == 'P' ? 1 : -2;
            if (n < 0 || (p == c && c == 'A'))
                break;
            p = c;
        }
        System.out.println(n == 1 && p == 'P' ? "PPAP" : "NP");
    }
}