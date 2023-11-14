import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde","ace"));
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(text1,text2);
//        return memoization(text1,text2,text1.length()-1, text2.length()-1,dp);
//        return recurr(text1,text2,text1.length()-1, text2.length()-1);
    }
    private static int recurr(String s, String t,int ind1,int ind2){
        if(ind1<0 || ind2<0){
            return 0;
        }
        if(s.charAt(ind1) == t.charAt(ind2)){
            return 1 + recurr(s,t,ind1-1,ind2-1);
        }
        return Math.max(recurr(s,t,ind1-1,ind2), recurr(s,t,ind1,ind2-1));
    }
    private static int memoization(String s, String t,int ind1,int ind2,int[][] dp){
        if(ind1<0 || ind2<0){
            return 0;
        }
        if(dp[ind1][ind2] != -1) return dp[ind1][ind2];
        if(s.charAt(ind1) == t.charAt(ind2)){
            return dp[ind1][ind2] = 1 + memoization(s,t,ind1-1,ind2-1,dp);
        }
        return dp[ind1][ind2] = Math.max(memoization(s,t,ind1-1,ind2,dp), memoization(s,t,ind1,ind2-1,dp));
    }
    private static int tabulation(String s,String t){
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 0;i<=s.length();i++) dp[i][0] = 0;
        for(int j = 0;j<=t.length();j++) dp[0][j] = 0;
        for(int ind1 = 1;ind1<=s.length();ind1++){
            for(int ind2 = 1;ind2<=t.length();ind2++){
                if(s.charAt(ind1-1) == t.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }
                else dp[ind1][ind2] = Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
            }
        }
        for(int[] i : dp){
            System.out.println(Arrays.toString(i));
        }
        return dp[s.length()][t.length()];
    }
}