import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(distinctSubsequences("babgbag","bag"));
    }
    public static int distinctSubsequences(String str, String sub) {
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[str.length()][sub.length()];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(str,sub);
//        return memoization(str,sub,str.length()-1,sub.length()-1,dp)%mod;
//        return recurr(str,sub,str.length()-1,sub.length()-1)%mod;
    }
    private static int recurr(String str, String sub, int i, int j){
        int mod = (int) 1e9 + 7;
        if(j<0) return 1;
        if(i<0) return 0;
        if(str.charAt(i) == sub.charAt(j)) return (recurr(str,sub,i-1,j-1) + recurr(str,sub,i-1,j))%mod;
        else return (recurr(str,sub,i-1,j))%mod;
    }
    private static int memoization(String str, String sub, int i, int j,int[][] dp){
        int mod = (int) 1e9 + 7;
        if(j<0) return 1;
        if(i<0) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(str.charAt(i) == sub.charAt(j)) return dp[i][j] = (memoization(str,sub,i-1,j-1,dp) + memoization(str,sub,i-1,j,dp))%mod;
        else return dp[i][j] = (memoization(str,sub,i-1,j,dp))%mod;
    }
    private static int tabulation(String str, String sub){
        int mod = (int) 1e9 + 7;

        int[][] dp = new int[str.length()+1][sub.length()+1];
        for(int i = 0;i<=str.length();i++){
            dp[i][0] = 1;
        }
        for(int j = 1;j<=sub.length();j++){
            dp[0][j] = 0;
        }


        for(int i = 1;i<=str.length();i++){
            for(int j = 1;j<sub.length();j++){
                if(str.charAt(i-1) == sub.charAt(j-1)) dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%mod;
                else dp[i][j] = (dp[i-1][j])%mod;
            }
        }
        for(int[] i : dp){
            System.out.println(Arrays.toString(i));
        }
        return dp[str.length()][sub.length()];
    }

}