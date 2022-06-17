import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static class Pair{
        private final int row;
        private final int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        int ROW = rooms.length;
        int COL = rooms[0].length;
        Queue<Pair> queue = new LinkedList<>();
        Pair[] directions = new Pair[]{
                new Pair(1,0),
                new Pair(-1, 0),
                new Pair(0, -1),
                new Pair(0, 1)
        };
        // collect doors
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (rooms[row][col] == 0) {
                    queue.add(new Pair(row, col));
                }
            }
        }
        // do bfs from doors
        while(queue.size() > 0) {
            int qLen = queue.size();
            for (int idx = 0; idx < qLen; idx++) {
                Pair top = queue.poll();
                if (top == null) {
                    continue;
                }
                for (Pair direction : directions) {
                    int shiftedRow = top.row + direction.row;
                    int shiftedCol = top.col + direction.col;
                    if (shiftedRow < 0 || shiftedRow >= ROW || shiftedCol < 0 || shiftedCol >= COL ||
                            rooms[shiftedRow][shiftedCol] <= rooms[top.row][top.col] + 1
                    ) {
                        continue;
                    }
                    rooms[shiftedRow][shiftedCol] = rooms[top.row][top.col] + 1;
                    queue.add(new Pair(shiftedRow, shiftedCol));
                }
            }
        }
    }
}
