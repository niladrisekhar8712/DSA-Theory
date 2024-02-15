import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
        int[] arr = {1,2,3,4};
        System.out.println(subsetSum(4,arr));
    }
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
//                if(i == 0 && j == 0){
//                    dp[i][j] = 1;
//                }
                int top = 0, left = 0;
                if(i>0) top = dp[i-1][j];
                if(j>0) left = dp[i][j-1];
                dp[i][j] = top + left;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m-1][n-1];
    }
    public static boolean subsetSum(int n, int[] arr){
        int k = 0;
        for(int i : arr){
            k += i;
        }
        if(k%2 ==1) return false;
        return f(n,n-1,k/2,arr);
    }
    private static boolean f(int n int k, int[] arr){
        boolean[][] dp = new boolean[n][k+1];
        for(int i = 0;i<n;i++){
            for(int tar = 0;tar<=k;tar++){
                dp[i][0] =  true;
                if(i == 0){
                    dp[i][tar] = (arr[i] == k);
                }
                boolean notPick = dp[i-1][k];
                boolean pick = false;
                if(arr[i] <= k){
                    pick = dp[i-1][k-arr[i]];
                }
                dp[i][tar] = pick || notPick;
            }
        }

    }
}