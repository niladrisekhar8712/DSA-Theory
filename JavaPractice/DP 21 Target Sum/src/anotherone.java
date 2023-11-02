import java.util.Arrays;

public class anotherone {
    public static void main(String[] args) {
        int[] arr = {24, 36, 1, 3,29, 0, 38, 17, 35, 31, 42, 30, 44, 35, 22};
        System.out.println(findWays(arr,183));
    }
    public static int findWays(int[] arr, int k) {
        int[][] dp = new int[arr.length][k+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return memoization(arr, arr.length-1, k,dp);
//        return recurr(arr,arr.length - 1,k);
//        return tabulation(arr,k);
    }
    private static int recurr(int[] arr,int index,int target){
        if(target == 0) return 1;
        if(index == 0){
            if(arr[0] == target){
                return 1;
            }
            else{
                return 0;
            }
        }


        int notPick = recurr(arr,index - 1,target);
        int pick = 0;
        if(target >= arr[index]){
            pick = recurr(arr,index - 1,target - arr[index]);
        }
        return pick + notPick;
    }

    private static int memoization(int[] arr, int index, int target,int[][] dp){
        int mod = (int)1e9 + 7;
        if(target == 0) return 1;
        if(index == 0){
            if(arr[0] == target){
                return 1;
            }
            else{
                return 0;
            }
        }
//        if(index == 0){
//            if(arr[0] == 0 && target == 0){
//                return 2;
//            }
//            if(arr[0] == target || target == 0){
//                return 1;
//            }
//        }
        if(dp[index][target] != -1){
            return dp[index][target];
        }

        int notPick = memoization(arr,index - 1,target,dp);
        int pick = 0;
        if(target >= arr[index]){
            pick = memoization(arr,index - 1,target - arr[index],dp);
        }
        return dp[index][target] = (pick + notPick)%mod; // since the ans can be very large
    }
    private static int tabulation(int[] arr, int k){
        int mod = (int)1e9 + 7;
        int[][] dp = new int[arr.length][k+1];
        for(int i = 0;i<arr.length;i++){
            dp[i][0] = 1;
        }
        if(arr[0]<=k){
            dp[0][arr[0]] = 1;
        }
        for(int index = 1;index<arr.length;index++){
            for(int target = 0;target<=k;target++){
                int notPick = dp[index-1][target];
                int pick = 0;
                if(target >= arr[index]){
                    pick = dp[index-1][target-arr[index]];
                }
                dp[index][target] = (pick + notPick)%mod; // since the ans can be very large
            }
        }
        return dp[arr.length-1][k];
    }
}