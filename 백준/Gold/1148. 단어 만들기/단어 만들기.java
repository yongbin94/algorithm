import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] words = new int[200001][26];
        int N = 0;
        String input;
        while ((input = br.readLine()).charAt(0) != '-') {
            for (char ch : input.toCharArray()) {
                words[N][ch - 'A']++;
            }
            N++;
        }

        StringBuilder sb = new StringBuilder();
        while ((input = br.readLine()).charAt(0) != '#') {
            int[] res = new int[26];
            int[] puzzle = new int[26];
            for (char ch : input.toCharArray()) {
                puzzle[ch - 'A']++;
            }
            word:
            for (int n = 0; n < N; n++) {
                int[] word = words[n];
                for (int i = 0; i < 26; i++) {
                    if (puzzle[i] < word[i]) continue word;
                }
                for (int i = 0; i < 26; i++) {
                    if (word[i] > 0) res[i]++;
                }
            }
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < 26; i++) {
                if (puzzle[i] == 0) continue;
                min = Math.min(min, res[i]);
                max = Math.max(max, res[i]);
            }
            for (int i = 0; i < 26; i++) {
                if (puzzle[i] > 0 && res[i] == min) sb.append((char) ('A' + i));
            }
            sb.append(" ").append(min).append(" ");
            for (int i = 0; i < 26; i++) {
                if (puzzle[i] > 0 && res[i] == max) sb.append((char) ('A' + i));
            }
            sb.append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}