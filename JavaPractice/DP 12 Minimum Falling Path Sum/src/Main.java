public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for(int j = 0;j<n;j++){
            dp[n-1][j] = matrix[n-1][j];
        }
        for(int i = n-2;i>=0;i--){
            for(int j = 0;j<n;j++){
                int down = matrix[i][j] + dp[i+1][j];

                int downRight = Integer.MAX_VALUE;
                if(j<n-1) downRight = matrix[i][j]+ dp[i+1][j+1];

                int downLeft = Integer.MAX_VALUE;
                if(j>0) downLeft = matrix[i][j] + dp[i+1][j-1];
                dp[i][j] = Math.min(down,Math.min(downLeft,downRight));
            }
        }
        int index = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<n;i++){
            if(dp[0][i]<min){
                index = i;
                min = dp[0][i];
            }
        }
        return dp[0][index];
    }
}