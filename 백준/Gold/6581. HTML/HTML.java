import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        int cnt = 0;
        while ((input = br.readLine()) != null) {
            for (String str : input.replace("\t", " ").split(" ")) {
                if (str.isEmpty()) continue;
                if (str.equals("<br>")) {
                    sb.append("\n");
                    cnt = 0;
                } else if (str.equals("<hr>")) {
                    if (cnt != 0) sb.append("\n");
                    sb.append("--------------------------------------------------------------------------------\n");
                    cnt = 0;
                } else {
                    int size = str.length();
                    if (cnt != 0 && (cnt + size + 1) > 80) {
                        sb.append("\n").append(str);
                        cnt = size;
                    } else {
                        if (cnt != 0) {
                            cnt++;
                            sb.append(" ");
                        }
                        sb.append(str);
                        cnt += size;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}