import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

		int a = 0; int b = 0; int times = 0; int wear = 0; // island 1, island 2, time to cross, hull damage

		for (int x = 0; x < routes; x++) {
			String[] seaRouteData = br.readLine().split(" ");
			a = Integer.parseInt(seaRouteData[0]) - 1; // island 1
			b = Integer.parseInt(seaRouteData[1]) - 1; // island 2

			// Island 1 already there?
			if(islands[a] == null) {
				islands[a] = new Island(thickness, a); // new island with default thickness and identify of the first island
				islands[a].visited = new boolean[islandsNum]; // construct the boolean array for the first island inputted
			}
			// Island 2 already there?
			if(islands[b] == null) {
				islands[b] = new Island(thickness, b); // new island with default thickness and identify of the second island
				islands[b].visited = new boolean[islandsNum]; // construct the boolean array for the second island inputted
			}

			times = Integer.parseInt(seaRouteData[2]); // time on this route
			wear = Integer.parseInt(seaRouteData[3]); // hull damage on this route

			// First island, add a route to it as requested (must do this both ways as it is an undirected graph)
			islands[a].routes.add(new Route(islands[a], islands[b], wear, times)); // Route from island '1' to island '2', how much hull degradation, time spent
			islands[b].routes.add(new Route(islands[b], islands[a], wear, times)); // Route from island '2' to island '1', same hull degradation, same time spent
		} // end for() going through routes between islands

		// Now find out where they want us to go
		String[] RTF = br.readLine().split(" ");
		int start = Integer.parseInt(RTF[0]) -1; // The island we are currently on
		int end = Integer.parseInt(RTF[1]) - 1; // The island we want to journey too ( cheapest time && hull > 0 )

		System.out.println(dijkstra(islands, start, end)); // Run Dijkstra's algorithm, pass it the island array, start and end islands
	}

	public static int dijkstra(Island[] islands, int start, int end) {

		int minTime = Integer.MAX_VALUE; // The min time, it is helpful to initialize this to max for first checking

		// We are working with the head and the tail so an ArrayDeque makes sense (.removeFirst, and .addLast)
		ArrayDeque<Island> queue = new ArrayDeque<>(); // queue for islands to be visited and tested

		queue.addLast(islands[start]); // Add on the end of the deque the source island (the island we start on)

		while(!queue.isEmpty()) {

			int size = queue.size();
//			System.out.println(size);

			for(int qq = 0; qq < size; qq++) {
				// Remove the first island in the queue
				Island temp = queue.removeFirst(); // (this is the island with the least distance from the source)
				if(temp.num == end) { //
					if(minTime > temp.time) {
						minTime = temp.time;
					}
				}
				for(int i = 0; i < temp.routes.size(); i++) {
					Route tempR = temp.routes.get(i);

					int rWear = temp.routes.get(i).wear;
					int time = temp.time;
//					System.out.println(temp.wear + "-" + rWear);
					if(temp.wear - rWear > 0 && time + temp.routes.get(i).time < minTime && !temp.visited[temp.routes.get(i).b.num]) {
						Island newIsland = new Island(temp.wear - tempR.wear, temp.routes.get(i).b.num);
//						System.out.println(temp.routes.get(i).b.num);
						newIsland.routes = temp.routes.get(i).b.routes;
						newIsland.time += temp.time + temp.routes.get(i).time;
						boolean[] arr = Arrays.copyOf(temp.visited, temp.visited.length);
						arr[temp.routes.get(i).b.num] = true;
						newIsland.visited = arr;
						queue.addLast(newIsland);
					}
				}
			}
		}
		if (minTime == Integer.MAX_VALUE) {
			return -1;
		} else {
			return minTime;
		}
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

