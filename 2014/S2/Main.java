import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by Tyler on 09/02/2017.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		String[] names = new String[N];
		String[] partners = new String[N];

		st = new StringTokenizer(br.readLine());

		for(int a = 0; a < N; a++) {
			names[a] = st.nextToken();
		}

		st = new StringTokenizer(br.readLine());
		for(int b =0; b < N; b++) {
			partners[b] = st.nextToken();
		}

		// All of the values are now in the program

		int check = 0;

		for(int x = 0; x < N; x++) {
			String temp = names[x];
			String tempPartner = partners[x];
			for(int y = 0; y < N; y++) {
				if(partners[y].matches(temp)) {
					if(names[y].matches(tempPartner) && !names[y].matches(partners[y])) {
						check++;
					}
				}
			}
		}

		if(check == N) {
			System.out.println("good");
		} else {
			System.out.println("bad");
		}

	}
}

