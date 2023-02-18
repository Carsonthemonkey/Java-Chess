import java.awt.*;
public class Chess {

    static void freeze_pawns(Piece[][] board, int side){
        for(Piece[] rank : board){
            for(Piece piece : rank){
                if(piece instanceof Pawn && piece.side == side){
                    ((Pawn) piece).just_moved = false;
                }
            }
        }

    }
    static void remove_en_passanted(Piece[][] board){
        for(int f = 0; f < 8; f++){
            // check fourth rank
            if(board[3][f] instanceof Pawn && board[3][f].side == 0 &&((Pawn) board[3][f]).just_moved
            && board[2][f] instanceof Pawn && board[2][f].side == 1 ){board[3][f] = null;}
            // check fifth rank
            if(board[4][f] instanceof Pawn && board[4][f].side == 1 &&((Pawn) board[4][f]).just_moved
            && board[5][f] instanceof Pawn && board[5][f].side == 0 ){board[4][f] = null;}
        }

    }
    static Piece[][] init_board(){
        Piece[][] board = new Piece[8][8];
        // test setups

        // assign white pawns
        for(int f = 0; f < board.length; f++){
            board[1][f] = new Pawn(0, 1, f);
        }

        // assign black pawns
        for(int f = 0; f < 8; f++){
            board[6][f] = new Pawn(1, 6, f);
        }

        // assign Kings
        board[0][4] = new King(0, 0, 4);
        board[7][4] = new King(1, 7, 4);

        // assign Queens
        board[0][3] = new Queen(0, 0, 3);
        board[7][3] = new Queen(1, 7, 3);

        // asign Bishops
        board[0][2] = new Bishop(0, 0, 2);
        board[0][5] = new Bishop(0, 0, 5);

        board[7][2] = new Bishop(1, 7, 2);
        board[7][5] = new Bishop(1, 7, 5);

        // assign Knights
        board[0][1] = new Knight(0, 0, 1);
        board[0][6] = new Knight(0, 0, 6);

        board[7][1] = new Knight(1, 7, 1);
        board[7][6] = new Knight(1, 7, 6);

        // assign Rooks
        board[0][0] = new Rook(0, 0, 0);
        board[0][7] = new Rook(0, 0, 7);

        board[7][0] = new Rook(1, 7, 0);
        board[7][7] = new Rook(1, 7, 7);

        return board;
    }

    static void init_graphics(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(700,700);
    }

    static void draw_board(Piece[][] board){
        StdDraw.clear(StdDraw.RED);
        int r0 = 255;
        int g0 = 214;
        int b0 = 201;

        int r1 = 100;
        int g1 = 0;
        int b1 = 0;

        StdDraw.setPenColor(r0,g0,b0);
        StdDraw.filledSquare(0.5,0.5,0.5);
        // draw black squares
        StdDraw.setPenColor(r1,g1,b1);
        int count = 0;
        for(double r = 1/16f; r < 1; r += 1/8f){
            for(double f = ((count % 2) / 8f) + 1/16f; f < 1; f += 2/8f){
                StdDraw.filledSquare(f, r, 1/16f);
            }
            count++;
        }

        // draw all pieces onto the board
        for(Piece[] rank : board){
            for(Piece piece : rank){
               // System.out.print(piece + "  ");
                if(piece != null) {
                    piece.drawSelf();
                }
            }
        }

        StdDraw.show();
    }

    static int[] get_square_from_mouse(){
        int[] location = new int[2];
        location[1] = (int)Math.floor(StdDraw.mouseX() * 8);
        location[0] = (int)Math.floor(StdDraw.mouseY() * 8);
        return location;
    }

    static void display_moves(boolean[][] moves){
        for(int rank = 0; rank < 8; rank++){
            for(int file = 0; file < 8; file++){
                StdDraw.setPenColor(Color.GREEN);
                if(moves[rank][file]){
                StdDraw.setPenRadius(0.01);
                StdDraw.square((double)file/8 + 1/16f, (double)rank/8 + 1/16f, 1/16f);}
            }
        }
        StdDraw.show();
    }

    static void promote(Piece piece, Piece[][] board){
        boolean just_clicked = true;

        StdOut.println("called promotion");
        Piece queen_button = new Queen(piece.side, piece.rank - 1 + piece.side * 2, piece.file);
        Piece rook_button = new Rook(piece.side, piece.rank - 2 + piece.side * 4, piece.file);
        Piece knight_button = new Knight(piece.side, piece.rank - 3 + piece.side * 6, piece.file);
        Piece bishop_button = new Bishop(piece.side, piece.rank - 4 + piece.side * 8, piece.file);

        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(piece.file/8f + 1/16f, (rook_button.rank + piece.side)/8f, 1/16f, 1/4f);

        queen_button.drawSelf();
        rook_button.drawSelf();
        knight_button.drawSelf();
        bishop_button.drawSelf();

        StdDraw.show();

        boolean promoted = false;
        while(!promoted){
            int[] mouse_squares = get_square_from_mouse();

            if(StdDraw.isMousePressed()){
                if(!just_clicked && mouse_squares[1] == piece.file){
                    if(mouse_squares[0] == queen_button.rank){board[piece.rank][piece.file] = new Queen(piece.side, piece.rank, piece.file); promoted = true;}
                    if(mouse_squares[0] == rook_button.rank){board[piece.rank][piece.file] = new Rook(piece.side, piece.rank, piece.file);promoted = true;}
                    if(mouse_squares[0] == knight_button.rank){board[piece.rank][piece.file] = new Knight(piece.side, piece.rank, piece.file);promoted = true;}
                    if(mouse_squares[0] == bishop_button.rank){board[piece.rank][piece.file] = new Bishop(piece.side, piece.rank, piece.file);promoted = true;}
                }
            }
            else{
                just_clicked = false;
            }

        }
    }
    static boolean is_check(Piece[][] board, int side, int rank, int file){

        // check for pawn
        if(side == 0){
            if(rank + 1 < 8 && file + 1 < 8 && board[rank + 1][file + 1] != null && board[rank+1][file + 1] instanceof Pawn
            && board[rank + 1][file + 1].side != side){
                return true;
            }
            else if (rank + 1 < 8 && file - 1 >= 0 && board[rank + 1][file - 1] != null && board[rank+1][file - 1] instanceof Pawn
            && board[rank + 1][file - 1].side != side){
                return true;
            }
        }
        else{
            if(rank - 1 >= 0 && file + 1 < 8 && board[rank - 1][file + 1] != null && board[rank - 1][file + 1] instanceof Pawn
                    && board[rank - 1][file + 1].side != side){
                return true;
            }
            else if (rank - 1 >= 0 && file - 1 >= 0 && board[rank - 1][file - 1] != null && board[rank - 1][file - 1] instanceof Pawn
                    && board[rank - 1][file - 1].side != side){
                return true;
            }
        }
        // check for Bishop and Queen diagonal

        // check up left
        for(int r = rank + 1, f = file - 1; r < 8 && f >= 0; r++, f--){
            if(board[r][f] != null){
                if((board[r][f] instanceof Bishop || board[r][f] instanceof Queen) && board[r][f].side != side){
                    return true;
                }
                else{
                    break;
                }
            }
        }

        // check up right
        for(int r = rank + 1, f = file + 1; r < 8 && f < 8; r++, f++){
            if(board[r][f] != null){
                if((board[r][f] instanceof Bishop || board[r][f] instanceof Queen) && board[r][f].side != side){
                    return true;
                }
                else{
                    break;
                }
            }
        }

        // check down left:
        for(int r = rank - 1, f = file - 1; r >= 0 && f >= 0; r--, f--){
            if(board[r][f] != null){
                if((board[r][f] instanceof Bishop || board[r][f] instanceof Queen) && board[r][f].side != side){
                    return true;
                }
                else{
                    break;
                }
            }
        }

        // check down right
        for(int r = rank - 1, f = file + 1; r >= 0 && f < 8; r--, f++){
            if(board[r][f] != null){
                if((board[r][f] instanceof Bishop || board[r][f] instanceof Queen) && board[r][f].side != side){
                    return true;
                }
                else{
                    break;
                }
            }
        }
        // check for Rook and Queen across

        // check up
        for(int r = rank + 1; r < 8; r++){
            if(board[r][file] != null) {
                if ((board[r][file] instanceof Rook || board[r][file] instanceof Queen) && board[r][file].side != side) {
                    return true;
                }
                else{break;}
            }
        }

        // check down
        for(int r = rank - 1; r >= 0; r--){
            if(board[r][file] != null) {
                if ((board[r][file] instanceof Rook || board[r][file] instanceof Queen) && board[r][file].side != side) {
                    return true;
                }
                else{break;}
            }
        }

        // check left
        for(int f = file - 1; f >= 0; f--){
            if(board[rank][f] != null) {
                if ((board[rank][f] instanceof Rook || board[rank][f] instanceof Queen) && board[rank][f].side != side) {
                    return true;
                }
                else{break;}
            }
        }

        // check right
        for(int f = file + 1; f < 8; f++){
            if(board[rank][f] != null) {
                if ((board[rank][f] instanceof Rook || board[rank][f] instanceof Queen) && board[rank][f].side != side) {
                    return true;
                }
                else{break;}
            }
        }

        // check for knight:

        // check up left
        if(rank + 1 < 8 && file - 2 >= 0 && board[rank + 1][file - 2] instanceof Knight && board[rank + 1][file - 2].side != side){return true;}
        if(rank + 2 < 8 && file - 1 >= 0 && board[rank + 2][file - 1] instanceof Knight && board[rank + 2][file - 1].side != side){return true;}

        // check up right
        if(rank + 1 < 8 && file + 2 < 8 && board[rank + 1][file + 2] instanceof Knight && board[rank + 1][file + 2].side != side){return true;}
        if(rank + 2 < 8 && file + 1 < 8 && board[rank + 2][file + 1] instanceof Knight && board[rank + 2][file + 1].side != side){return true;}

        // check down left
        if(rank - 1 >= 0 && file - 2 >= 0 && board[rank - 1][file - 2] instanceof Knight && board[rank - 1][file - 2].side != side){return true;}
        if(rank - 2 >= 0 && file - 1 >= 0 && board[rank - 2][file - 1] instanceof Knight && board[rank - 2][file - 1].side != side){return true;}

        // check down right
        if(rank - 1 >= 0 && file + 2 < 8 && board[rank - 1][file + 2] instanceof Knight && board[rank - 1][file + 2].side != side){return true;}
        if(rank - 2 >= 0 && file + 1 < 8 && board[rank - 2][file + 1] instanceof Knight && board[rank - 2][file + 1].side != side){return true;}

        // check for king
        for(int r = rank - 1; r <= rank + 1; r++){
            for(int f = file - 1; f <= file + 1; f++){
                if(f==file && r == rank){continue;}
                else if (r < 8 && r >= 0 && f < 8 && f >= 0 && board[r][f] instanceof King && board[r][f].side != side){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean is_checkmated(Piece[][] board, int side){
        // find king first
//        king_search:
//        for(Piece[] rank : board){
//            for(Piece piece : rank){
//                if(piece != null && piece instanceof King && piece.side == side){
//                    StdOut.println(is_check(board,side,piece.rank,piece.file));
//                    if(!is_check(board,side,piece.rank,piece.file)){return false;}
//                    else{StdOut.println("king is in check"); break king_search;}
//                }
//            }
//        }

        for(Piece[] rank : board){
            for(Piece piece : rank){
                if(piece == null || piece.side != side){continue;}
                else{
                    boolean[][] valid_moves = piece.getMoves(board);
                    prune_illegal_moves(piece,board,valid_moves);

                    for(boolean[] i : valid_moves){
                        for(boolean j : i){
                            if(j){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    static void prune_illegal_moves(Piece piece, Piece[][] board, boolean[][] valid_moves){
        // modifies argument valid_moves
        Piece[][] test_board = new Piece[8][8];
        // copy board into test board
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, test_board[i], 0, board[0].length);
        }

        for(int r = 0; r < 8; r++){
            for(int f = 0; f < 8; f++){
                // loop through all positions
                if(!valid_moves[r][f]){continue;}
                else{
                    // phantom move piece to proposed location
                    Piece saved_piece = test_board[r][f];
                    test_board[r][f] = piece;
                    test_board[piece.rank][piece.file] = null;

                    king_search:
                    for(int kr = 0; kr < 8; kr++){
                        for(int kf = 0; kf < 8; kf++){
                            //find king
                            if(test_board[kr][kf] != null && test_board[kr][kf] instanceof King && test_board[kr][kf].side == piece.side){
                                // found friendly king
                                if(is_check(test_board, piece.side, kr, kf)){valid_moves[r][f] = false;}
                                break king_search;
                            }
                        }
                    }
                    test_board[r][f] = saved_piece;
                    test_board[piece.rank][piece.file] = piece;
                }
            }
        }
    }

    static void take_move(int side, Piece[][] board, boolean computer_move){
        Piece selected_piece = null;
        boolean made_move = false;
        boolean just_clicked = true;

        while(!made_move){
            while(selected_piece == null){
                int[] mouse_square = get_square_from_mouse();

                if(StdDraw.isMousePressed() && board[mouse_square[0]][mouse_square[1]] != null && !just_clicked){
                    just_clicked = true;
                    selected_piece = board[mouse_square[0]][mouse_square[1]];
                    if (selected_piece.side != side){selected_piece = null;}
                }
                else if(!StdDraw.isMousePressed()){just_clicked = false;}
            }

            while(selected_piece != null && !made_move){
                int[] mouse_square = get_square_from_mouse();
                boolean[][] valid_moves = selected_piece.getMoves(board);
                prune_illegal_moves(selected_piece, board, valid_moves);
                display_moves(valid_moves);
                if(StdDraw.isMousePressed() && !just_clicked){
                    just_clicked = true;
                    if(!valid_moves[mouse_square[0]][mouse_square[1]]){
                        //StdOut.print("");
                        selected_piece = null;
                    }
                    else{
                        //StdOut.print("");
                        board[mouse_square[0]][mouse_square[1]] = selected_piece;
                        board[selected_piece.rank][selected_piece.file] = null;
                        if(selected_piece instanceof King && !((King) selected_piece).has_moved && mouse_square[1] == 2){
                            board[side * 7][3] = board[side * 7][0];
                            board[side * 7][0] = null;
                            board[side * 7][3].move(side * 7, 3);
                        }
                        else if(selected_piece instanceof King && !((King) selected_piece).has_moved && mouse_square[1] == 6){
                            board[side * 7][5] = board[side * 7][7];
                            board[side * 7][7] = null;
                            board[side * 7][5].move(side * 7, 5);
                        }
                        selected_piece.move(mouse_square[0],mouse_square[1]);
                        made_move = true;
                        selected_piece = null;
                        just_clicked = true;
                        // look for any unpromoted pawns
                        for(Piece piece : board[7 - side * 7]){
                            if(piece instanceof Pawn){
                                promote(piece, board);
                            }
                        }
                        // check for en passant
                        remove_en_passanted(board);
                        // freeze opposite side pawns
                        if(side == 1){freeze_pawns(board, 0);}
                        else{freeze_pawns(board, 1);}
                    }
                }
                else if(!StdDraw.isMousePressed()){
                    StdOut.print(""); //THIS HAS TO BE HERE OR ELSE IT WON'T WORK IDK WHY
                    just_clicked = false;
                }
                else{
                    StdOut.print(""); //THIS HAS TO BE HERE OR ELSE IT WON'T WORK IDK WHY
                    just_clicked = true;

                }
            }
            draw_board(board);
        }


    }

    static Piece[][][] get_all_legal_moves(Piece[][] board, int side){
        Piece[][][] board_positions = new Piece[296][8][8];
        for(int r = 0; r < 8; r++){
            for(int f = 0; f < 8; f++){
                if(board[r][f] != null){
                    boolean[][] valid_squares = board[r][f].getMoves(board,r,f);
                    // create possible board positions and add to array
                }
            }
        }
    return null;
    }

    /**
     * Returns the modified FEN code of the current board position. the first part is the same as standard fen.
     * following the description of the board position, we have whether white's queenside rook has moved (m or -)
     * then white's kingside rook, then white's king, then black's queenside rook, black's kingside rook and then black's king.
     * Next is the position of a pawn that has just moved using rank and then file (24) is 3rd rank, 5th file, or board[2][4]
     * if no pawn has just moved, this position will be --
     * @param board the board to be converted
     * @return a String of the position in modified FEN notation
     */
    static String board_to_fen(Piece[][] board){
        String fen = "";
        Piece moved_pawn = null;
        for(int r = 7; r >= 0; r--){
            int empty_space = 0;
            for(int f = 0; f < 8; f++){
                if(board[r][f] == null){empty_space++;}
                else{
                    if(board[r][f] instanceof Pawn && ((Pawn) board[r][f]).just_moved &&
                    (board[r][f].rank == 3 && board[r][f].side == 0)
                        || (board[r][f].rank == 4 && board[r][f].side == 1)){moved_pawn = board[r][f];}
                    if(empty_space > 0){fen += empty_space; empty_space = 0;}
                    fen += board[r][f].fen_code;
                }
            }
            if(empty_space > 0){fen += empty_space;}
            if(r > 0){fen += '/';}
        }
        fen += ' ';
        // check white queenside rook
        if(board[0][0] instanceof Rook && !((Rook) board[0][0]).has_moved){fen += '-';}
        else{fen += 'm';}

        // check white kingside rook
        if(board[0][7] instanceof Rook && !((Rook) board[0][7]).has_moved){fen += '-';}
        else{fen += 'm';}

        // check white king
        if(board[0][4] instanceof King && !((King) board[0][4]).has_moved){fen += '-';}
        else{fen += 'm';}

        fen += ' ';

        // check black queenside rook
        if(board[7][0] instanceof Rook && !((Rook) board[7][0]).has_moved){fen += '-';}
        else{fen += 'm';}

        // check black kingside rook
        if(board[7][7] instanceof Rook && !((Rook) board[7][7]).has_moved){fen += '-';}
        else{fen += 'm';}

        // check for black king
        if(board[7][4] instanceof King && !((King) board[7][4]).has_moved){fen += '-';}
        else{fen += 'm';}

        fen += ' ';

        // check for moved pawn
        if(moved_pawn != null){
            fen += moved_pawn.rank;
            fen += moved_pawn.file;
        }
        return fen;
    }

    static Piece[][] fen_to_board(String fen){
        int offset = 0;
        Piece[][] new_board = new Piece[8][8];
        int i = 0;
        int rank = 7;
        int file = 0;
        for(; i < fen.length(); i++){
            if(fen.charAt(i) == ' '){break;}
            else if(fen.charAt(i) >= '1' && fen.charAt(i) <= '8'){
                file += fen.charAt(i) - 48; i++;
            }
            else if(fen.charAt(i) == '/'){
                rank--;
                file = 0;
                i++;
            }

            else {
                rank = 7 - (int) Math.floor((double) i / 8);
                file = (int) ((double) i % 8);
            }
            // add piece to board
            // Pawns
            if(fen.charAt(i) == 'p'){new_board[rank][file] = new Pawn(1,rank,file);}
            else if(fen.charAt(i) == 'P'){new_board[rank][file] = new Pawn(0,rank,file);}
            //Rooks
            else if(fen.charAt(i) == 'r'){new_board[rank][file] = new Rook(1,rank,file); ((Rook)new_board[rank][file]).has_moved = true;}
            else if(fen.charAt(i) == 'R'){new_board[rank][file] = new Rook(0, rank, file); ((Rook)new_board[rank][file]).has_moved = true;}

            // Knights
            else if(fen.charAt(i) == 'n'){new_board[rank][file] = new Knight(1,rank,file);}
            else if(fen.charAt(i) == 'N'){new_board[rank][file] = new Knight(0, rank, file);}

            //Bishops
            else if(fen.charAt(i) == 'b'){new_board[rank][file] = new Bishop(1,rank,file);}
            else if(fen.charAt(i) == 'B'){new_board[rank][file] = new Bishop(0,rank,file);}

            //Queens
            else if(fen.charAt(i) == 'q'){new_board[rank][file] = new Queen(1,rank,file);}
            else if(fen.charAt(i) == 'Q'){new_board[rank][file] = new Queen(0,rank,file);}

            //Kings
            else if(fen.charAt(i) == 'k'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
            else if(fen.charAt(i) == 'K'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
            continue;
        }
//        board_place:
//        for(int rank = 7; rank >= 0; rank++){
//            for(int file = 0; file < 8; file++, i++){
//                //control where we are on the board
//
//                while(true){
//                    if(fen.charAt(i) == ' '){break board_place;}
//                    if(fen.charAt(i) >= '1' && fen.charAt(i) <= '8'){file += fen.charAt(i) - 48; i++; continue;}
//                    if(fen.charAt(i) == '/'){rank--; file = 0; i++; continue;}
//                    break;
//                }
//                if(fen.charAt(i) == 'p'){new_board[rank][file] = new Pawn(1,rank,file);}
//                else if(fen.charAt(i) == 'P'){new_board[rank][file] = new Pawn(0,rank,file);}
//                //Rooks
//                else if(fen.charAt(i) == 'r'){new_board[rank][file] = new Rook(1,rank,file); ((Rook)new_board[rank][file]).has_moved = true;}
//                else if(fen.charAt(i) == 'R'){new_board[rank][file] = new Rook(0, rank, file); ((Rook)new_board[rank][file]).has_moved = true;}
//
//                // Knights
//                else if(fen.charAt(i) == 'n'){new_board[rank][file] = new Knight(1,rank,file);}
//                else if(fen.charAt(i) == 'N'){new_board[rank][file] = new Knight(0, rank, file);}
//
//                //Bishops
//                else if(fen.charAt(i) == 'b'){new_board[rank][file] = new Bishop(1,rank,file);}
//                else if(fen.charAt(i) == 'B'){new_board[rank][file] = new Bishop(0,rank,file);}
//
//                //Queens
//                else if(fen.charAt(i) == 'q'){new_board[rank][file] = new Queen(1,rank,file);}
//                else if(fen.charAt(i) == 'Q'){new_board[rank][file] = new Queen(0,rank,file);}
//
//                //Kings
//                else if(fen.charAt(i) == 'k'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
//                else if(fen.charAt(i) == 'K'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
//
//            }
//        }
//        StdOut.println(board_to_fen(new_board));
        /*
        while(fen.charAt(i) != ' '){
            if(fen.charAt(i) == ' '){break;}
            int rank = (int)Math.floor((double)(i + offset)/8) * 8;
            int file = (i + offset) - (8 - rank) * 8;
            if(fen.charAt(i) >= '1' && fen.charAt(i) <= '8'){offset += fen.charAt(i) - 48;}

            // add piece to board
            // Pawns
            else if(fen.charAt(i) == 'p'){new_board[rank][file] = new Pawn(1,rank,file);}
            else if(fen.charAt(i) == 'P'){new_board[rank][file] = new Pawn(0,rank,file);}
            //Rooks
            else if(fen.charAt(i) == 'r'){new_board[rank][file] = new Rook(1,rank,file); ((Rook)new_board[rank][file]).has_moved = true;}
            else if(fen.charAt(i) == 'R'){new_board[rank][file] = new Rook(0, rank, file); ((Rook)new_board[rank][file]).has_moved = true;}

            // Knights
            else if(fen.charAt(i) == 'n'){new_board[rank][file] = new Knight(1,rank,file);}
            else if(fen.charAt(i) == 'N'){new_board[rank][file] = new Knight(0, rank, file);}

            //Bishops
            else if(fen.charAt(i) == 'b'){new_board[rank][file] = new Bishop(1,rank,file);}
            else if(fen.charAt(i) == 'B'){new_board[rank][file] = new Bishop(0,rank,file);}

            //Queens
            else if(fen.charAt(i) == 'q'){new_board[rank][file] = new Queen(1,rank,file);}
            else if(fen.charAt(i) == 'Q'){new_board[rank][file] = new Queen(0,rank,file);}

            //Kings
            else if(fen.charAt(i) == 'k'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
            else if(fen.charAt(i) == 'K'){new_board[rank][file] = new King(1,rank,file); ((King)new_board[rank][file]).has_moved = true;}
        }
        */
        StdOut.println(board_to_fen(new_board));
        if(fen.charAt(i + 1) == '-'){((Rook)new_board[0][0]).has_moved = false;}
        if(fen.charAt(i + 2) == '-'){((Rook)new_board[0][7]).has_moved = false;}
        if(fen.charAt(i + 3) == '-'){((Rook)new_board[0][4]).has_moved = false;}

        if(fen.charAt(i + 5) == '-'){((Rook)new_board[0][0]).has_moved = false;}
        if(fen.charAt(i + 6) == '-'){((Rook)new_board[0][7]).has_moved = false;}
        if(fen.charAt(i + 7) == '-'){((Rook)new_board[0][4]).has_moved = false;}

        if(fen.length() == i + 7 + 4){((Pawn)new_board[fen.charAt(i + 7 + 2)][fen.charAt(i + 7 + 3)]).just_moved = true;}
        return new_board;
    }

    static int get_material_count(Piece[][] board){
        int material = 0;
        if(is_checkmated(board, 0)){return -1000;} // black wins
        if(is_checkmated(board, 1)){return 1000;}

        for(Piece[] rank : board){
            for(Piece piece : rank){
                if(piece instanceof Pawn){material += 1 + (-2 * piece.side);}
                if(piece instanceof Knight || piece instanceof Bishop){material += 3 + (-6 * piece.side);}
                if(piece instanceof Rook){material += 5 + (-10 * piece.side);}
                if(piece instanceof Queen){material += 9 + (-18 * piece.side);}
            }
        }
        return material;
    }

    static String[] get_move_deltas(Piece[][] board, Piece piece){
        String[] move_deltas = new String[36]; //movedeltas[] is size 2 array of rank and file
        int move_count = 0;
        boolean[][] valid_moves = piece.getMoves(board);
        for(int r = 0; r < 8; r++){
            for(int f = 0; f < 8; f++){
                if(valid_moves[r][f]){
                    move_deltas[move_count] = piece.rank + "" + piece.file + "" + r + "" + f;
                    move_count++;
                }
            }
        }
        String[] move_deltas_final = new String[move_count];
        System.arraycopy(move_deltas, 0, move_deltas_final, 0, move_count);
        return move_deltas_final;
    }


    static double minimax(Piece[][] board, int original_rank, int original_file, int new_rank, int new_file,int depth, int side){
        if(is_checkmated(board, 1)){return 1000;}
        if(is_checkmated(board, 0)){return -1000;}
        if(depth == 0){return get_material_count(board);}
        // foreach piece
            // moves = get move deltas(board, piece)
        // return minimax(board, move deltas, depth - 1)
        Piece destination = board[new_rank][new_file];
        Piece original = board[original_rank][original_file];
        board[new_rank][new_file] = original;
        board[original_rank][original_file] = null;

        //original.move(new_rank,new_file);
        //draw_board(board);

        double evaluation;
        if(side == 0){ // white (return max)
            double max = -100;
            double eval;
            for(Piece[] rank : board){
                for(Piece piece : rank){
                    if(piece == null){continue;}
                    String[] deltas = get_move_deltas(board, piece);
                    for(String move : deltas){
                        eval = minimax(board, Character.getNumericValue(move.charAt(0)),
                                              Character.getNumericValue(move.charAt(1)),
                                              Character.getNumericValue(move.charAt(2)),
                                              Character.getNumericValue(move.charAt(3)),
                                              depth -1, 1);
                        if(eval > max){max = eval;}
                    }
                }
            }
            evaluation = max;
        }

        else{ // black (return min)
            double min = 100;
            double eval;
            for(Piece[] rank : board){
                for(Piece piece : rank){
                    if(piece == null){continue;}
                    String[] deltas = get_move_deltas(board, piece);
                    for(String move : deltas){
                        eval = minimax(board, Character.getNumericValue(move.charAt(0)),
                                Character.getNumericValue(move.charAt(1)),
                                Character.getNumericValue(move.charAt(2)),
                                Character.getNumericValue(move.charAt(3)),
                                depth -1, 0);
                        if(eval < min){min = eval;}
                    }
                }
            }
            evaluation = min;
        }

        board[original_rank][original_file] = original;
       // original.move(original_rank,original_file);
        board[new_rank][new_file] = destination;
        return evaluation;
    }

    static void computer_move(int side, Piece[][] board){
        double eval = 100;
        String best_move = null;
        for(Piece[] rank : board) {
            for (Piece piece : rank) {
                if(piece == null){continue;}
                if(piece.side != side){continue;}
                String[] deltas = get_move_deltas(board, piece);
                if(deltas.length < 1){continue;}
                //best_move = deltas[0];
                for (String move : deltas) {
                    double evaluation = minimax(board, Character.getNumericValue(move.charAt(0)),
                            Character.getNumericValue(move.charAt(1)),
                            Character.getNumericValue(move.charAt(2)),
                            Character.getNumericValue(move.charAt(3)),
                            3, 0);
                    StdOut.println(evaluation);
                    if (evaluation < eval) {
                        eval = evaluation;
                        best_move = move;
                    }
                }
            }
        }
        int old_rank = Character.getNumericValue(best_move.charAt(0));
        int old_file = Character.getNumericValue(best_move.charAt(1));
        int new_rank = Character.getNumericValue(best_move.charAt(2));
        int new_file = Character.getNumericValue(best_move.charAt(3));
        board[old_rank][old_file].move(new_rank, new_file);
        board[new_rank][new_file] = board[old_rank][old_file];
        board[old_rank][old_file] = null;
        draw_board(board);
        StdOut.println(eval);
    }

    public static void main(String[] args){
        // initialize variables
        boolean white_computer = false;
        boolean black_computer = false;
        init_graphics();
        Piece[][] board = init_board();
        draw_board(board);

        while(true){
            //StdOut.println(board_to_fen(board));
            //StdOut.println(board_to_fen(fen_to_board(board_to_fen(board))));
            take_move(0, board, white_computer);
            // check for white checkmate
            if(is_checkmated(board,1)){StdOut.println("CHECKMATE, WHITE WINS"); break;}

            //StdOut.println(get_move_deltas(board, board[0][0])[1]);
            //StdOut.println(board_to_fen(board));
            //StdOut.println(board_to_fen(fen_to_board(board_to_fen(board))));
            //computer_move(1, board);
            take_move(1, board, black_computer);
            // check for black checkmate
            if(is_checkmated(board,0)){StdOut.println("CHECKMATE, BLACK WINS"); break;}
        }

    }
}
