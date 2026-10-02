class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack("", 0, 0, n, ans);
        return ans;
    }

    void backtrack(String s, int open, int close, int n, List<String> ans) {  // "(()"
        // If we used all brackets
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }
        // Add opening bracket
        if (open < n) { //n=3 open=2
            backtrack(s + "(", open + 1, close, n, ans);
        }
        // Add closing bracket
        if (close < open) {  //s="(())"
            backtrack(s + ")", open, close + 1, n, ans);
        }
    }
}