import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Tyler on 10/02/2017.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] cellStates;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numCells = Integer.parseInt(st.nextToken());
		int states = Integer.parseInt(st.nextToken());
		cellStates = new int[numCells];

		String[] input = br.readLine().split("");


		for(int a = 0; a < numCells; a++) {
			cellStates[a] = Integer.parseInt(input[a]);
		}

		// All the values now in the program
		cellStates = conwaySolve(states);

		for(int x = 0; x < cellStates.length; x++) {
			System.out.print(cellStates[x]);
		}
	}

	public static int[] conwaySolve(int s) {
		for(int b = 0; b < s; b++) {
			int[] temp = cellStates.clone();
			for(int x = 0; x < cellStates.length; x++) {
				int left = 0; int right = 0;
				if( x == 0 )
				{ left = cellStates.length - 1; right = x + 1; }
				else if (x == cellStates.length-1)
				{ left = x - 1; right = 0; }
				else
				{ left = x-1; right = x + 1;}

				// Now the actual decisions
				if (temp[left] == 0 && temp[right] == 0) {
					cellStates[x] = 0;
				} else if ((temp[left] == 0 && temp[right] == 1) | (temp[left] == 1 && temp[right] == 0)) {
					cellStates[x] = 1;
				} else if (temp[left] == 1 && temp[right] == 1) {
					cellStates[x] = 0;
				}
			}
		}
		return cellStates;
	}
}
