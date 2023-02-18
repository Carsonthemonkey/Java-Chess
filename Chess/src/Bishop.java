
final class Bishop extends Piece{
    Bishop(int init_side, int init_rank, int init_file){
        super(init_side, init_rank, init_file, "Bishop");
        if(this.side == 0){this.fen_code = 'B';}
        else{this.fen_code = 'b';}
    }

    @Override
    boolean[][] getMoves(Piece[][] board) {
        boolean[][] valid_squares = new boolean[8][8];

        // loop left up
        for(int r = rank + 1, f = file - 1; r < 8 && f >= 0; r++, f--){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop right up
        for(int r = rank + 1, f = file + 1; r < 8 && f < 8; r++, f++){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop down left
        for(int r = rank - 1, f = file - 1; r >= 0 && f >= 0; r--, f--){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop down right
        for(int r = rank - 1, f = file + 1; r >= 0 && f < 8; r--, f++){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        return valid_squares;

    }

    boolean[][] getMoves(Piece[][] board, int rank, int file) {
        boolean[][] valid_squares = new boolean[8][8];

        // loop left up
        for(int r = rank + 1, f = file - 1; r < 8 && f >= 0; r++, f--){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop right up
        for(int r = rank + 1, f = file + 1; r < 8 && f < 8; r++, f++){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop down left
        for(int r = rank - 1, f = file - 1; r >= 0 && f >= 0; r--, f--){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        // loop down right
        for(int r = rank - 1, f = file + 1; r >= 0 && f < 8; r--, f++){
            if(board[r][f] != null){
                if(board[r][f].side != this.side){
                    valid_squares[r][f] = true;
                    break;
                }
                else{break;}
            }
            else{valid_squares[r][f] = true;}
        }

        return valid_squares;

    }
}
