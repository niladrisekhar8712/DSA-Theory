import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(countWaysToMakeChange(arr,4));
    }
    public static long countWaysToMakeChange(int[] denominations, int value){
        long[][] dp = new long[denominations.length][value+1];
        for(long[] i : dp){
            Arrays.fill(i,-1);
        }
        return tabulation(denominations,value);
//        return memoization(denominations, denominations.length-1, value,dp);
//        return recurr(denominations,denominations.length - 1, value);

    }
    private static long recurr(int[] denominations,int ind,int value){
//        if(value == 0) return 1;
        if(ind == 0){
            if(value % denominations[0] == 0){
                return 1;
            }
            else return 0;
        }
        long notPick = recurr(denominations,ind-1,value);
        long pick = 0;
        if(denominations[ind] <= value){
            pick = recurr(denominations,ind,value-denominations[ind]);
        }
        return notPick+pick;
    }

    private static long memoization(int[] denominations,int ind,int value,long[][] dp){
//        if(value == 0) return 1;
        if(ind == 0){
            if(value % denominations[0] == 0){
                return 1;
            }
            else return 0;
        }
        if(dp[ind][value] != -1) return dp[ind][value];
        long notPick = memoization(denominations,ind-1,value,dp);
        long pick = 0;
        if(denominations[ind] <= value){
            pick = memoization(denominations,ind,value-denominations[ind],dp);
        }
        return dp[ind][value] = notPick+pick;
    }
    private static long tabulation(int[] denominations, int value){
        long[][] dp = new long[denominations.length][value+1];
        for(int i = 0;i<=value;i++){
            if(i%denominations[0] == 0){
                dp[0][i] = 1;
            }

        }
        for(int i = 1;i< denominations.length;i++){
            for(int v = 0;v<=value;v++){
                long notPick = dp[i-1][v];
                long pick = 0;
                if(denominations[i] <= v){
                    pick = dp[i][v-denominations[i]];
                }
                dp[i][v] = notPick+pick;
            }
        }
        //demonstration(not required)
        for(long[] i : dp){
            for(long j : i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        return dp[denominations.length-1][value];
    }
}