import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int first;
    int second;
    int dist;

    public Pair(int first, int second, int dist) {
        this.first = first;
        this.second = second;
        this.dist = dist;
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] arr = {{0,0,0},{0,1,0},{1,1,1}};
        System.out.println(Arrays.deepToString(updateMatrix(arr)));
    }
    public static int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] vis = new boolean[n][m];
        int[][] dist = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(mat[i][j] == 0){
                    q.add(new Pair(i,j,0));
                    vis[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()){
            Pair node = q.poll();
            int row = node.first;
            int col = node.second;
            int distance = node.dist;
            dist[row][col] = distance;
            if(row-1>=0 && !vis[row-1][col]){   // top
                vis[row-1][col] = true;
                q.offer(new Pair(row-1,col,distance+1));
            }
            if(col-1>=0 && !vis[row][col-1]){   // left
                vis[row][col-1] = true;
                q.offer(new Pair(row,col-1,distance+1));
            }
            if(row+1<=n-1 && !vis[row+1][col]){   // bottom
                vis[row+1][col] = true;
                q.offer(new Pair(row+1,col,distance+1));
            }
            if(col+1<=m-1 && !vis[row][col+1]){   // right
                vis[row-1][col] = true;
                q.offer(new Pair(row,col+1,distance+1));
            }
        }
        return dist;
    }
}