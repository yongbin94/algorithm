class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 0) continue;
                union(i, j);
            }
        }
        
        boolean[] check = new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!check[find(i)]) {
                check[find(i)] = true;
                answer++;
            }
        }
        
        return answer;
    }
    

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        parent[rootB] = rootA;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}