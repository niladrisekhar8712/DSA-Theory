import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(word1,word2);
//        return memoization(word1,word2,word1.length()-1,word2.length()-1,dp);
//        return recurr(word1,word2, word1.length()-1,word2.length()-1);
    }
    private static int recurr(String word1, String word2,int i,int j){
        if(j<0) return i+1;
        if(i<0) return j+1;
        if(word1.charAt(i) == word2.charAt(j)) return recurr(word1, word2, i-1, j-1);
        int insert = 1+recurr(word1, word2, i, j-1);
        int remove = 1+recurr(word1, word2, i-1, j);
        int replace = 1+recurr(word1, word2, i-1, j-1);
        return Math.min(insert,Math.min(remove,replace));
    }
    private static int memoization(String word1, String word2,int i,int j,int[][] dp){
        if(j<0) return i+1;
        if(i<0) return j+1;
        if(dp[i][j] != -1) return dp[i][j];
        if(word1.charAt(i) == word2.charAt(j)) return dp[i][j] = memoization(word1, word2, i-1, j-1,dp);
        int insert = 1+memoization(word1, word2, i, j-1,dp);
        int remove = 1+memoization(word1, word2, i-1, j,dp);
        int replace = 1+memoization(word1, word2, i-1, j-1,dp);
        return dp[i][j] = Math.min(insert,Math.min(remove,replace));
    }
    private static int tabulation(String word1, String word2){
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        for(int i = 0;i<=word1.length();i++){
            dp[i][0] = i;
        }
        for(int j = 1;j<=word2.length();j++){
            dp[0][j] = j;
        }
        for(int i = 1;i<=word1.length();i++){
            for(int j = 1;j<=word2.length();j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{
                    int insert = 1+dp[i][j-1];
                    int remove = 1+dp[i-1][j];
                    int replace = 1+dp[i-1][j-1];
                    dp[i][j] = Math.min(insert,Math.min(remove,replace));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}