public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int cutRod(int[] price, int n) {
        int[][] dp = new int[][]
        return f(price);
    }
    // private static int recurr(int[] price, int n, int ind){
    //     if(ind == 0){
    //         return n*price[ind];
    //     }
    //     int notPick = recurr(price, n, ind-1);
    //     int pick = 0;
    //     if(ind+1<=n){
    //         pick = price[ind] + recurr(price, n-ind-1, ind);
    //     }
    //     return Math.max(notPick,pick);
    // }
    public static int f(int[] arr){ // tabulation approach
        int[][] dp = new int[arr.length][arr.length+1];
        for(int i = 0;i<dp.length;i++){
            dp[i][0] = 0;
        }
        for(int len = 0;len<=arr.length;len++){
            dp[0][len] = len * arr[0];
        }
        for(int i = 1;i< arr.length;i++){
            for(int len = 1;len<=arr.length;len++){
                int notTake = dp[i - 1][len];
                int pick = (int) -1e9;
                if(len >= i+1){
                    pick = arr[i] + dp[i][len - i - 1];
                }
                dp[i][len] = Math.max(notTake,pick);
            }
        }
        return dp[arr.length-1][arr.length];
    }
}
