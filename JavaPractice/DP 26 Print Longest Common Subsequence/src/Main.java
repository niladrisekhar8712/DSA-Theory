import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(tabulation("abcde","bdgek"));
    }
    private static String tabulation(String s,String t){
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
        int i = s.length();
        int j = t.length();
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0){
            if(s.charAt(i-1) == t.charAt(j-1)){
                sb.append(s.charAt(i-1));
                i--;
                j--;
            }
            else{
                if(dp[i-1][j] > dp[i][j-1]){
                    i--;
                }
                else{
                    j--;
                }
            }
        }
        return sb.reverse().toString();
    }
}