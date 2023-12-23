import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long[] arr = {7,1,5,3,6,2};
        System.out.println(getMaximumProfit(6,arr));
    }
    public static long getMaximumProfit (int n, long[] values) {
//        long[][] dp = new long[n][2];
//        for(long[] i : dp){
//            Arrays.fill(i,-1);
//        }
        return spaceOptimised(n,values);
//        return tabulation(n,values);
//        return memoization(n,values,0,1,dp);
//        return recurr(n, values, 0, 1);
    }
    private static long recurr(int n, long[] values,int ind, int buy){
        if(ind == n){
            return 0;
        }
        long profit = 0;
        if(buy == 1){
            profit = Math.max((-values[ind] + recurr(n, values, ind+1,0)),(recurr(n, values, ind + 1, 1)));
        }
        else{
            profit = Math.max((values[ind]+recurr(n, values, ind+1, 1)),(recurr(n, values, ind + 1, 0)));
        }
        return profit;
    }
    private static long memoization(int n, long[] values,int ind, int buy,long[][] dp){
        if(ind == n){
            return 0;
        }
        long profit = 0;
        if(dp[ind][buy] != -1){
            return dp[ind][buy];
        }
        if(buy == 1){
            profit = Math.max((-values[ind] + memoization(n, values, ind+1,0,dp)),(memoization(n, values, ind + 1, 1, dp)));
        }
        else{
            profit = Math.max((values[ind]+memoization(n, values, ind+1, 1,dp)),(memoization(n, values, ind + 1, 0, dp)));
        }
        return dp[ind][buy] = profit;
    }
    private static long tabulation(int n, long[] values){
        long[][] dp = new long[n+1][2];
        dp[n][0] = 0;
        dp[n][1] = 0;
        for(int i = n-1;i>=0;i--){
            for(int buy = 0;buy<=1;buy++){
                long profit = 0;
                if(buy == 1){
                    profit = Math.max((-values[i] + dp[i+1][0]),dp[i + 1][1]);
                }
                else{
                    profit = Math.max((values[i]+dp[i+1][1]),dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
    private static long spaceOptimised(int n, long[] values){
        long[] ahead = new long[2];
        long[] curr = new long[2];
        for(int i = n-1;i>=0;i--){
            for(int buy = 0;buy<=1;buy++){
                long profit = 0;
                if(buy == 1){
                    profit = Math.max((-values[i] + ahead[0]),ahead[1]);
                }
                else{
                    profit = Math.max((values[i]+ahead[1]),ahead[0]);
                }
                curr[buy] = profit;
            }
            ahead = curr;
        }
        return ahead[1];
    }
}