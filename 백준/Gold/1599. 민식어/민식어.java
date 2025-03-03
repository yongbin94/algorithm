import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, String> tm = new TreeMap<>();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String input = br.readLine();
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch == 'k') {
                    tmp.append('c');
                } else if (ch == 'n' && i + 1 < input.length() && input.charAt(i + 1) == 'g') {
                    tmp.append('o');
                    i++;
                } else if (ch == 'o') {
                    tmp.append('p');
                } else if (ch == 'p') {
                    tmp.append('q');
                } else {
                    tmp.append(ch);
                }
            }
            tm.put(tmp.toString(), input);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!tm.isEmpty()) {
            sb.append(tm.pollFirstEntry().getValue()).append("\n");
        }
        System.out.println(sb);
    }
}