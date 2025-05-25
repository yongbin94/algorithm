
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().replaceAll("-", "0");
        int res = 0;
        for (int f = 1, i = 0; f <= 10; f++) {
            char a = input.charAt(i++);
            if (a == 'S') {
                res += 10;
                if (input.charAt(i + 1) == 'P') res += 10;
                else if (input.charAt(i + 1) == 'S') res += 20;
                else res += (input.charAt(i) == 'S' ? 10 : input.charAt(i) - '0') + input.charAt(i + 1) - '0';
                continue;
            }
            res += a - '0';
            char b = input.charAt(i++);
            if (b == 'P') {
                res += 10 - (a - '0');
                res += input.charAt(i) == 'S' ? 10 : input.charAt(i) - '0';
                continue;
            }
            res += b - '0';
        }
        System.out.println(res);
    }
}