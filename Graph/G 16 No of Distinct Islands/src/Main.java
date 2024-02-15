import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(distinctIsland(arr,4,5));
    }
    public static int distinctIsland(int [][] arr, int n, int m)
    {
        boolean[][] vis = new boolean[n][m];
        Set<String> set = new HashSet<>();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(!vis[i][j] && arr[i][j] == 1){
                    StringBuilder vec = new StringBuilder("");
                    dfs(i,j,arr,n,m,vis,vec,i,j);
                    System.out.println(set.contains(vec));
                    set.add(vec.toString());
                }

            }
        }
//        System.out.println(set);
        return set.size();
    }
    private static void dfs(int row,int col,int[][] arr,int n,int m,boolean[][] vis,StringBuilder vec,int row0,int col0){
        vis[row][col] = true;
        vec.append(row-row0).append(" ").append(col-col0);
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};
        for(int i = 0;i<4;i++){
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if(nrow>=0 && nrow<n && ncol >=0 && ncol<m && !vis[nrow][ncol] && arr[nrow][ncol] == 1){
                dfs(nrow,ncol,arr,n,m,vis,vec,row0,col0);
            }
        }
    }
}