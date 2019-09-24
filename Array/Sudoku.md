Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.



map--
set--
array-> '1'-'1' locate index

class Solution{
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][][] boxCheck = new boolean[3][3][9];
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){                
                if(board[i][j]!='.'){
                    if(rowCheck[i][board[i][j] - '1'] || colCheck[j][board[i][j] - '1'] || boxCheck[i/3][j/3][board[i][j]-'1']){
                        return false;
                    }else{
                        rowCheck[i][board[i][j] - '1'] = true;
                        colCheck[j][board[i][j] - '1'] = true;
                        boxCheck[i/3][j/3][board[i][j]-'1'] = true;
                    }
                       
                }
            }
        }
        
        return true;
    }
}
