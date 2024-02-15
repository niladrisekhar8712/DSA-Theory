import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
class Tuple {
    int distance;
    int row;
    int col;

    public Tuple(int distance, int row,int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] mat = {{1,2,2},{1,8,2},{1,3,5}};
        System.out.println(shortestPathBinaryMatrix(mat));
    }
    public static int shortestPathBinaryMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x,y)-> x.distance - y.distance);
        int[][] dist = new int[n][m];
        for(int[] i : dist){
            Arrays.fill(i,(int)1e9);
        }
        dist[0][0] = 0;
        pq.add(new Tuple(0,0,0));
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};
        while(!pq.isEmpty()){
            int diff = pq.peek().distance;
            int row = pq.peek().row;
            int col = pq.peek().col;
            if(row == n-1 && col == m-1) return diff;
            pq.poll();
            for(int i = 0;i<4;i++){
                int nrow = row+dr[i];
                int ncol = col+dc[i];
                if(nrow>=0 && ncol>=0 && nrow<n && ncol<m){
                    int newEffort = Math.max(Math.abs(matrix[row][col]-matrix[nrow][ncol]),diff);
                    if(newEffort < dist[nrow][ncol]){
                        dist[nrow][ncol] = newEffort;
                        pq.add(new Tuple(newEffort,nrow,ncol));
                    }
                }
            }
        }
        return 0;
//        Math.max(effort[row][col],)
    }
}