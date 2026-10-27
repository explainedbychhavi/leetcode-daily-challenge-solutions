class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        grid[0] = ".".repeat(n);

        for (int i = 1; i < m; i++) {
            grid[i] = "#".repeat(n);
        }

        for (int i = 1; i < n; i++) {
            char[] row = grid[0].toCharArray();
            row[i] = '.';
            grid[0] = new String(row);
        }
        for (int i = 1; i < m; i++) {
            char[] row = grid[i].toCharArray();
            row[n - 1] = '.';
            grid[i] = new String(row);
        }

        return grid;
    }
}
