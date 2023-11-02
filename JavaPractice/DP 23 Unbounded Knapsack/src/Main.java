import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] wt = { 2, 4, 6 };
        int[] val = { 5, 11, 13 };
        int W = 10;

        int n = wt.length;
        System.out.println(unboundedKnapsack(n,W,val,wt));
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[][] dp = new int[n][w+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(n,w,profit,weight);
//        return memoization(n-1,w,profit,weight,dp);
//        return recurr(n-1, w, profit, weight);
    }
    private static int recurr(int ind, int w,int[] profit,int[] weight){
        if(ind == 0){
            if(w >= weight[0]){
                return (w / weight[0])*profit[0];
            }
            else{
                return 0;
            }
        }
        int notPick = recurr(ind-1, w, profit, weight);
        int pick = 0;
        if(w>=weight[ind]){
            pick = profit[ind] + recurr(ind, w-weight[ind], profit, weight);
        }
        return Math.max(notPick,pick);
    }

    private static int memoization(int ind, int w,int[] profit,int[] weight,int[][] dp){
        if(ind == 0){
            if(w >= weight[0]){
                return (w / weight[0])*profit[0];
            }
            else{
                return 0;
            }
        }
        if(dp[ind][w] != -1){
            return dp[ind][w];
        }
        int notPick = memoization(ind-1, w, profit, weight,dp);
        int pick = 0;
        if(w>=weight[ind]){
            pick = profit[ind] + memoization(ind, w-weight[ind], profit, weight,dp);
        }
        return dp[ind][w] = Math.max(notPick,pick);
    }
    private static int tabulation(int n, int w, int[] profit, int[] weight){
        int[][] dp = new int[n][w+1];
        for(int wt = 0;wt<=w;wt++){
            if(wt >= weight[0]){
                dp[0][wt] = (wt / weight[0])*profit[0];
            }
        }
        for(int ind = 1;ind<n;ind++){
            for(int wt = 0;wt<=w;wt++){
                int notPick = dp[ind-1][wt];
                int pick = 0;
                if(wt>=weight[ind]){
                    pick = profit[ind] + dp[ind][wt-weight[ind]];
                }
                dp[ind][wt] = Math.max(notPick,pick);
            }
        }
        return dp[n-1][w];
    }
}