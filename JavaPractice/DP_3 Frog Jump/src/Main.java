import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] stairs = {10,40,50,20,60};
        System.out.println(frogJump(4,stairs));
        System.out.println(frogJumpTabulation(5,stairs));
        System.out.println(minimizeCost(5,2,stairs));
        System.out.println(minimalCostTabulation(5,2,stairs));
    }
    public static int frogJump(int n, int heights[]) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return recurr(n-1, dp, heights);
    }
    private static int recurr(int n, int[] dp, int[] heights){
        if(n == 0){
            return 0;
        }
        if(dp[n] != -1){
            return dp[n];
        }

        int left = recurr(n-1,dp,heights) + Math.abs(heights[n] - heights[n-1]);
        int right = Integer.MAX_VALUE;
        if(n>1){
            right = recurr(n-2,dp,heights) + Math.abs(heights[n] - heights[n-2]);
        }

        dp[n] = Math.min(left,right);
        return dp[n];
    }

    public static int frogJumpTabulation(int n,int[] heights){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1;i<n;i++) {
            int fs = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int rs = Integer.MAX_VALUE;
            if (i > 1) {
                rs = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(fs, rs);
        }
        return dp[n-1];
    }
    /* FOLLOW UP*/
    public static int minimizeCost(int n, int k, int []height){
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return func(n-1,dp,k,height);
    }
    public static int func(int ind,int[]dp, int k,int[] height){
        if(ind == 0) return 0;
        int min = Integer.MAX_VALUE;
        if(dp[ind] !=-1) return dp[ind];
        for(int i = ind - 1;i>=ind - k && i>=0;i--){
            int jump = func(i,dp,k,height) + Math.abs(height[ind] - height[i]);
            min = Math.min(min,jump);
        }
        dp[ind] = min;
        return min;
    }
    public static int minimalCostTabulation(int n, int k, int []height){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1;i<n;i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 1;j<=k && i-j>=0;j++){
                int jump = dp[i-j] + Math.abs(height[i] - height[i-j]);
                min = Math.min(min,jump);
            }
            dp[i] = min;
        }
        return dp[n-1];
    }
}