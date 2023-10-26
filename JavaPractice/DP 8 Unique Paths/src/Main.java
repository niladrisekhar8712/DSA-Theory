import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(uniquePaths(6,7));
        System.out.println(tabulation(6,7));
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return memoization(m-1,n-1,dp);
    }
    private static int memoization(int i, int j, int[][] dp){
        if(i == 0 && j == 0){
            return 1;
        }
        if(i < 0 || j < 0){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int up = memoization(i-1,j,dp);
        int down = memoization(i,j-1,dp);
        dp[i][j] = up + down;
        return up + down;
    }

    private static int recurr(int i, int j){
        if(i == 0 && j == 0){
            return 1;
        }
        if(i < 0 || j < 0){
            return 0;
        }
        int up = recurr(i-1,j);
        int down = recurr(i,j-1);
        return up + down;
    }
    public static int tabulation(int m,int n){
        int[][] dp = new int[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(i == 0 && j == 0){
                    dp[0][0] = 1;
                }
                else{
                    int up = 0;
                    int down = 0;
                    if(i>0) up = dp[i-1][j];
                    if(j>0) down = dp[i][j-1];
                    dp[i][j] = up + down;
                }
            }
        }
        return dp[m-1][n-1];
    }
}