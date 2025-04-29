package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Practice
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        this.board.setViewingPerspective(side);
        int col = this.board.size();
        for(int i = 0; i < col; i ++){
            boolean b = this.tiltColumn(i);
            if(b){
                changed = true;
            }
        }
        this.board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /**
     * this method is a helper method for tilt method
     * it processes a single column of the board, since each column is handled independently
     */
    private boolean tiltColumn(int col){
        int row = this.board.size();
        int targetPosition = row - 1;
        boolean res = false;
        for(int i = row - 2; i >= 0; i --){
            if(this.board.tile(col, i) != null){
                Tile tile = this.board.tile(col, i);
                if(isNullOrSameValue(col, i, targetPosition)){
                    boolean move = this.board.move(col, targetPosition, tile);
                    res = true;
                    if(move){
                        this.score += this.board.tile(col, targetPosition).value();
                        targetPosition -= 1;
                    }
                }else{
                    if(i != targetPosition - 1){
                        this.board.move(col, targetPosition - 1, tile);
                        res = true;
                    }
                    targetPosition -= 1;
                }
            }
        }
        return res;
    }

    /**
     * this method is helper method for tiltColumn
     * it check the target position if empty or has the same value as current tile
     */
    private boolean isNullOrSameValue(int col, int curRow, int targetRow){
        if(this.board.tile(col, targetRow) == null){
            return true;
        }
        if(this.board.tile(col, targetRow).value() == this.board.tile(col, curRow).value()){
            return true;
        }
        return false;
    }

    /**
     * This method should move the tile at position (x, y) as far up in its
     * column as possible.Remember that a tile can move up through empty
     * squares, until the tile either reaches the top row, or the tile
     * reaches an empty square with another tile directly above it or the tile
     * with the same value
     */
    public void moveTileUpAsFarAsPossible(int x, int y){
        int top = this.board.size();
        Tile sourceTile = this.board.tile(x, y);
        if(sourceTile == null)return;
        for(int row = y; row < top - 1; row ++){
            Tile tile = this.board.tile(x, row + 1);
            if(tile == null)continue;
            else{
                if(tile.value() == sourceTile.value() && tile.next() == tile){
                    this.board.move(x, row + 1, sourceTile);
                }else this.board.move(x, row, sourceTile);
                return;
            }
        }
        this.board.move(x, top - 1, sourceTile);
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int row = b.size(), col = b.size();
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                if(b.tile(j, i) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        int row = b.size(), col = b.size();
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                Tile tile = b.tile(j, i);
                if(tile != null && tile.value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};
        int row = b.size(), col = b.size();

        if(emptySpaceExists(b))return true;
        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                for(int k = 0; k < 4; k ++){
                    int new_row = i + dx[k], new_col = j + dy[k];
                    if(new_row >= 0 && new_row < row && new_col >= 0 && new_col < col
                            && b.tile(new_col, new_row).value() == b.tile(j, i).value()){
                        return true;
                    }
                }
            }
        }

        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
