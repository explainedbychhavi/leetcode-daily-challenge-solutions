class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s, int l, int r) {
        Set<String> result = new HashSet<>();

        // Single character
        if (l == r) {
            result.add(String.valueOf(s.charAt(l)));
            return result;
        }

        // Remove outer braces if they enclose the whole expression
        if (s.charAt(l) == '{' && matchingBrace(s, l) == r) {
            return solve(s, l + 1, r - 1);
        }

        int balance = 0;
        int start = l;

        // First handle top-level union
        for (int i = l; i <= r; i++) {
            char ch = s.charAt(i);

            if (ch == '{') {
                balance++;
            } else if (ch == '}') {
                balance--;
            } else if (ch == ',' && balance == 0) {   //  a,{b,c},d

                result.addAll(solve(s, start, i - 1));
                start = i + 1;
            }
        }

        // If a top-level comma existed
        if (start != l) {
            result.addAll(solve(s, start, r));
            return result;
        }

        // Otherwise, this is concatenation
        balance = 0;

        for (int i = l; i < r; i++) {
            char ch = s.charAt(i);   //{a,b} {c,d}

            if (ch == '{') {
                balance++;
            } else if (ch == '}') {
                balance--;
            }

            // Split at a valid concatenation point
            if (balance == 0) {
                Set<String> left = solve(s, l, i);
                Set<String> right = solve(s, i + 1, r);

                for (String a : left) {
                    for (String b : right) {
                        result.add(a + b);
                    }
                }

                return result;
            }
        }

        return result;
    }

    private int matchingBrace(String s, int start) {
        int balance = 0;

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;

                if (balance == 0) {
                    return i;
                }
            }
        }

        return -1;
    }
}