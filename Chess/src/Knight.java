
final class Knight extends Piece{
    Knight(int init_side, int init_rank, int init_file){
        super(init_side,init_rank,init_file, "Knight");
        if(this.side == 0){this.fen_code = 'N';}
        else{this.fen_code = 'n';}
    }

    @Override
    boolean[][] getMoves(Piece[][] board) {
        boolean[][] valid_squares = new boolean[8][8];

        // below knight
        if((rank - 1 >= 0 && file + 2 < 8) && (board[rank -1][file + 2] == null || board[rank -1][file + 2].side != this.side)){
            valid_squares[rank - 1][file + 2] = true;
        }

        if((rank - 2 >= 0 && file + 1 < 8) && (board[rank - 2][file + 1] == null || board[rank -2][file + 1].side != this.side)){
            valid_squares[rank - 2][file + 1] = true;
        }

        if((rank - 1 >= 0 && file - 2 >= 0) && (board[rank - 1][file - 2] == null || board[rank -1][file - 2].side != this.side)){
            valid_squares[rank - 1][file - 2] = true;
        }

        if((rank - 2 >= 0 && file - 1 >= 0) && (board[rank - 2][file - 1] == null || board[rank -2][file - 1].side != this.side)){
            valid_squares[rank - 2][file - 1] = true;
        }

        // above knight
        if((rank + 1 < 8 && file + 2 < 8) && (board[rank + 1][file + 2] == null || board[rank +1][file + 2].side != this.side)){
            valid_squares[rank + 1][file + 2] = true;
        }

        if((rank + 2 < 8 && file + 1 < 8) && (board[rank + 2][file + 1] == null || board[rank +2][file + 1].side != this.side)){
            valid_squares[rank + 2][file + 1] = true;
        }

        if((rank + 1 < 8 && file - 2 >= 0) && (board[rank + 1][file - 2] == null || board[rank +1][file - 2].side != this.side)){
            valid_squares[rank + 1][file - 2] = true;
        }

        if((rank + 2 < 8 && file - 1 >= 0) && (board[rank + 2][file - 1] == null || board[rank +2][file - 1].side != this.side)){
            valid_squares[rank + 2][file - 1] = true;
        }

        return valid_squares;
    }

    boolean[][] getMoves(Piece[][] board, int rank, int file) {
        boolean[][] valid_squares = new boolean[8][8];

        // below knight
        if((rank - 1 >= 0 && file + 2 < 8) && (board[rank -1][file + 2] == null || board[rank -1][file + 2].side != this.side)){
            valid_squares[rank - 1][file + 2] = true;
        }

        if((rank - 2 >= 0 && file + 1 < 8) && (board[rank - 2][file + 1] == null || board[rank -2][file + 1].side != this.side)){
            valid_squares[rank - 2][file + 1] = true;
        }

        if((rank - 1 >= 0 && file - 2 >= 0) && (board[rank - 1][file - 2] == null || board[rank -1][file - 2].side != this.side)){
            valid_squares[rank - 1][file - 2] = true;
        }

        if((rank - 2 >= 0 && file - 1 >= 0) && (board[rank - 2][file - 1] == null || board[rank -2][file - 1].side != this.side)){
            valid_squares[rank - 2][file - 1] = true;
        }

        // above knight
        if((rank + 1 < 8 && file + 2 < 8) && (board[rank + 1][file + 2] == null || board[rank +1][file + 2].side != this.side)){
            valid_squares[rank + 1][file + 2] = true;
        }

        if((rank + 2 < 8 && file + 1 < 8) && (board[rank + 2][file + 1] == null || board[rank +2][file + 1].side != this.side)){
            valid_squares[rank + 2][file + 1] = true;
        }

        if((rank + 1 < 8 && file - 2 >= 0) && (board[rank + 1][file - 2] == null || board[rank +1][file - 2].side != this.side)){
            valid_squares[rank + 1][file - 2] = true;
        }

        if((rank + 2 < 8 && file - 1 >= 0) && (board[rank + 2][file - 1] == null || board[rank +2][file - 1].side != this.side)){
            valid_squares[rank + 2][file - 1] = true;
        }

        return valid_squares;
    }
}
