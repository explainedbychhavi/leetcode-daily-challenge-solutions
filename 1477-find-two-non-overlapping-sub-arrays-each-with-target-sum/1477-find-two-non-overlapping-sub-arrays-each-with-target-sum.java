class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];

        int INF = 1_000_000_000;
        int ans = INF;

        int left = 0;
        int sum = 0;
        int minLen = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left++];
            }

            // Found a subarray with sum = target
            if (sum == target) {
                int len = right - left + 1;

                // Previous subarray must end before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                minLen = Math.min(minLen, len);
            }

            // Store the best subarray found so far
            best[right] = minLen;
        }

        return ans == INF ? -1 : ans;
    }
}