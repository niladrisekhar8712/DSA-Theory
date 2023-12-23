import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,16,7,8,4};
        System.out.println(divisibleSet(arr));
    }
    public static List< Integer > divisibleSet(int []arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.sort(arr);
        int lastIndex = 0;
        Arrays.fill(dp,1);
        int max = (int) -1e9;
        for(int ind = 0;ind < n;ind++){
            hash[ind] = ind;
            for(int prev = 0;prev<=ind-1;prev++){
                if(arr[ind]%arr[prev] == 0 && 1+dp[prev] > dp[ind]){
                    hash[ind] = prev;
                    dp[ind] = 1+dp[prev];
                }
            }
            if(dp[ind] > max){
                lastIndex = ind;
                max = dp[ind];
            }
        }
        List<Integer> lis = new ArrayList<>();
        lis.add(arr[lastIndex]);
        int ind = 1;
        while (hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            lis.add(arr[lastIndex]);
        }
        lis.sort(Comparator.naturalOrder());
        return lis;
    }
}