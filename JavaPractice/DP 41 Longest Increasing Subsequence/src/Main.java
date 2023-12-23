import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(longestIncreasingSubsequence(arr));
    }
    public static int longestIncreasingSubsequence(int[] arr) {
//        int[][] dp = new int[arr.length][arr.length+1];
//        for(int[] i : dp){
//            Arrays.fill(i,-1);
//        }
        return binarySearch(arr);
//        return bestTabulation(arr);
//        return spaceOptimised(arr);
//        return tabulation(arr);
//        return memoization(arr,0,-1,dp);
//        return recurr(arr,0,-1);
    }
    private static int recurr(int[] arr, int i, int prev){
        if(i == arr.length) return 0;
        int notTake = recurr(arr, i+1, prev);
        int take = (int) -1e9;
        if(prev == -1 || arr[i]>arr[prev]){
            take = 1 + recurr(arr, i+1, i);
        }
        return Math.max(take,notTake);
    }
    private static int memoization(int[] arr, int i, int prev, int[][] dp){
        if(i == arr.length) return 0;
        if(dp[i][prev+1] != -1) return dp[i][prev+1];
        int notTake = memoization(arr, i+1, prev,dp);
        int take = (int) -1e9;
        if(prev == -1 || arr[i]>arr[prev]){
            take = 1 + memoization(arr, i+1, i,dp);
        }
        return dp[i][prev+1] = Math.max(take,notTake);
    }
    /* DP 42 Tabulation and Space Optimisation */
    private static int tabulation(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n+1][n+1];
        for(int i = n-1;i>=0;i--){
            for(int prev = i-1;prev>=-1;prev--){
                int notTake = dp[i+1][prev+1];      // prev is 1-indexed
                int take = (int) -1e9;
                if(prev == -1 || arr[i]>arr[prev]){
                    take = 1 + dp[i+1][i+1];
                }
                dp[i][prev+1] = Math.max(take,notTake);
            }
        }
        return dp[0][0];
    }
    private static int spaceOptimised(int[] arr){
        int n = arr.length;
        int[] next = new int[n+1];
        int[] curr = new int[n+1];
        for(int i = n-1;i>=0;i--){
            for(int prev = i-1;prev>=-1;prev--){
                int notTake = next[prev+1];
                int take = (int) -1e9;
                if(prev == -1 || arr[i]>arr[prev]){
                    take = 1 + next[i+1];
                }
                curr[prev+1] = Math.max(take,notTake);
            }
            next = curr;
        }
        return next[0];
    }
    private static int bestTabulation(int[] arr){       // best for printing LIS
        int n = arr.length;
        int[] dp = new int[n];
        int max = (int) -1e9;
        for(int ind = 0;ind < n;ind++){
            for(int prev = 0;prev<=ind-1;prev++){
                if(arr[prev]<arr[ind]){
                    dp[ind] = Math.max(1+dp[prev],dp[ind]);
                }
            }
            max = Math.max(max,dp[ind]);
        }
        return max+1;
    }
    /* DP 43 Binary Search */
    private static int binarySearch(int[] arr){
        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);
        int len = 1;
        for(int i = 1;i<arr.length;i++){
            if(temp.getLast() < arr[i]){
                temp.add(arr[i]);
                len++;
            }
            else{
                int ind = lowerBound(temp,arr[i]);
                temp.set(ind,arr[i]);
            }
        }
        return len;
    }
    private static int lowerBound(List<Integer> arr,int skey){
        int low = 0;
        int high = arr.size() - 1;
        while(low <= high){
            int mid = (low+high)/2;
            if(arr.get(mid) < skey){
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return low;
    }

}