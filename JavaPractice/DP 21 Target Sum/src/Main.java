import java.util.Arrays;
// concept of DP 18
public class Main {
    public static void main(String[] args) {
        int[] arr = {24, 36, 1, 3,29, 0, 38, 17, 35, 31, 42, 30, 44, 35, 22};
        System.out.println(targetSum(arr.length,21,arr));
    }

    public static int targetSum(int n, int target, int[] arr) {

        int s = Arrays.stream(arr).sum();
        if((s-target) < 0 || (s-target)%2 == 1) return 0;
//        int[][] dp = new int[n][(s-target)/2+1];
//        for(int[] i : dp){
//            Arrays.fill(i,-1);
//        }
        return tabulation(n,(s-target)/2,arr);
//        return memoization(n,n-1,(s-target)/2,arr,dp);
//        return recurr(n,n-1,(s-target)/2,arr);
    }
    private static int recurr(int n,int ind, int target, int[] arr){
        if(target == 0) return 1;
        if(ind == 0){
            if(arr[ind] == target){
                return 1;
            }
            else return 0;
        }
        int notPick = recurr(n,ind-1,target,arr);
        int pick = 0;
        if(target >= arr[ind]){
            pick = recurr(n,ind-1,target-arr[ind],arr);
        }
        return notPick+pick;
    }
    private static int memoization(int n,int ind, int target, int[] arr,int[][] dp){
        if(target == 0) return 1;
        if(ind == 0){
            if(arr[ind] == target){
                return 1;
            }
            else return 0;
        }
        if(dp[ind][target] != -1){
            return dp[ind][target];
        }
        int notPick = memoization(n,ind-1,target,arr,dp);
        int pick = 0;
        if(target >= arr[ind]){
            pick = memoization(n,ind-1,target-arr[ind],arr,dp);
        }
        return dp[ind][target] = notPick+pick;
    }
    private static int tabulation(int n, int target, int[] arr){
        int[][] dp = new int[n][target+1];
        for(int i = 0;i<n;i++){
            dp[i][0] = 1;
        }
        if(arr[0]<=target){
            dp[0][arr[0]] = 1;
        }
        for(int i = 1;i<n;i++){
            for(int t = 0;t<=target;t++){
                int notPick = dp[i-1][t];
                int pick = 0;
                if(t >= arr[i]){
                    pick = dp[i-1][t-arr[i]];
                }
                dp[i][t] = notPick+pick;
            }
        }
        return dp[n-1][target];
    }
}