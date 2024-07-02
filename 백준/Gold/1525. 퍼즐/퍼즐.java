import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        String str = br.readLine().replace(" ", "");
        str += br.readLine().replace(" ", "");
        str += br.readLine().replace(" ", "");
        q.add(Integer.parseInt(str));
        set.add(Integer.parseInt(str));
        int time = 0;
        while (!q.isEmpty()) {
            for(int i = 0, size = q.size(); i < size; i++) {
                int num = q.poll();
                if(num == 123456780){
                    System.out.println(time);
                    return;
                }
                int idx = 0;
                int tmp = num;
                while (tmp > 0) {
                    if (tmp % 10 == 0)
                        break;
                    tmp /= 10;
                    idx++;
                }
                // 상
                if (idx < 6) {
                    tmp = (num % (int) Math.pow(10, idx + 4)) - (num % (int) Math.pow(10, idx + 3));
                    int next = num - tmp + (tmp / 1000);
                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }
                // 하
                if (idx >= 3) {
                    tmp = (num % (int) Math.pow(10, idx - 2)) - (num % (int) Math.pow(10, idx - 3));
                    int next = num - tmp + (tmp * 1000);
                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }

                // 좌
                if (idx % 3 != 2) {
                    tmp = (num % (int) Math.pow(10, idx + 2)) - (num % (int) Math.pow(10, idx + 1));
                    int next = num - tmp + (tmp / 10);
                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }

                // 우
                if (idx % 3 != 0) {
                    tmp = (num % (int) Math.pow(10, idx)) - (num % (int) Math.pow(10, idx - 1));
                    int next = num - tmp + (tmp * 10);
                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }
            }
            time++;
        }
        System.out.println(-1);
    }
}
