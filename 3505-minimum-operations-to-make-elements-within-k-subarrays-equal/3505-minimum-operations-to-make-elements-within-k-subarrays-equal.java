
import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int sizeLeft = 0, sizeRight = 0;
        long sumLeft = 0, sumRight = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                sizeLeft++;
                sumLeft += val;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                sizeRight++;
                sumRight += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                erase(left, val);
                sizeLeft--;
                sumLeft -= val;
            } else {
                erase(right, val);
                sizeRight--;
                sumRight -= val;
            }
            balance();
        }

        void erase(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            int target = (sizeLeft + sizeRight + 1) / 2;

            while (sizeLeft > target) {
                int val = left.lastKey();
                erase(left, val);
                sizeLeft--;
                sumLeft -= val;

                right.put(val, right.getOrDefault(val, 0) + 1);
                sizeRight++;
                sumRight += val;
            }

            while (sizeLeft < target) {
                int val = right.firstKey();
                erase(right, val);
                sizeRight--;
                sumRight -= val;

                left.put(val, left.getOrDefault(val, 0) + 1);
                sizeLeft++;
                sumLeft += val;
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * sizeLeft - sumLeft
                 + sumRight - median * sizeRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window of size x

        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        // Step 2: Dynamic Programming

        long INF = Long.MAX_VALUE / 2;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Select j subarrays
        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Choice 1: Skip current element
                curr[i] = curr[i - 1];

                // Choice 2: Take a subarray of size x
                if (i >= x) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
