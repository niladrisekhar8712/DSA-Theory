import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int ninjaTraining(int n, int[][] points) {
        int[][] dp = new int[n][4];
        for(int i = 0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return recurr(n-1,3,points,dp);
    }
    private static int recurr(int day, int last, int[][] points, int[][] dp){
        if(dp[day][last] != -1){
            return dp[day][last];
        }
        if(day == 0){
            int maxi = 0;
            for(int i = 0;i<3;i++){
                if(i != last){
                    maxi = Math.max(maxi, points[0][i]);
                }
            }
            return maxi;
        }
        int maxi = 0;
        for(int i = 0;i<3;i++){
            if(i != last){
                int point = points[day][i] + recurr(day-1,i,points,dp);
                maxi = Math.max(point,maxi);
            }
        }
        dp[day][last] = maxi;
        return maxi;
    }
}