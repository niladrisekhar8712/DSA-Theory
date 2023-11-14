public class Main {
    public static void main(String[] args) {
        System.out.println(minInsertion("codingninjas"));
    }
    public static int minInsertion(String str) {
        return str.length() - tabulation(str,new StringBuilder(str).reverse().toString());
    }
    private static int tabulation(String s, String t){
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 0;i<=s.length();i++) dp[i][0] = 0;
        for(int j = 0;j<=t.length();j++) dp[0][j] = 0;

        for(int i = 1;i<=s.length();i++){
            for(int j = 1;j<=t.length();j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        return dp[s.length()][t.length()];
    }
}