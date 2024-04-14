import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        TreeSet<Integer> gate = new TreeSet<>();
        int cnt = 0;
        for (int i = 0; i <= G; i++)
            gate.add(i);
        while (P-- > 0) {
            int i = gate.floor(Integer.parseInt(br.readLine()));
            if (i <= 0)
                break;
            gate.remove(i);
            cnt++;
        }
        System.out.println(cnt);
    }
}
