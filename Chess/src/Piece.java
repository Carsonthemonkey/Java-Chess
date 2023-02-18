abstract class Piece{
    int rank;
    int file;

    int side;
    String name;

    char fen_code;

    String image;

    public Piece(int init_side, int init_rank, int init_file, String name) {
        this.rank = init_rank;
        this.file = init_file;
        this.side = init_side;
        if(this.side == 0){
            this.image = "white_" + name + ".png";
            this.name = "White " + name;
        }
        else{
            this.image = "black_" + name + ".png";
            this.name = "Black " + name;
        }
    }

    public void move(int rank, int file){
        this.rank = rank;
        this.file = file;
    }
    public void drawSelf() {
        StdDraw.picture((double)file/8 + 1/16f, (double)rank/8 + 1/16f,image,1/8f, 1/8f);
//        StdOut.println("Drawing " + this.name);
    }

    public String toString(){
        return this.name;
    }

    abstract boolean[][] getMoves(Piece[][] board);
    abstract boolean[][] getMoves(Piece[][] board, int rank, int file);
}