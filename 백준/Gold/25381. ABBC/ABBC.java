import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> st = new ArrayDeque<>();
        int answer = 0;
        for (char c : br.readLine().toCharArray()) {
            if (c == 'A') st.push(c);
            else if (c == 'B') {
                if (!st.isEmpty() && st.peek() == 'A') {
                    st.pop();
                    answer++;
                } else st.push(c);
            } else {
                if (!st.isEmpty() && st.peek() == 'B') {
                    st.pop();
                    answer++;
                } else st.push(c);
            }
        }
        System.out.println(answer);
    }
}