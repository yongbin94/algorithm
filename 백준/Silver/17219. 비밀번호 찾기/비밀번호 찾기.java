import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, String> map = new HashMap<>();
		while(N-- > 0) {
			st= new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken());
		}
		while(M-- > 0) 
			System.out.println(map.get(br.readLine()));
	}
}
