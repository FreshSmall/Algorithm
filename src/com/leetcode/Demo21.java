package com.leetcode;

public class Demo21 {

    public void solve(char[][] board) {
        if(board.length<0){
            return;
        }
        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            DFS(board,i,0);
            DFS(board,i,col-1);
        }

        for(int i=0;i<col;i++){
            DFS(board,0,i);
            DFS(board,row-1,i);
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='*'){
                    board[i][j]='O';
                }
            }
        }
    }

    private void DFS(char[][] board, int row, int col) {
        if(row<0||row>board.length-1||col<0||col>board[0].length-1){
            return;
        }
        if(board[row][col]=='O'){
            board[row][col]='*';
            DFS(board,row-1,col);
            DFS(board,row,col-1);
            DFS(board,row+1,col);
            DFS(board,row,col+1);
        }

    }

    public static void main(String[] args) {
        int[][] test = {{1,2,3},{4,3,2,},{5,6,7},{6,8,7}};
//        System.out.println(test.length);
//        System.out.println(test[0].length);
    }
}
