import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3 };
        int T = 7;
        System.out.println(minimumElements(arr,T));
    }
    public static int minimumElements(int[] num, int x) {
        return tabulation(num,x);
//        int[][] dp = new int[num.length][x+1];
//        for(int[] i : dp){
//            Arrays.fill(i,-1);
//        }
//        return memoization(num, num.length-1, x,dp) == (int) 1e9 ? -1 : memoization(num,num.length-1,x,dp);
//        return recurr(num,num.length-1,x) == (int) 1e9 ? -1 : recurr(num,num.length-1,x);
    }
    private static int recurr(int[] num,int ind, int t){
        if(ind == 0){
            if(t%num[0] == 0){
                return t/num[0];
            }
            else return (int) 1e9;
        }
        int notTake = recurr(num, ind - 1, t);
        int take = (int) 1e9;
        if(num[ind] <= t){
            take = 1+recurr(num, ind, t-num[ind]);
        }
        return Math.min(take,notTake);
    }

    private static int memoization(int[] num,int ind, int t,int[][] dp){
        if(ind == 0){
            if(t%num[0] == 0){
                return t/num[0];
            }
            else return (int) 1e9;
        }
        if(dp[ind][t] != -1){
            return dp[ind][t];
        }
        int notTake = memoization(num, ind - 1, t,dp);
        int take = (int) 1e9;
        if(num[ind] <= t){
            take = 1+memoization(num, ind, t-num[ind],dp);
        }
        return dp[ind][t] = Math.min(take,notTake);
    }
    private static int tabulation(int[] num, int x){
        int[][] dp = new int[num.length][x+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        for(int target = 0;target<=x;target++){
            if(target%num[0] == 0){
                dp[0][target] = target/num[0];
            }
            else{
                dp[0][target] = (int) 1e9;
            }
        }
        for (int i = 1; i < num.length; i++) {
            for(int target = 0;target<=x;target++){
                int notTake = dp[i- 1][target];
                int take = (int) 1e9;
                if(num[i] <= target){
                    take = 1+dp[i][target-num[i]];
                }
                dp[i][target] = Math.min(take,notTake);
            }
        }
        int val = dp[num.length-1][x];
        return val == (int) 1e9 ? -1 : val;
    }
}