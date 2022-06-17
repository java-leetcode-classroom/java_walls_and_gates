import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    final private Solution sol = new Solution();
    @Test
    void wallsAndGatesExample1() {
        int[][] rooms = new int[][]{
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
        };
        sol.wallsAndGates(rooms);
        assertArrayEquals(new int[][]{
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        }, rooms);
    }
    @Test
    void wallsAndGatesExample2() {
        int[][] rooms = new int[][]{
                {0, -1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE}
        };
        sol.wallsAndGates(rooms);
        assertArrayEquals(new int[][]{
                {0, -1},
                {1,2}
        }, rooms);
    }
}