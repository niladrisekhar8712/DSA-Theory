import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(arr));
    }
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        return bestTabulation(words);
    }
    private static int bestTabulation(String[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int max = (int) -1e9;
        for(int ind = 0;ind < n;ind++){
            for(int prev = 0;prev<=ind-1;prev++){
                if(compare(arr[ind],arr[prev])){
                    dp[ind] = Math.max(1+dp[prev],dp[ind]);
                }
            }
            max = Math.max(max,dp[ind]);
        }
        return max+1;
    }
    private static boolean compare(String s1, String s2){
        if(s1.length() - s2.length() != 1){
            return false;
        }
        int i = 0;
        int j = 0;
        while(i<s1.length()){
            if(j < s2.length() && s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }
            else{
                i++;
            }
        }
        if(i == s1.length() && j == s2.length()){
            return true;
        }
        return false;
    }
}