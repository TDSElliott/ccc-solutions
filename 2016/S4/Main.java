import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by Tyler on 09/02/2017.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer> riceBalls = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int toFollow = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for(int x = 0; x < toFollow; x++) {
			 riceBalls.add(Integer.parseInt(st.nextToken()));
		}
		System.out.println(maxSize(riceBalls));
	}

	public static int maxSize(ArrayList<Integer> r) {
		ArrayList<Integer> forTransfer = new ArrayList<>();
		for(int a = 1; a < r.size() -1; a++) {
				int left, center, right;
				left = r.get(a - 1); center = r.get(a); right = r.get(a + 1);

				if(left == center) {
					int temp = r.get(a - 1) + r.get(a);
					r.remove(a);
					r.remove(a-1);
					r.add(a, temp);
				} else if (center == right) {
					int temp = r.get(a) + r.get(a + 1);
					r.remove(a);
					r.remove(a+1);
					r.add(a, temp);
				} else if (left == right) {
					int temp = r.get(a - 1) + r.get(a) + r.get(a + 1);
					r.remove(a-1);
					r.remove(a);
					r.remove(a+1);
					r.add(a, temp);
				}
				r = forTransfer;
		}
		Collections.sort(r);
		return r.get(r.size()-1);
	}
}
