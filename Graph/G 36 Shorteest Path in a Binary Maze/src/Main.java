import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/* Don't know why this gives TLC */
class Tuple {
    int first;
    Point point;

    public Tuple(int first, Point point) {
        this.first = first;
        this.point = point;
    }
}
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static void main(String[] args) {
//        1 1 1 1
//        0 1 1 0
//        0 0 1 1
        int[][] matrix = {{1,1,1,1},{0,1,1,0},{0,0,1,1}};
        System.out.println(shortestPathBinaryMatrix(matrix,new Point(0,0),new Point(2,3)));
    }
    public static int shortestPathBinaryMatrix(int[][] matrix, Point src, Point dest) {
        if(src.x == dest.x && src.y == dest.y) return 0; // source == destination
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] distance = new int[n][m];
        for(int[] i : distance){
            Arrays.fill(i,(int)1e9);
        }
        distance[src.x][src.y] = 0;
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(0,src)); /// first is distance then point(row,col)
        while(!q.isEmpty()){
            int dist = q.peek().first;
            int row = q.peek().point.x;
            int col = q.peek().point.y;
            q.poll();
            int[] dr = {-1,0,1,0};
            int[] dc = {0,1,0,-1};
            for(int i = 0;i<4;i++){
                int nrow = row + dr[i];
                int ncol = col + dc[i];
                if(nrow >= 0 && nrow<n && ncol >= 0 && ncol<m && matrix[nrow][ncol] == 1 && 1 + dist < distance[nrow][ncol]){
                    q.offer(new Tuple(1+dist,new Point(nrow,ncol)));
                    if(nrow == dest.x && ncol == dest.y) return 1 + dist;
                }
            }
        }
        return -1;
    }
}