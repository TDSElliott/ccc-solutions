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
	public static void main(String[] args) throws IOException {
		// Buffered reader is very fast, necessary for CCC input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] parameters = br.readLine().split(" ");

		int thickness = Integer.parseInt(parameters[0]);	// hull length
		int islandsNum = Integer.parseInt(parameters[1]);	// number of islands
		int routes = Integer.parseInt(parameters[2]); 		// number of routes between islands
		Island[] islands = new Island[islandsNum];

		int a = 0; int b = 0; int t = 0; int h = 0;

		for (int x = 0; x < routes; x++) {
			String[] seaRouteData = br.readLine().split(" ");
			a = Integer.parseInt(seaRouteData[0]) - 1; // island 1
			b = Integer.parseInt(seaRouteData[1]) - 1; // island 2

			// Island 1 already there?
			if(islands[a] == null) {
				islands[a] = new Island(thickness, a); // new island with default thickness and identify of the first island
				islands[a].visited = new boolean[islandsNum];
			}
			// Island 2 already there?
			if(islands[b] == null) {
				islands[b] = new Island(thickness, b);
				islands[b].visited = new boolean[islandsNum];
			}

			times = Integer.parseInt(seaRouteData[2]); // time on this route
			wear = Integer.parseInt(seaRouteData[3]); // hull damage on this route

			islands[a].routes.add(new Route(islands[a], islands[b], wear, times));
			islands[b].routes.add(new Route(islands[b], islands[a], wear, times));
		}
		String[] RTF = br.readLine().split(" ");
		int start = Integer.parseInt(RTF[0]);
		int end = Integer.parseInt(RTF[1]);

		System.out.println(dijkstra());
	}

	public int dijkstra() {
		int minTIme = Integer.MAX_VALUE;
	}
}

class Route {
	Island a; // from this island
	Island b; // to this island
	int wear; // how much does it wear the hull?
	int time; // how long does it take to travel?

	// Constructor
	public Route(Island a, Island b, int wear, int time) {
		this.a = a;
		this.b = b;
		this.wear = wear;
		this.time = time;
	} // end constructor
} // end Route

class Island {
	int time; // time value to this island from source
	int wear; // wear on hull to this island from source
	int num;  // which island?
	ArrayList<Route> routes = new ArrayList<Route>();
	boolean[] visited; // visited array

	public Island(int wear, int num) {
		this.wear = wear;
		this.num = num;
	}
}

