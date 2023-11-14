public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int cutRod(int[] price, int n) {
        int[][] dp = new int[][]
        return recurr(price, n, n-1);
    }
    private static int recurr(int[] price, int n, int ind){
        if(ind == 0){
            return n*price[ind];
        }
        int notPick = recurr(price, n, ind-1);
        int pick = 0;
        if(ind+1<=n){
            pick = price[ind] + recurr(price, n-ind-1, ind);
        }
        return Math.max(notPick,pick);
    }
}