import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[8000 * 12 + 1];
        while (N-- > 0) {
            String str = br.readLine();
            int startYear = Integer.parseInt(str.substring(0, 4)) - 2000;
            int startMonth = Integer.parseInt(str.substring(5, 7)) - 1;
            int endYear = Integer.parseInt(str.substring(8, 12)) - 2000;
            int endMonth = Integer.parseInt(str.substring(13, 15)) - 1;
            A[startYear * 12 + startMonth]++;
            A[endYear * 12 + endMonth + 1]--;
        }
        int max = A[0];
        int answer = 0;
        for (int i = 1; i < 8000 * 12; i++) {
            A[i] += A[i - 1];
            if (max < A[i]) {
                max = A[i];
                answer = i;
            }
        }
        int year = answer / 12 + 2000;
        int month = answer % 12 + 1;
        StringBuilder sb  = new StringBuilder();
        sb.append(year).append("-").append(month < 10 ? 0 : "").append(month);
        System.out.println(sb);
    }
}