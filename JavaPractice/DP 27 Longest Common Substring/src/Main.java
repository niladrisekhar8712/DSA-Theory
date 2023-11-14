public class Main {
    public static void main(String[] args) {
        System.out.println(tabulation("abc","axd"));
    }
    private static int tabulation(String s,String t) {
        int maxi = 0;
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) dp[i][0] = 0;
        for (int j = 0; j <= t.length(); j++) dp[0][j] = 0;
        for (int ind1 = 1; ind1 <= s.length(); ind1++) {
            for (int ind2 = 1; ind2 <= t.length(); ind2++) {
                if (s.charAt(ind1 - 1) == t.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else dp[ind1][ind2] = 0;
                maxi = Math.max(dp[ind1][ind2],maxi);
            }
        }
        return maxi;
    }
}