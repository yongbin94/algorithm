import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {
    static int N;
    static Cat[] cats;
    static HashMap<Integer, TreeMap<Integer, Cat>> rm, cm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cats = new Cat[N];
        rm = new HashMap<>();
        cm = new HashMap<>();
        for (int n = 0; n < N; n++) {
            cats[n] = new Cat(new StringTokenizer(br.readLine()));
        }
        for (int n = 0; n < N; n++) {
            cats[n].play();
        }
        System.out.println(Arrays.stream(cats).filter(v -> v.alive).count());
    }

    static List<BiFunction<TreeMap<Integer, Cat>, Integer, Map.Entry<Integer, Cat>>> func = List.of(
            TreeMap::lowerEntry,
            TreeMap::higherEntry
    );

    private static class Cat {
        int r, c;
        boolean alive;

        public Cat(StringTokenizer st) {
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            alive = true;

            TreeMap<Integer, Cat> tm;
            tm = rm.computeIfAbsent(r, k -> new TreeMap<>());
            tm.put(c, this);

            tm = rm.computeIfAbsent(r + 1, k -> new TreeMap<>());
            tm.put(c, null);

            tm = cm.computeIfAbsent(c, k -> new TreeMap<>());
            tm.put(r, this);
            tm.put(r + 1, null);
        }

        public void play() {
            if (!alive) return;
            for (int d = 0; d < 4; d++) {
                int next = d < 2 ? r : c;
                while (true) {
                    Map.Entry<Integer, Cat> entry = func.get(d % 2).apply((d < 2 ? cm.get(c) : rm.get(r)), next);
                    if (entry == null || entry.getValue() == null) break;
                    entry.getValue().alive = false;
                    next = entry.getKey();
                }
            }
        }
    }
}