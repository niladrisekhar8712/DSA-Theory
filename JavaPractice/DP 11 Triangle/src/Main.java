import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return memoization(0,0,triangle,dp);
    }
    private static int recurr(int i, int j, List<List<Integer>> triangle){
        if(i == triangle.size()-1) return triangle.get(i).get(j);
        int down = triangle.get(i).get(j) + recurr(i+1,j,triangle);
        int downRight = triangle.get(i).get(j)+ recurr(i+1,j+1,triangle);
        return Math.min(down,downRight);
    }
    private static int memoization(int i, int j, List<List<Integer>> triangle, int[][] dp){
        if(i == triangle.size()-1) return triangle.get(i).get(j);
        if(dp[i][j] != -1) return dp[i][j];
        int down = triangle.get(i).get(j) + memoization(i+1,j,triangle,dp);
        int downRight = triangle.get(i).get(j)+ memoization(i+1,j+1,triangle,dp);
        return dp[i][j] = Math.min(down,downRight);
    }
    private static int tabulation(List<List<Integer>> triangle){
        int n = triangle.size();
        int[][] dp = new int[n][triangle.get(triangle.size()-1).size()];
        for(int j = 0;j<triangle.get(n-1).size();j++){
            dp[n-1][j] = triangle.get(n-1).get(j);
        }
        for(int i = n-2;i>=0;i--){
            for(int j = 0;j<triangle.get(i).size();j++){
                int down = triangle.get(i).get(j) + dp[i+1][j];
                int downRight = triangle.get(i).get(j)+ dp[i+1][j+1];
                dp[i][j] = Math.min(down,downRight);
            }
        }
        return dp[0][0];
    }
}