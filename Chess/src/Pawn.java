final class Pawn extends Piece{
    boolean just_moved = false;
    Pawn(int init_side, int init_rank, int init_file){
        super(init_side,init_rank,init_file, "Pawn");
        if(this.side == 0){this.fen_code = 'P';}
        else{this.fen_code = 'p';}
    }

    @Override
    public void move(int rank, int file) {
        super.move(rank, file);
        just_moved = true;
    }

    @Override
    boolean[][] getMoves(Piece[][] board) {
        // en passant still need to be implemented
        boolean[][] valid_squares = new boolean[8][8];

        if(this.side == 0){ // check moves for white pawn

            // check for square in front:
            if(rank + 1 < 8 && board[rank + 1][file] == null){
                valid_squares[rank + 1][file] = true;
                // check for moving 2 squares forward (on initial move)
                if(rank == 1 && board[rank + 2][file] == null){valid_squares[rank + 2][file] = true;}
            }



            // check for piece taking on either side
            // rank + 1 < 8 shouldn't be necessary once promotion is implemented
            if(file - 1 >= 0 && rank + 1 < 8 && board[rank + 1][file - 1] != null && board[rank + 1][file - 1].side == 1){
                valid_squares[rank + 1][file -1] = true;}

            if(file + 1 < 8 && rank + 1 < 8 && board[rank + 1][file + 1] != null && board[rank + 1][file + 1].side == 1){
                valid_squares[rank + 1][file + 1] = true;}

            // checks for en passant on either side
            if(this.rank == 4){
                if(file - 1 >= 0 && board[rank][file - 1] instanceof Pawn && ((Pawn) board[rank][file - 1]).just_moved){
                    valid_squares[rank + 1][file - 1] = true;
                }
                else if(file + 1 < 8 && board[rank][file + 1] instanceof Pawn && ((Pawn) board[rank][file + 1]).just_moved){
                    valid_squares[rank + 1][file + 1] = true;
                }
            }
        }

        if(this.side == 1){ // check moves for black pawn
            // check for square in front:
            if(rank - 1 >= 0 && board[rank - 1][file] == null){
                valid_squares[rank - 1][file] = true;
                // check for moving 2 squares forward (on initial move)
                if(rank == 6 && board[rank - 2][file] == null){valid_squares[rank - 2][file] = true;}
            }



            // check for piece taking on either side
            // rank - 1 >= 0 shouldn't be necessary once promotion is implemented
            if(file - 1 >= 0 && rank - 1 >= 0 && board[rank - 1][file - 1] != null && board[rank - 1][file - 1].side == 0){
                valid_squares[rank - 1][file -1] = true;}

            if(file + 1 < 8 && rank - 1 >= 0 && board[rank - 1][file + 1] != null && board[rank - 1][file + 1].side == 0){
                valid_squares[rank - 1][file + 1] = true;}

            // checks for en passant on either side
            if(this.rank == 3){
                if(file - 1 >= 0 && board[rank][file - 1] instanceof Pawn && ((Pawn) board[rank][file - 1]).just_moved){
                    valid_squares[rank - 1][file - 1] = true;
                }
                else if(file + 1 < 8 && board[rank][file + 1] instanceof Pawn && ((Pawn) board[rank][file + 1]).just_moved){
                    valid_squares[rank - 1][file + 1] = true;
                }
            }
        }
        return valid_squares;
    }


    boolean[][] getMoves(Piece[][] board, int rank, int file) {
        // en passant still need to be implemented
        boolean[][] valid_squares = new boolean[8][8];

        if(this.side == 0){ // check moves for white pawn

            // check for square in front:
            if(rank + 1 < 8 && board[rank + 1][file] == null){
                valid_squares[rank + 1][file] = true;
                // check for moving 2 squares forward (on initial move)
                if(rank == 1 && board[rank + 2][file] == null){valid_squares[rank + 2][file] = true;}
            }



            // check for piece taking on either side
            // rank + 1 < 8 shouldn't be necessary once promotion is implemented
            if(file - 1 >= 0 && rank + 1 < 8 && board[rank + 1][file - 1] != null && board[rank + 1][file - 1].side == 1){
                valid_squares[rank + 1][file -1] = true;}

            if(file + 1 < 8 && rank + 1 < 8 && board[rank + 1][file + 1] != null && board[rank + 1][file + 1].side == 1){
                valid_squares[rank + 1][file + 1] = true;}

            // checks for en passant on either side
            if(this.rank == 4){
                if(file - 1 >= 0 && board[rank][file - 1] instanceof Pawn && ((Pawn) board[rank][file - 1]).just_moved){
                    valid_squares[rank + 1][file - 1] = true;
                }
                else if(file + 1 < 8 && board[rank][file + 1] instanceof Pawn && ((Pawn) board[rank][file + 1]).just_moved){
                    valid_squares[rank + 1][file + 1] = true;
                }
            }
        }

        if(this.side == 1){ // check moves for black pawn
            // check for square in front:
            if(rank - 1 >= 0 && board[rank - 1][file] == null){
                valid_squares[rank - 1][file] = true;
                // check for moving 2 squares forward (on initial move)
                if(rank == 6 && board[rank - 2][file] == null){valid_squares[rank - 2][file] = true;}
            }



            // check for piece taking on either side
            // rank - 1 >= 0 shouldn't be necessary once promotion is implemented
            if(file - 1 >= 0 && rank - 1 >= 0 && board[rank - 1][file - 1] != null && board[rank - 1][file - 1].side == 0){
                valid_squares[rank - 1][file -1] = true;}

            if(file + 1 < 8 && rank - 1 >= 0 && board[rank - 1][file + 1] != null && board[rank - 1][file + 1].side == 0){
                valid_squares[rank - 1][file + 1] = true;}

            // checks for en passant on either side
            if(this.rank == 3){
                if(file - 1 >= 0 && board[rank][file - 1] instanceof Pawn && ((Pawn) board[rank][file - 1]).just_moved){
                    valid_squares[rank - 1][file - 1] = true;
                }
                else if(file + 1 < 8 && board[rank][file + 1] instanceof Pawn && ((Pawn) board[rank][file + 1]).just_moved){
                    valid_squares[rank - 1][file + 1] = true;
                }
            }
        }
        return valid_squares;
    }
}