import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = {3,2,1,5,7,8};
        System.out.println(EqualSum(a,6));
    }

    public static ArrayList<Integer> EqualSum(int a[], int n)
    {
        int leftSum = 0;
        int rightSum = Arrays.stream(a).sum();
        int mini = rightSum;
        int index = 0;
        for(int i = 0;i<n;i++){
            leftSum += a[i];
            rightSum -= a[i];
            if(Math.abs(rightSum-leftSum) < mini){
                mini = Math.abs(rightSum - leftSum);
            }
            else{
                leftSum -= a[i];
                rightSum += a[i];
                index = i;
                break;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(mini);
        ans.add(index+1);
        if(leftSum>rightSum) ans.add(2);
        else ans.add(1);
        return ans;
    }
}