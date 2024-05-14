import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        Stack<Character> oper = new Stack<>();
        Stack<Integer> value = new Stack<>();
        int answer = 0, current = 0;
        for (char c : S) {
            switch (c) {
                case '(':
                case '[':
                    oper.push(c);
                    value.push(current);
                    current = 0;
                    break;
                case ')':
                case ']':
                    if (oper.isEmpty()) {
                        System.out.println(0);
                        return;
                    }
                    char p = oper.pop();
                    int v = value.pop();
                    if (++p != c && ++p != c) {
                        System.out.println(0);
                        return;
                    }
                    int n = c == ')' ? 2 : 3;
                    if(current == 0) {
                        current += n;
                        current += v;
                        break;
                    }
                    current *= n;
                    current += v;
            }
        }
        System.out.println(oper.isEmpty() ? current : 0);
    }
}