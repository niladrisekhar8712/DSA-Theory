import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        System.out.println("Hello world!");
    }
    public static int numIslands(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int count = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(!vis[i][j] && grid[i][j] == 1){
                    count++;
                    BFS(i,j,vis,grid);
                }
            }
        }
        return count;
    }
    public static void BFS(int row, int col,boolean[][] vis, int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row,col));
        vis[row][col] = true;
        while(!q.isEmpty()){
            int r = q.peek().first;
            int c = q.poll().second;
            for(int delrow = -1;delrow<=1;delrow++){
                for(int delcom = -1;delcom<=1;delcom++){
                    int nrow = r + delrow;
                    int ncol = c + delcom;
                    if(nrow>=0 && nrow <n && ncol>=0 && ncol<n && grid[nrow][ncol] == 1 && !vis[nrow][ncol]){
                        vis[nrow][ncol] = true;
                        q.offer(new Pair(nrow,ncol));
                    }
                }
            }
        }
    }

}