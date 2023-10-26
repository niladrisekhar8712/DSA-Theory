import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(rob(nums));
        System.out.println(robTabulation(nums));
        System.out.println(robSpace(nums));
    }
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return recurrMem(nums,nums.length-1,dp);
    }
    private static int recurrMem(int[] nums, int n, int[] dp){
        // TC: O(N) SC: O(N) + O(N)
        if(n == 0){
            return nums[n];
        }
        if(n < 1) return 0;
        if(dp[n] != -1){
            return dp[n];
        }
        int pick = nums[n] + recurrMem(nums,n-2,dp);
        int notPick = recurrMem(nums,n-1,dp);
        dp[n] = Math.max(pick,notPick);
        return Math.max(pick,notPick);
    }
    private static int robTabulation(int[] nums){
        // TC: O(N) SC: O(N)
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1;i< nums.length;i++){
            int pick = (i == 1) ? nums[i] : nums[i] + dp[i-2];
            int notPick = dp[i-1];
            dp[i] = Math.max(pick,notPick);
        }
        return dp[nums.length-1];
    }
    private static int robSpace(int[] nums){
        // TC: O(N) SC: O(1)
        int p1 = nums[0];
        int p2 = 0;
        for(int i = 1;i< nums.length;i++){
            int pick = (i == 1) ? nums[i] : nums[i] + p2;
            int notPick = p1;
            int curri = Math.max(pick,notPick);
            p2 = p1;
            p1 = curri;
        }
        return p1;
    }
}