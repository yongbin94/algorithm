import java.io.*;

public class Main {
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        A = new int[6];
        for (int i = 0; i < 6; i++)
            A[i] = Integer.parseInt(br.readLine());
        answer += A[5];
        while (A[4] > 0) {
            answer++;
            A[4]--;
            A[0] -= Math.min(11, A[0]);
        }
        while (A[3] > 0) {
            answer++;
            int space = 20 - Math.min(5, A[1]) * 4;
            A[3]--;
            A[1] -= Math.min(5, A[1]);
            A[0] -= Math.min(space, A[0]);
        }
        while (A[2] > 0) {
            answer++;
            int space = 36 - Math.min(4, A[2]) * 9;
            A[2] -= Math.min(4, A[2]);
            if (space == 9) {
                space -= Math.min(1, A[1]) * 4;
                A[1] -= Math.min(1, A[1]);
            } else if (space == 18) {
                space -= Math.min(3, A[1]) * 4;
                A[1] -= Math.min(3, A[1]);
            } else if (space == 27) {
                space -= Math.min(5, A[1]) * 4;
                A[1] -= Math.min(5, A[1]);
            }
            A[0] = Math.max(0, A[0] - space);
        }
        while (A[1] > 0) {
            answer++;
            int space = 36 - Math.min(9, A[1]) * 4;
            A[1] -= Math.min(9, A[1]);
            A[0] = Math.max(0, A[0] - space);
        }
        while (A[0] > 0) {
            answer++;
            A[0] = Math.max(0, A[0] - 36);
        }
        System.out.println(answer);
    }
}