import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long countA = Long.parseLong(st.nextToken());
        long countB = Long.parseLong(st.nextToken());
        long maxA = Long.parseLong(st.nextToken());
        long maxB = Long.parseLong(st.nextToken());
        long a = 0, b = 0;
        if (maxA == 0) {
            b = Math.min(countB, maxB);
        } else if (maxB == 0) {
            a = (Math.min(countA, maxA));
        } else if (countA > countB) {
            a = Math.min(countA, (countB + 1) * maxA);
            b = countB;
        } else {
            b = Math.min(countB, (countA + 1) * maxB);
            a = countA;
        }
        System.out.println(a + b);
    }
}