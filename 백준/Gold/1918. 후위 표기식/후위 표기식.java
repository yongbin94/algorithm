import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S = br.readLine();
        Stack<Character> oper = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            switch (c) {
                case '+':
                case '-':
                    while (!oper.isEmpty() && oper.peek() != '(')
                        sb.append(oper.pop());
                    oper.push(c);
                    break;
                case '*':
                case '/':
                    while (!oper.isEmpty() && (oper.peek() == '*' || oper.peek() == '/'))
                        sb.append(oper.pop());
                case '(':
                    oper.push(c);
                    break;
                case ')':
                    while (oper.peek() != '(')
                        sb.append(oper.pop());
                    oper.pop();
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        while (!oper.isEmpty())
            sb.append(oper.pop());
        System.out.println(sb);
    }

}