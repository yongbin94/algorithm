import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] tails = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = 0;
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            int i = Arrays.binarySearch(tails, 0, size, v);
            if (i < 0) i = -i - 1;
            tails[i] = v;
            if(i == size) size++;
        }
        System.out.println(N - size);
    }
}