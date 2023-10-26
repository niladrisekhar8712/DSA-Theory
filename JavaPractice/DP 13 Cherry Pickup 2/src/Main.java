import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int maximumChocolates(int r, int c, int[][] grid) {
        int[][][] dp = new int[r][c][c];
        for(int[][] i : dp){
            for(int[] j : i){
                Arrays.fill(j,-1);
            }
        }
        return recurr(0,0,c-1,grid,r,c,dp);
    }

    private static int recurr(int i,int j1,int j2,int[][] grid,int n,int m,int[][][] dp){
        if(j1<0 || j1>=m || j2<0 || j2>=m){
            return (int)-1e8;
        }
        if(i == n-1){
            if(j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        if(dp[i][j1][j2] != -1){
            return dp[i][j1][j2];
        }
        int maxi = 0;
        for(int dj1 = -1;dj1<=1;dj1++){
            for(int dj2 = -1;dj2<=1;dj2++){
                if(j1 == j2){
                    maxi = Math.max(maxi, grid[i][j1] + recurr(i+1,j1+dj1,j2+dj2,grid,n,m,dp));
                }
                else{
                    maxi = Math.max(maxi, grid[i][j2] + grid[i][j1] + recurr(i+1,j1+dj1,j2+dj2,grid,n,m,dp));
                }
            }
        }
        return dp[i][j1][j2] = maxi;
    }
    public static int tabulation()
}