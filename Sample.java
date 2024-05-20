// Problem 1: https://leetcode.com/problems/flood-fill/
// Time Complexity : O(m*n) where m is the number of rows and n is the number of columns in the image
// Space Complexity : O(m*n) where m is the number of rows and n is the number of columns in the image
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach: I have used DFS to solve this problem. I have used a dirs array to traverse in all 4 directions. I have also used a m and n variable to store the number of rows and columns in the image. I have checked if the old color is equal to the new color then I have returned the image as it is. I have then called the dfs function with the image, sr, sc, color and oldColor. In the dfs function, I have changed the color of the current cell to the new color and then I have traversed in all 4 directions and called the dfs function recursively if the new cell is within the bounds and the color of the new cell is equal to the old color.
class Solution {
    int[][] dirs;
    int m, n;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        this.m = image.length;
        this.n = image[0].length;
        int oldColor = image[sr][sc];
        if (oldColor == color) {
            return image;
        }
        dfs(image, sr, sc, color, oldColor);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int color, int oldColor) {
        image[i][j] = color;
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && image[nr][nc] == oldColor) {
                dfs(image, nr, nc, color, oldColor);
            }
        }
    }
}

// Problem 2: https://leetcode.com/problems/01-matrix/
// Time Complexity : O(m*n) where m is the number of rows and n is the number of
// columns in the matrix
// Space Complexity : O(m*n) where m is the number of rows and n is the number
// of columns in the matrix
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach: I have used DFS
// to solve this problem. I have used a result array to store the distance of
// the nearest 0 from the current cell. I have called the dfs function for all
// the cells with value 1. In the dfs function, I have checked if the current
// cell is 0 then I have returned 0. I have then checked if the current cell is
// on the boundary and if the cell is 0 then I have returned 1. I have then
// checked the top, bottom, left and right cells and if they are not 0 then I
// have called the dfs function recursively. I have then returned 1 + the
// minimum of top, bottom, left and right.
class Solution {
    int[][] result;
    int m, n;

    public int[][] updateMatrix(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    result[i][j] = dfs(mat, i, j);
                }
            }
        }

        return result;
    }

    private int dfs(int[][] mat, int i, int j) {
        if (i > 0 && mat[i - 1][j] == 0) {
            return 1;
        }
        if (i < m - 1 && mat[i + 1][j] == 0) {
            return 1;
        }
        if (j > 0 && mat[i][j - 1] == 0) {
            return 1;
        }
        if (j < n - 1 && mat[i][j + 1] == 0) {
            return 1;
        }

        int top = 9999, bottom = 9999, left = 9999, right = 9999;
        if (i > 0 && result[i - 1][j] != 0) {
            top = result[i - 1][j];
        }
        if (j > 0 && result[i][j - 1] != 0) {
            left = result[i][j - 1];
        }
        if (i < m - 1) {
            if (result[i + 1][j] == 0) {
                result[i + 1][j] = dfs(mat, i + 1, j);
            }
            bottom = result[i + 1][j];
        }
        if (j < n - 1) {
            if (result[i][j + 1] == 0) {
                result[i][j + 1] = dfs(mat, i, j + 1);
            }
            right = result[i][j + 1];
        }
        return 1 + Math.min(top, Math.min(left, Math.min(bottom, right)));
    }
}
