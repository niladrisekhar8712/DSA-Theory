import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] weight = {3,2,5};
        int[] value = {30,40,60};
        System.out.println(knapsack(weight,value,3,6));
    }
    public static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(weight,value,n,maxWeight);
//        return memoization(weight,value,n-1,maxWeight,dp);
//        return recurr(weight,value,n-1,maxWeight);
    }
    private static int recurr(int[] weight, int[] value, int ind, int maxWeight){
        if(ind == 0){
            if(maxWeight >= weight[ind]){
                return value[ind];
            }
            else return 0;
        }
        int notPick = recurr(weight,value,ind-1,maxWeight);
        int pick = Integer.MIN_VALUE;
        if(maxWeight>= weight[ind]){
            pick = value[ind] + recurr(weight,value,ind-1,maxWeight - weight[ind]);
        }
        return Math.max(pick,notPick);
    }
    private static int memoization(int[] weight, int[] value, int ind, int maxWeight,int[][] dp){
        if(ind == 0){
            if(maxWeight >= weight[ind]){
                return value[ind];
            }
            else return 0;
        }
        if(dp[ind][maxWeight] != -1){
            return dp[ind][maxWeight];
        }
        int notPick = memoization(weight,value,ind-1,maxWeight,dp);
        int pick = Integer.MIN_VALUE;
        if(maxWeight>= weight[ind]){
            pick = value[ind] + memoization(weight,value,ind-1,maxWeight - weight[ind],dp);
        }
        return dp[ind][maxWeight] = Math.max(pick,notPick);
    }
    private static int tabulation(int[] weight, int[] value, int n, int maxWeight){
        int[][] dp = new int[n][maxWeight+1];
        for(int i = weight[0];i<=maxWeight;i++){
            dp[0][i] = value[0];
        }
        for(int ind = 1;ind<n;ind++){
            for(int w = 0;w<=maxWeight;w++){
                int notPick = dp[ind-1][w];
                int pick = Integer.MIN_VALUE;
                if(w>= weight[ind]){
                    pick = value[ind] + dp[ind-1][w - weight[ind]];
                }
                dp[ind][w] = Math.max(pick,notPick);
            }
        }
        return dp[n-1][maxWeight];
    }
}