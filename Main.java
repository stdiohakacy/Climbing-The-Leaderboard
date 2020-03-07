public class Main {

	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int nScores = scores.length;
		int nAlice = alice.length;

		int result[] = new int[nAlice];
		int []ranks = new int[nScores];
		ranks[0] = 1;

		for (int i = 1; i < nScores; i++) {
			if (scores[i] == scores[i - 1]) {
				ranks[i] = ranks[i - 1];
			} else {
				ranks[i] = ranks[i - 1] + 1;
			}
		}

		for (int i = 0; i < nAlice; i++) {
			if (alice[i] > scores[0]) {
				result[i] = 1;
			} else if (alice[i] < scores[nScores - 1]) {
				result[i] = ranks[nScores - 1] + 1;
			} else {
				int index = binarySearch(scores, alice[i]);
				result[i] = ranks[index];
			}
		}
		return result;
	}

	private static int binarySearch(int[] scores, int alice) {
		int head = 0;
		int tail = scores.length;

		while (head <= tail) {
			int mid = head + (tail - head) / 2;
			if (scores[mid] == alice) {
				return mid;
			} else if (scores[mid] < alice && alice < scores[mid - 1]) {
				return mid;
			} else if (scores[mid] > alice && alice >= scores[mid + 1]) {
				return mid + 1;
			} else if (scores[mid] < alice) {
				tail = mid - 1;
			} else if (scores[mid] > alice) {
				head = mid + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] scores = { 100, 90, 90, 80, 75, 60 };
		int[] alice = { 50, 65, 77, 90, 102 };

//		int[] scores = { 100, 100, 50, 40, 40, 20, 10 };
//		int[] alice = { 5, 25, 50, 120 };

		int[] data = new int[alice.length];
		data = climbingLeaderboard(scores, alice);
		
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i] + " ");
		}
	}
}
