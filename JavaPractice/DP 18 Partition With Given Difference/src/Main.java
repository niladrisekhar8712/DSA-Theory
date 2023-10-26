import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static final int mod = (int) 1e9 +7;
    public static int countPartitions(int n, int d, int[] arr) {

        int s = Arrays.stream(arr).sum();
        if((s-d)%2 == 1 || (s-d)<0) return 0;
        int s1 = (s-d)/2;
        return findWays(arr,s1);
    }
    private static int findWaysUtil(int ind, int target,int[] arr, int[][] dp){
        int mod = (int)1e9 + 7;
        if(ind == 0){
            if(target==0 && arr[0]==0)
                return 2;
            if(target==0 || target == arr[0])
                return 1;
            return 0;
        }

        if(dp[ind][target]!=-1)
            return dp[ind][target];

        int notTaken = findWaysUtil(ind-1,target,arr,dp);

        int taken = 0;
        if(arr[ind]<=target)
            taken = findWaysUtil(ind-1,target-arr[ind],arr,dp);

        return dp[ind][target]= (notTaken + taken)%mod;
    }

    private static int findWays(int[] num, int k){
        int n = num.length;
        int dp[][]= new int[n][k+1];

        for(int row[]: dp)
            Arrays.fill(row,-1);

        return findWaysUtil(n-1,k,num,dp);
    }
}