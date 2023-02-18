
final class King extends Piece{
    boolean has_moved;
    King(int init_side, int init_rank, int init_file){
        super(init_side,init_rank,init_file, "King");
        has_moved = false;
        if(this.side == 0){this.fen_code = 'K';}
        else{this.fen_code = 'k';}
    }

    @Override
    public void move(int rank, int file) {
        super.move(rank, file);
        has_moved = true;
    }

    @Override
    boolean[][] getMoves(Piece[][] board) {
        boolean[][] valid_squares = new boolean[8][8];

        for(int r = -1; r <= 1; r++){
            for(int f = -1; f <= 1; f++){
                if(r + rank < 8 && r + rank >= 0 && f + file < 8 && f + file >= 0){
                    if(board[rank + r][file + f] == null){valid_squares[rank + r][file + f] = true;}
                    else if(board[rank + r][file + f].side != this.side){valid_squares[rank + r][file + f] = true;}
                }
            }
        }
        // castling for white
        if(side == 0){
            // white kingside
            if(!this.has_moved && board[0][7] != null && board[0][7] instanceof Rook && !((Rook) board[0][7]).has_moved
                    && board[0][6] == null
                    && !Chess.is_check(board,0,0,6)
                    && board[0][5] == null && !Chess.is_check(board,0,0,5)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[0][6] = true;}
            // white queenside
            if(!this.has_moved && board[0][0] != null && board[0][0] instanceof Rook && !((Rook) board[0][0]).has_moved
                    && board[0][3] == null && !Chess.is_check(board,0,0,3)
                    && board[0][2] == null && !Chess.is_check(board,0,0,2)
                    && board[0][1] == null && !Chess.is_check(board,0,0,1)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[0][2] = true;}
        }
        // castling for black
        else{
            // black kingside
            if(!this.has_moved && board[7][7] != null && board[7][7] instanceof Rook && !((Rook) board[7][7]).has_moved && board[7][6] == null
                    && !Chess.is_check(board,1,7,6) && board[7][5] == null && !Chess.is_check(board,1,7,5)
                    && !Chess.is_check(board,side,rank,this.file)){
                valid_squares[7][6] = true;
            }
            // black queenside
            if(!this.has_moved && board[7][0] != null && board[7][0] instanceof Rook && !((Rook) board[7][0]).has_moved
                    && board[7][3] == null && !Chess.is_check(board,1,7,3)
                    && board[7][2] == null && !Chess.is_check(board,1,7,2)
                    && board[7][1] == null && !Chess.is_check(board,1,7,1)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[7][2] = true;}
        }
        return valid_squares;
    }

    boolean[][] getMoves(Piece[][] board, int rank, int file) {
        boolean[][] valid_squares = new boolean[8][8];

        for(int r = -1; r <= 1; r++){
            for(int f = -1; f <= 1; f++){
                if(r + rank < 8 && r + rank >= 0 && f + file < 8 && f + file >= 0){
                    if(board[rank + r][file + f] == null){valid_squares[rank + r][file + f] = true;}
                    else if(board[rank + r][file + f].side != this.side){valid_squares[rank + r][file + f] = true;}
                }
            }
        }
        // castling for white
        if(side == 0){
            // white kingside
            if(!this.has_moved && board[0][7] != null && board[0][7] instanceof Rook && !((Rook) board[0][7]).has_moved
                    && board[0][6] == null
                    && !Chess.is_check(board,0,0,6)
                    && board[0][5] == null && !Chess.is_check(board,0,0,5)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[0][6] = true;}
            // white queenside
            if(!this.has_moved && board[0][0] != null && board[0][0] instanceof Rook && !((Rook) board[0][0]).has_moved
                    && board[0][3] == null && !Chess.is_check(board,0,0,3)
                    && board[0][2] == null && !Chess.is_check(board,0,0,2)
                    && board[0][1] == null && !Chess.is_check(board,0,0,1)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[0][2] = true;}
        }
        // castling for black
        else{
            // black kingside
            if(!this.has_moved && board[7][7] != null && board[7][7] instanceof Rook && !((Rook) board[7][7]).has_moved && board[7][6] == null
                    && !Chess.is_check(board,1,7,6) && board[7][5] == null && !Chess.is_check(board,1,7,5)
                    && !Chess.is_check(board,side,rank,this.file)){
                valid_squares[7][6] = true;
            }
            // black queenside
            if(!this.has_moved && board[7][0] != null && board[7][0] instanceof Rook && !((Rook) board[7][0]).has_moved
                    && board[7][3] == null && !Chess.is_check(board,1,7,3)
                    && board[7][2] == null && !Chess.is_check(board,1,7,2)
                    && board[7][1] == null && !Chess.is_check(board,1,7,1)
                    && !Chess.is_check(board,this.side,rank,file)){valid_squares[7][2] = true;}
        }
        return valid_squares;
    }
}