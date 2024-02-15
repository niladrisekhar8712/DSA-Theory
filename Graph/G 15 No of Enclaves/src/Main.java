import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] aaz = {{1,1,0,1},{0,1,0,1},{1,0,1,0},{1,0,1,1}};
        System.out.println(numEnclaves(aaz));
    }
    public static int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        for(int i = 0;i<n;i++){ // left
            if(grid[i][0] == 1 && !vis[i][0]){
                vis[i][0] = true;
                bfs(grid,vis,new Pair(i,0));
            }
            if(grid[i][m-1] == 1 && !vis[i][m-1]){
                vis[i][m-1] = true;
                bfs(grid,vis,new Pair(i,m-1));
            }
        }
        for(int i = 0;i<m;i++){ // left
            if(grid[0][i] == 1 && !vis[0][i]){
                vis[0][i] = true;
                bfs(grid,vis,new Pair(0,i));
            }
            if(grid[n-1][i] == 1 && !vis[n-1][i]){
                vis[n-1][i] = true;
                bfs(grid,vis,new Pair(n-1,i));
            }
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == 1 && !vis[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }
    private static void bfs(int[][] grid,boolean[][] vis, Pair start){
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()){
            Pair node = q.poll();
            int row = node.first;
            int col = node.second;
            if(row-1>=0 && !vis[row-1][col] &&  grid[row-1][col] == 1){   // top
                vis[row-1][col] = true;
                q.offer(new Pair(row-1,col));
            }
            if(col-1>=0 && !vis[row][col-1] &&  grid[row][col-1] == 1){   // left
                vis[row][col-1] = true;
                q.offer(new Pair(row,col-1));
            }
            if(row+1<=n-1 && !vis[row+1][col] &&  grid[row+1][col] == 1){   // bottom
                vis[row+1][col] = true;
                q.offer(new Pair(row+1,col));
            }
            if(col+1<=m-1 && !vis[row][col+1] &&  grid[row][col+1] == 1){   // right
                vis[row][col+1] = true;
                q.offer(new Pair(row,col+1));
            }
        }
    }
}