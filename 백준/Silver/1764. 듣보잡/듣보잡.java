import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        while (N-- > 0)
            set.add(br.readLine());
        while (M-- > 0) {
            String str = br.readLine();
            if (set.contains(str))
                list.add(str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        Collections.sort(list);
        for (String str : list)
            sb.append(str).append("\n");
        System.out.println(sb);
    }
}
