import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static class Planet {
		int no, x, y, z;

		public Planet(int no, int x, int y, int z) {
			this.no = no;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		List<Planet> planetList = new ArrayList<>();
		int no = 0;
		for (int n = 0; n < V; n++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			planetList.add(new Planet(no++,input[0], input[1], input[2]));
		}

		List<Vertex>[] vertexList = new LinkedList[V];
		for (int i = 0; i < V; i++)
			vertexList[i] = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			if(i == 0) Collections.sort(planetList, (o1, o2) -> Integer.compare(o1.x,o2.x));
			if(i == 1) Collections.sort(planetList, (o1, o2) -> Integer.compare(o1.y,o2.y));
			if(i == 2) Collections.sort(planetList, (o1, o2) -> Integer.compare(o1.z,o2.z));
			
			for(int j = 1; j < V; j++) {
				Planet aPlanet = planetList.get(j-1);
				Planet bPlanet = planetList.get(j);
				int weight = 0;
				if(i == 0) weight = Math.abs(aPlanet.x - bPlanet.x);
				if(i == 1) weight = Math.abs(aPlanet.y - bPlanet.y);
				if(i == 2) weight = Math.abs(aPlanet.z - bPlanet.z);

				vertexList[aPlanet.no].add(new Vertex(bPlanet.no, weight));
				vertexList[bPlanet.no].add(new Vertex(aPlanet.no, weight));
			}
		}

		int[] minEdge = new int[V];
		Arrays.fill(minEdge, 1, V, Integer.MAX_VALUE);

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, 0));

		boolean[] visited = new boolean[V];
		int result = 0;
		int cnt = 0;
		while (true) {
			Vertex minVertex = pq.poll();
			if (visited[minVertex.no])
				continue;

			result += minVertex.weight;
			visited[minVertex.no] = true;

			if (++cnt == V)
				break;

			for (Vertex vertex : vertexList[minVertex.no]) {
				if (!visited[vertex.no] && vertex.weight < minEdge[vertex.no]) {
						minEdge[vertex.no] = vertex.weight;
						pq.offer(new Vertex(vertex.no, vertex.weight));
				}
			}
		}
		System.out.println(result);
	}
}
