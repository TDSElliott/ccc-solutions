package S4_2015_ConvexHull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2015 - Question #4: Convex Hull
 * Language - Java
 * Author Tyler D.S. Elliott
 * Created on 08/02/2017.
 */
public class Main {
	island[] islands = new island[10010];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] parameters = br.readLine().split(" ");
		int k = Integer.parseInt(parameters[0]);
		int n = Integer.parseInt(parameters[1]);
		int m = Integer.parseInt(parameters[2]);

		int a = 0; int b = 0; int t = 0; int h = 0;

		List<edge> graph = new ArrayList<edge>();
		int[][] dist = new int[2001][201];
		boolean[] inq = new boolean[2001];
		Arrays.fill(dist, 63);

		for (int x = 0; x < m; x++) {
			String[] seaRouteData = br.readLine().split(" ");
			a = Integer.parseInt(seaRouteData[0]);
			b = Integer.parseInt(seaRouteData[1]);
			t = Integer.parseInt(seaRouteData[2]);
			h = Integer.parseInt(seaRouteData[3]);

			graph.add(a, new edge(b, t, h));
			graph.add(b, new edge(a, t, h));
		}

		String[] routeToFollow = br.readLine().split(" ");
		int A = Integer.parseInt(routeToFollow[0]);
		int B = Integer.parseInt(routeToFollow[1]);

		PriorityQueue<Pair<Integer, Integer>> que;
	}

}

class canal {
	island[] destination = new island[10010];
	long weight;
	long hullDamage;
	canal(ArrayList<island> dest, long w, long dmg) {
		destination = dest;
		weight = w;
		hullDamage = dmg;
	}
}

class island {
	ArrayList<canal> outgoing = new ArrayList<canal>();
	int shortestpath, hull;
	island() {
		shortestpath = 999;
		hull = -1;
	}
}

