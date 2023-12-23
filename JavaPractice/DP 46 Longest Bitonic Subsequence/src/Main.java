public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,1,2,1};
        System.out.println(longestBitonicSequence(arr));
    }
    private static int longestBitonicSequence(int[] arr){
        int n = arr.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int max1 = (int) -1e9;
        for(int ind = 0;ind < n;ind++){
            for(int prev = 0;prev<=ind-1;prev++){
                if(arr[prev]<arr[ind]){
                    dp1[ind] = Math.max(1+dp1[prev],dp1[ind]);
                }
            }
        }
        for(int ind = n-1;ind>=0;ind--){
            for(int next = n - 1;next>=ind;next--){
                if(arr[next]<arr[ind]){
                    dp2[ind] = Math.max(1+dp2[next],dp2[ind]);
                }
            }
        }
        for(int i = 0;i<n;i++){
            max1 = Math.max(dp1[i]+dp2[i]+1,max1);
        }
        return max1;
    }
}