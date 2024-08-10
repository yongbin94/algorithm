import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] A = new int[26];
        int bit = 0;
        int i = 0, j = 0;
        int answer = 0;
        bit |= (1 << (str.charAt(i) - 'a'));
        A[str.charAt(i) - 'a']++;
        while (i < str.length()) {
            if (Integer.bitCount(bit) <= N) {
                answer = Math.max(answer, ++j - i);
                if(j == str.length())
                    break;
                if (A[str.charAt(j) - 'a']++ == 0)
                    bit |= (1 << (str.charAt(j) - 'a'));
            } else {
                if (--A[str.charAt(i) - 'a'] == 0)
                    bit -= (1 << (str.charAt(i) - 'a'));
                i++;
            }
        }
        System.out.println(answer);
    }
}