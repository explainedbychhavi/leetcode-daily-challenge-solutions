
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        int max = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
            total += diff[i];
        }

        if (k >= total) return 0;

        int low = 0, high = max;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long ops = 0;

            for (int d : diff) {
                ops += Math.max(0, d - mid);
            }

            if (ops <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int limit = low;
        long ops = 0;
        long sum = 0;

        for (int d : diff) {
            int reduced = Math.min(d, limit);
            sum += (long) reduced * reduced;
            ops += Math.max(0, d - limit);
        }

        long extra = k - ops;
        return sum - extra * (2L * limit - 1);
    }
}
