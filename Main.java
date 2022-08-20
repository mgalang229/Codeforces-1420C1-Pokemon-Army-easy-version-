import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*

1 2 3 4 5 6 7 8 9

- if all numbers are the same, just print it
- if the sequence is in non-increasing order, the alternating would get smaller
- if the sequence is in non-increasing order, the alternating would get smaller as well

3 5 3 1 4 6 8 4 3 2 9 10
= 5 - 1 + 8 - 2 + 10
= 20

we should continue finding the larger element

= current element + smallest between + larger than current

for every element choose the highest element from the back
choose the minimum element in a given interval

we need something that would increase the max element of the sequence

1 2 5 4 3 6 7

we need to make sure that the element between the end points is smaller
for every i, j, k where 0 <= i < j < k < n: 
a[i] > a[j] < a[k]
this expression would always make the sum bigger
otherwise, it would stay constant or simply get smaller

how would i do this ?

contradicting test cases:

1
12 0
3 5 3 1 4 6 8 4 3 2 9 10
{5, 1, 8, 2, 10}
max sum = 20

1
10 0
6 1 9 3 7 6 8 1 3 4
{6, 1, 9, 3, 7, 6, 8, 1, 4}
max sum = 23

inference:
plus = increasing order
minus = decreasing order

compare max sum while doing the operations

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int q = fs.nextInt();
			int[] a = fs.readArray(n);
			long max = Arrays.stream(a).max().getAsInt();
			long ans = Long.MIN_VALUE;
			long sum = 0;
			String op = "plus";
			for (int i = 1; i < n; i++) {
				if (op.equals("plus")) {
					int index = i;
					while (index < n && a[index] >= a[index-1]) {
						index++;
					}
					index--;
					sum += a[index];
					ans = Math.max(ans, sum);
					i = index;
					op = "minus";
				} else {
					int index = i;
					while (index < n && a[index] <= a[index-1]) {
						index++;
					}
					index--;
					sum -= a[index];
					ans = Math.max(ans, sum);
					i = index;
					op = "plus";
				}
			}
			out.println(Math.max(ans, max));
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
