import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int first;
    int second;
    int time;

    public Pair(int first, int second, int time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }
}
public class Main {
    public static void main(String[] args) {
//        int[][] mat = {{2,1,1},{1,1,0},{0,1,1}};
        int[][] mat = {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(orangesRotting(mat));
    }
    public static int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[n][m];
        int time = 0;
        for(int i = 0;i< grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 2){
                    q.add(new Pair(i,j,0));
                    vis[i][j] = grid[i][j];
                }
            }
        }
        while(!q.isEmpty()){
            Pair node = q.poll();
            if(node.first - 1>=0 && grid[node.first-1][node.second] == 1 && vis[node.first-1][node.second] != 2){ // top
                vis[node.first-1][node.second] = 2;
                q.offer(new Pair(node.first-1, node.second, node.time+1));
                time = Math.max(time,node.time+1);
            }
            if(node.second - 1>=0 && grid[node.first][node.second-1] == 1 && vis[node.first][node.second-1] != 2){ // left
                vis[node.first][node.second-1] = 2;
                q.offer(new Pair(node.first, node.second-1, node.time+1));
                time = Math.max(time,node.time+1);
            }
            if(node.first + 1<=n-1 && grid[node.first+1][node.second] == 1 && vis[node.first+1][node.second] != 2){ // bottom
                vis[node.first+1][node.second] = 2;
                q.offer(new Pair(node.first+1, node.second, node.time+1));
                time = Math.max(time,node.time+1);
            }
            if(node.second + 1<=m-1 && grid[node.first][node.second+1] == 1 && vis[node.first][node.second+1] != 2){ // right
                vis[node.first][node.second+1] = 2;
                q.offer(new Pair(node.first, node.second+1, node.time+1));
                time = Math.max(time,node.time+1);
            }
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == 1 && vis[i][j] != 2){
                    return -1;
                }
            }
        }
        return time;
    }
}