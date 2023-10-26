import java.util.Arrays;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {

        int n = 6;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(fibonacci(dp, n));
        System.out.println(fibonacciTabulation(n));
    }
    public static int fibonacci(int[] dp, int n){
//        Time: O(N) Space: O(N)
        if(n <= 1) return n;
        if(dp[n] != -1) return dp[n];
        dp[n] = fibonacci(dp, n-1) + fibonacci(dp,n-2);
        return fibonacci(dp, n-1) + fibonacci(dp,n-2);
    }
    public static int fibonacciTabulation(int n){
//        Time: O(N) Space: O(1)
        int p1 = 1;
        int p2 = 0;
        for(int i = 2;i<=n;i++){
            int curri = p1+p2;
            p2 = p1;
            p1 = curri;
        }
        return p1;
    }
}