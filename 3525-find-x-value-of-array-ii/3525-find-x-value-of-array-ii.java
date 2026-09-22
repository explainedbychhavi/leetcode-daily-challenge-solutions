
class Solution {

    int k;
    Node[] tree;

    class Node {
        int product;
        int[] pre;
        int[] suf;

        Node() {
            pre = new int[k];
            suf = new int[k];
        }
    }

    Node merge(Node a, Node b) {

        if (a == null) return b;
        if (b == null) return a;

        Node c = new Node();

        // Product of entire segment
        c.product = (a.product * b.product) % k;

        // Prefixes
        // Prefix completely inside left
        for (int r = 0; r < k; r++) {
            c.pre[r] = a.pre[r];
        }

        // Prefix = entire left + prefix of right
        for (int r = 0; r < k; r++) {

            if (b.pre[r] > 0) {
                int newRemainder = (a.product * r) % k;
                c.pre[newRemainder] += b.pre[r];
            }
        }

        // Suffixes
        // Suffix completely inside right
        for (int r = 0; r < k; r++) {
            c.suf[r] = b.suf[r];
        }

        // Suffix = suffix of left + entire right
        for (int r = 0; r < k; r++) {

            if (a.suf[r] > 0) {
                int newRemainder = (r * b.product) % k;
                c.suf[newRemainder] += a.suf[r];
            }
        }

        return c;
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            tree[node] = new Node();

            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].pre[rem] = 1;
            tree[node].suf[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    void update(int node, int l, int r,
                int index, int value) {

        if (l == r) {

            tree[node] = new Node();

            int rem = value % k;

            tree[node].product = rem;
            tree[node].pre[rem] = 1;
            tree[node].suf[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r,
               int ql, int qr) {

        if (qr < l || r < ql) {
            return null;
        }

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            nums[index] = value;

            update(1, 0, n - 1, index, value);

            // Get segment [start ... n-1]
            Node res = query(1, 0, n - 1,
                             start, n - 1);

            // IMPORTANT:
            // We need prefixes, not all subarrays.
            answer[i] = res.pre[x];
        }

        return answer;
    }
}
