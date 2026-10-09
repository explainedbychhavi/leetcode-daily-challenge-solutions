class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // consume the second ')'
                } else {
                    ans++; // insert one ')' to complete '))'
                }

                if (open > 0) {
                    open--;
                } else {
                    ans++; // insert a missing '('
                }
            }
        }

        return ans + 2 * open;
    }
}
