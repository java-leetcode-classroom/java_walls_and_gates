# java_walls_and_gates

You are given an m x n 2D grid initialized with these three possible values.

- `-1`-  A wall or an obstacle.
- `0` - A gate.
- `INF` - Infinity means an empty room.
- We use the value $`2^{31}$ - 1 = 2147483647` to represent INF as you may assume that the distance to a gate is less than `2147483647`.Fill each empty room with the distance to its nearest gate. If it is impossible to reach a `Gate`, that room should remain filled with `INF`

*Contact me on WeChat to get **Amazon、Google** request Interview questions . (wechat id : **jiuzhang0607**)*

## Examples

**Example1**

```
Input:
[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output:
[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

```

**Example2**

```
Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]

```

## 解析

類似於 [994. Rotting Oranges](https://www.notion.so/994-Rotting-Oranges-3c6396de56e74bef937c7038de36ea5f) 

題目給定了一個 m by n 整數矩陣 grid,

每個 grid[r][c] 有三種值

-1: 代表是一個牆或是阻礙物

0: 代表是一個門

INF： 這邊使用 $2^{31}$ -1 代表一個空房間

每個 cell 可以透過水平或垂直方向移動

實作一個演算法來找出每個 空房間到最近門的距離

類似的思路，可以先找出所有的門的座標

然後從門開始做對水平方向還有垂直方向做 BFS 更新每個 cell 的最短距離

直到 grid[r][c]  ≤ prevDist +1 就停止

否則更新 grid[r][c] = prevDist +1

![](https://i.imgur.com/YpQ0Xt4.png)


## 程式碼

```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static class Pair{
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
                new Pair(1, 0),
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
        while (queue.size() > 0) {
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

```
## 困難點

1. 理解如何透過BFS 累計目前到最近門的距離
2. 知道終止條件

## Solve Point

- [x]  理解如何透過BFS 累計目前到最近門的距離
- [x]  知道終止條件