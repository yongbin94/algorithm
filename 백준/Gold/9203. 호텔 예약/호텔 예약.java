import java.io.*;
import java.util.*;

public class Main {
    static int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Reservation[] list = new Reservation[B];
            for (int i = 0; i < B; i++) {
                st = new StringTokenizer(br.readLine());
                String code = st.nextToken();
                long s = dateTimeToLong(st.nextToken(), st.nextToken());
                long e = dateTimeToLong(st.nextToken(), st.nextToken()) + C;
                
                list[i] = new Reservation(s, e);
            }

            Arrays.sort(list, (o1, o2) -> Long.compare(o1.s, o2.s));

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (Reservation res : list) {
                if (!pq.isEmpty() && pq.peek() <= res.s) {
                    pq.poll();
                }
                pq.offer(res.e);
            }
            sb.append(pq.size()).append("\n");
        }
        System.out.print(sb);
    }

    private static long dateTimeToLong(String date, String time) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(5, 7));
        int d = Integer.parseInt(date.substring(8, 10));
        
        int h = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));

        long totalDays = 0;
        for (int i = 2013; i < y; i++) {
            totalDays += isLeap(i) ? 366 : 365;
        }
        for (int i = 1; i < m; i++) {
            totalDays += days[i];
            if (i == 2 && isLeap(y)) totalDays++;
        }
        totalDays += (d - 1);

        return totalDays * 24 * 60 + h * 60 + min;
    }

    private static boolean isLeap(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    static class Reservation {
        long s, e;
        Reservation(long s, long e) {
            this.s = s;
            this.e = e;
        }
    }
}