package Algorithms;

/**
 * Created by luoxianzhuo on 2018/8/21 15:06
 *
 * @author luoxianzhuo
 * @copyright Copyright 2014-2018 JD.COM All Right Reserved
 */

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *示例 1:
 输入:
 11110
 11010
 11000
 00000
 输出: 1

 示例 2:
 输入:
 11000
 11000
 00100
 00011
 输出: 3
 
 解法：这道求岛屿数量的题的本质是求矩阵中连续区域的个数，很容易想到需要用深度优先搜索DFS来解，
 需要建立一个visited数组用来记录某个位置是否被访问过，对于一个为‘1’且未被访问过的位置，
 递归进入其上下左右位置上为‘1’的数，将其visited对应值赋为0，继续进入其所有相连的邻位置，
 可以将这个连通区域所有的数找出来，并将其对应的visited中的值赋0，找完次区域后，将结果res自增1，
 然后再继续找下一个为‘1’且未被访问过的位置，以此类推直至遍历完整个原数组即可得到最终结果
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                // 注意char
                if (grid[i][j] == '1') {
                    count++;
                    dfsSearch(grid, i, j, rows, cols);
                }
        }
        return count++;
    }

    // 每遇到'1'后, 开始向四个方向 递归搜索. 搜到后变为'0',
    // 因为相邻的属于一个island. 然后开始继续找下一个'1'.
    private void dfsSearch(char[][] grid, int i, int j, int rows, int cols) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) return;
        if (grid[i][j] != '1') return;

        // 也可以才用一个visited数组，标记遍历过的岛屿
        grid[i][j] = '0';
        dfsSearch(grid, i + 1, j, rows, cols);
        dfsSearch(grid, i - 1, j, rows, cols);
        dfsSearch(grid, i, j + 1, rows, cols);
        dfsSearch(grid, i, j - 1, rows, cols);

    }

}