import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2,3,1,1};
        int target = 4;
        System.out.println(subsetSumToK(4,target,arr));
    }
    public static boolean subsetSumToK(int n, int k, int[] arr){
        int[][] dp = new int[n][k+1]; // constraints
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
//        return recurr(n, k, arr, n-1);
//        return memoization(n,k,arr,n-1,dp);
        return tabulation(n, k, arr);
    }
    private static boolean recurr(int n, int k,int[] arr,int ind){
        if(k == 0){
            return true;
        }
        if(ind == 0){
            return arr[ind] == k;
        }
        boolean pick = k >= arr[ind] && recurr(n, k - arr[ind], arr, ind - 1);
        boolean notPick = recurr(n,k,arr,ind-1);
        return pick || notPick;
    }
    private static boolean memoization(int n, int k,int[] arr,int ind,int[][] dp){
        if(k == 0){
            return true;
        }
        if(ind == 0){
            return arr[ind] == k;
        }
        if(dp[ind][k] != -1){
            return dp[ind][k] == 1;
        }
        boolean pick = k >= arr[ind] && memoization(n, k - arr[ind], arr, ind - 1,dp);
        boolean notPick = memoization(n,k,arr,ind-1,dp);
        dp[ind][k] = (pick || notPick) ? 1 : 0;
        return pick || notPick;
    }
    private static boolean tabulation(int n, int k, int[] arr){
        boolean[][] dp = new boolean[n][k+1];
        for(int i = 0;i<n;i++){
            dp[i][0] = true;
        }
        if(arr[0]<=k){
            dp[0][arr[0]] = true;
        }
        for(int ind = 1;ind<n;ind++){
            for(int target = 1;target<=k;target++){
                boolean pick = target >= arr[ind] && dp[ind - 1][target - arr[ind]];
                boolean notPick = dp[ind-1][target];
                dp[ind][target] = (pick || notPick);
            }
        }
        return dp[n-1][k];
    }
}