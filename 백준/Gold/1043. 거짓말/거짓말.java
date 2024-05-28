import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean A[], B[];
	static List<Integer>[] AList, BList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new boolean[N+1];
		B = new boolean[M];
		AList =  new ArrayList[N+1];
		BList = new ArrayList[M];
		for(int i = 1; i <= N; i++)
			AList[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int z = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= z; i++) 
			A[Integer.parseInt(st.nextToken())] = true;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			BList[m] = new ArrayList<>();
			int I = Integer.parseInt(st.nextToken());
			for(int i = 1; i <= I; i++) {
				int v = Integer.parseInt(st.nextToken());
				AList[v].add(m);
				BList[m].add(v);
			}
		}
		
		for(int i = 1; i <= N; i++) 
			if(A[i])
				recur(i);
		
		int result = 0;
		for(int i = 0; i < M; i++) 
			if(!B[i])
				result++;
		
		System.out.println(result);
	}

	private static void recur(int i) {
		for(int a : AList[i]) {
			if(!B[a]) {
				B[a] = true;
				for(int b : BList[a]) {
					if(!A[b]) {
						A[b] = true;
						recur(b);
					}
				}
			}
		}
	}
}
