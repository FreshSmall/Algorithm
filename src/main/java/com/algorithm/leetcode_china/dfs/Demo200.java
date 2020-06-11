package com.algorithm.leetcode_china.dfs;

/**
 * Created by IntelliJ IDEA.
 * 岛屿数量
 *
 * @Author : yinchao
 * @create 2020/6/10 10:56 上午
 */
public class Demo200 {

    public int numIslands(char[][] grid) {
        int len = grid.length;
        int col = grid[0].length;
        int num_Islands = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    num_Islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return num_Islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length || j > grid[0].length||grid[i][j] == '0') {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i + 1, j);
            dfs(grid,i-1,j);
            dfs(grid, i, j + 1);
            dfs(grid,i,j-1);

        }
    }
}
