import java.io.*;
import java.util.*;

public class Main {
	static int N;
    static Menu[] menus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        menus = new Menu[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            menus[i] = new Menu(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(menus);

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int startIdx = findLowerBound(u);
            int endIdx = findUpperBound(v);

            int cnt = 0;
            for (int i = startIdx; i <= endIdx; i++) {
                if (menus[i].b >= x && menus[i].b <= y) {
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static int findLowerBound(int val) {
        int l = 0, r = N - 1;
        int res = N;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (menus[mid].a >= val) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private static int findUpperBound(int val) {
        int l = 0, r = N - 1;
        int res = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (menus[mid].a <= val) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    static class Menu implements Comparable<Menu> {
        int a, b;
        Menu(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Menu o) {
            return this.a - o.a;
        }
    }
}