import java.util.Arrays;

public class Main {
//    An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
//
//    You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
//
//    To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
//
//    Return the modified image after performing the flood fill.
    public static void main(String[] args) {
        int[][] image = {{1,1,1},{2,2,0},{2,2,2}};
        int[][] arr = floodFill(image,2,0,3);
        for(int[] i :arr){
            System.out.println(Arrays.toString(i));
        }
    }
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int initialColor = image[sr][sc];
        int[][] ans = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0;j<m;j++){
                ans[i][j] = image[i][j];
            }
        }
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};
        dfs(image,sr, sc, color,ans,delrow,delcol,initialColor);
        return ans;
    }
    private static void dfs(int[][] image, int sr, int sc, int color,int[][] ans,int[] delrow,int[] delcol,int iniColor){
        int n = image.length;
        int m = image[0].length;
        ans[sr][sc] = color;
        for(int i = 0;i<4;i++){
            if(sr+delrow[i]>=0 && sr+delrow[i]<n && sc+delcol[i]>=0 && sc+delcol[i]<m && image[sr+delrow[i]][sc+delcol[i]] == iniColor && ans[sr+delrow[i]][sc+delcol[i]] != color){
                dfs(image,sr+delrow[i],sc+delcol[i],color,ans,delrow,delcol,iniColor);
            }

        }
    }
}