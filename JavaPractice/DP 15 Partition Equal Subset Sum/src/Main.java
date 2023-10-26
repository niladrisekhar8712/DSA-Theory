import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public boolean canPartition(int[] nums) {
        int s = Arrays.stream(nums).sum();
        if(s % 2 == 1) return false;
        int k = s/2;
        int n = nums.length;
        boolean[][] dp = new boolean[n][k+1];
        for(int i = 0;i<n;i++){
            dp[i][0] = true;
        }
        if(nums[0]<=k){
            dp[0][nums[0]] = true;
        }
        for(int ind = 1;ind<n;ind++){
            for(int target = 1;target<=k;target++){
                boolean pick = target >= nums[ind] && dp[ind - 1][target - nums[ind]];
                boolean notPick = dp[ind-1][target];
                dp[ind][target] = (pick || notPick);
            }
        }
        return dp[n-1][k];
    }

}