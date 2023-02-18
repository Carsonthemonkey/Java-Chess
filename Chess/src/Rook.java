
final class Rook extends Piece{
    boolean has_moved;
    Rook(int init_side, int init_rank, int init_file){
        super(init_side,init_rank,init_file, "Rook");
        has_moved = false;
        if(this.side == 0){this.fen_code = 'R';}
        else{this.fen_code = 'r';}
    }

    @Override
    public void move(int rank, int file) {
        super.move(rank, file);
        has_moved = true;
    }

    @Override
    boolean[][] getMoves(Piece[][] board) {
        boolean[][] valid_squares = new boolean[8][8];

        // loop left
        for(int f = file - 1; f >= 0; f--){
            if(board[rank][f] != null){
                // enemy piece
                if(board[rank][f].side != this.side){
                    valid_squares[rank][f] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[rank][f] = true;}
        }

        // loop right
        for(int f = file + 1; f < 8; f++){
            if(board[rank][f] != null){
                // enemy piece
                if(board[rank][f].side != this.side){
                    valid_squares[rank][f] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[rank][f] = true;}
        }

        // loop up

        for(int r = rank + 1; r < 8; r++){
            if(board[r][file] != null){
                // enemy piece
                if(board[r][file].side != this.side){
                    valid_squares[r][file] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[r][file] = true;}
        }

        // loop down
        for(int r = rank - 1; r >= 0; r--){
            if(board[r][file] != null){
                // enemy piece
                if(board[r][file].side != this.side){
                    valid_squares[r][file] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[r][file] = true;}
        }

        return valid_squares;
    }

    boolean[][] getMoves(Piece[][] board, int rank, int file) {
        boolean[][] valid_squares = new boolean[8][8];

        // loop left
        for(int f = file - 1; f >= 0; f--){
            if(board[rank][f] != null){
                // enemy piece
                if(board[rank][f].side != this.side){
                    valid_squares[rank][f] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[rank][f] = true;}
        }

        // loop right
        for(int f = file + 1; f < 8; f++){
            if(board[rank][f] != null){
                // enemy piece
                if(board[rank][f].side != this.side){
                    valid_squares[rank][f] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[rank][f] = true;}
        }

        // loop up

        for(int r = rank + 1; r < 8; r++){
            if(board[r][file] != null){
                // enemy piece
                if(board[r][file].side != this.side){
                    valid_squares[r][file] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[r][file] = true;}
        }

        // loop down
        for(int r = rank - 1; r >= 0; r--){
            if(board[r][file] != null){
                // enemy piece
                if(board[r][file].side != this.side){
                    valid_squares[r][file] = true;
                    break;
                }
                // friendly piece
                else{break;}
            }
            // empty
            else{valid_squares[r][file] = true;}
        }

        return valid_squares;
    }
}
