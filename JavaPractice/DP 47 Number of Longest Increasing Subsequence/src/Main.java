import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,3,5,4,7};
        System.out.println(findNumberOfLIS(arr));
    }
    public static int findNumberOfLIS(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        int max = (int) -1e9;
        for(int ind = 0;ind < n;ind++){
            for(int prev = 0;prev<=ind-1;prev++){
                if(arr[prev]<arr[ind] && 1+dp[prev] > dp[ind]){
                    dp[ind] = 1+dp[prev];
                    cnt[ind] = cnt[prev];
                } else if (arr[prev]<arr[ind] && 1+dp[prev] == dp[ind]) {
                    cnt[ind] += cnt[prev];
                }
            }
            max = Math.max(max,dp[ind]);
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            if(dp[i] == max){
                ans += cnt[i];
            }
        }
        return ans;
    }
}