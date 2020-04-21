package leetcode.NumberOfIslands;


/**
 * 岛屿的数量
 */
public class Solution {
    /* 标记矩阵 */
    int[][] marks;


    public int numIslands(char[][] grid) {
        // 标记
        int mark = 1;
        // 位置
        int x;
        int y;
        // 长度
        int row = grid.length;
        // 最大宽度
        int column = 0;
        for (char[] chars : grid) {
            if (column < chars.length) {
                column = chars.length;
            }
        }
        marks = new int[row][column];

        for (x = 0; x < grid.length; x++) {
            for (y = 0; y < grid[x].length; y++) {
                if (marks[x][y] == 0) {
                    move(grid, x, y, mark);
                    // 只有当确定周边没有陆地的时候，mark才自增，必须保证mark不会重复增加
                    if (marks[x][y] == mark) {
                        mark++;
                    }
                }
            }
        }

        return --mark;
    }

    /*
    区域搜索：从一个点开始，对其上下左右进行搜索判断，如果是1，则进行标记。
     */
    public void move(char[][] grid, int x, int y, int mark) {
        if (grid[x][y] == '1' && marks[x][y] == 0) {
            marks[x][y] = mark;

            // move down
            if (y + 1 < grid[x].length && grid[x][y + 1] == '1') {
                move(grid, x, y + 1, mark);
            }
            // move up
            if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                move(grid, x, y - 1, mark);
            }
            // move left
            if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                move(grid, x - 1, y, mark);
            }
            // move right
            if (x + 1 < grid.length && grid[x + 1][y] == '1') {
                move(grid, x + 1, y, mark);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };

        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        char[][] grid2 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };

        System.out.println(new Solution().numIslands(grid2));
    }

}
