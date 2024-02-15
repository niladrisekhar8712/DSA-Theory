import java.util.Arrays;
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
        char[][] arr = {{'X','O','X','X'},{'X','O','X','X'},{'X','O','O','X'},{'X','X','O','O'}};
        solve(arr);
        System.out.println(Arrays.deepToString(arr));
    }
    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        for(int i = 0;i<n;i++){ // left
            if(board[i][0] == 'O' && !vis[i][0]){
                vis[i][0] = true;
                bfs(board,vis,new Pair(i,0));
            }
            if(board[i][m-1] == 'O' && !vis[i][m-1]){
                vis[i][m-1] = true;
                bfs(board,vis,new Pair(i,m-1));
            }
        }
        for(int i = 0;i<m;i++){ // left
            if(board[0][i] == 'O' && !vis[0][i]){
                vis[0][i] = true;
                bfs(board,vis,new Pair(0,i));
            }
            if(board[n-1][i] == 'O' && !vis[n-1][i]){
                vis[n-1][i] = true;
                bfs(board,vis,new Pair(n-1,i));
            }
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(board[i][j] == 'O' && !vis[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
    private static void bfs(char[][] board,boolean[][] vis, Pair start){
        int n = board.length;
        int m = board[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()){
            Pair node = q.poll();
            int row = node.first;
            int col = node.second;
            if(row-1>=0 && !vis[row-1][col] &&  board[row-1][col] == 'O'){   // top
                vis[row-1][col] = true;
                q.offer(new Pair(row-1,col));
            }
            if(col-1>=0 && !vis[row][col-1] &&  board[row][col-1] == 'O'){   // left
                vis[row][col-1] = true;
                q.offer(new Pair(row,col-1));
            }
            if(row+1<=n-1 && !vis[row+1][col] &&  board[row+1][col] == 'O'){   // bottom
                vis[row+1][col] = true;
                q.offer(new Pair(row+1,col));
            }
            if(col+1<=m-1 && !vis[row][col+1] &&  board[row][col+1] == 'O'){   // right
                vis[row][col+1] = true;
                q.offer(new Pair(row,col+1));
            }
        }
    }
}