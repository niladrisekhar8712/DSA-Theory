import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {
                {1,0,0},
                {0,1,0},
                {0,0,1}
        };
        System.out.println(findCircleNum(arr));

    }
    public static int findCircleNum(int[][] isConnected) {
        List<List<Integer>> adj = makeAdjacency(isConnected);
        boolean[] vis = new boolean[isConnected.length];
        int cnt = 0;
        for(int i = 0;i<isConnected.length;i++) {
            if(!vis[i]) {
                cnt++;
                DFS(i,vis,adj);
            }
        }
        return cnt;
    }
    public static void DFS(int start,boolean[] vis, List<List<Integer>> adjacencyList) {
        vis[start] = true;
        for (var it : adjacencyList.get(start)) {
            if (!vis[it]) {
                DFS(it, vis, adjacencyList);
            }
        }
    }
    private static List<List<Integer>> makeAdjacency(int[][] mat){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i< mat.length;i++) {
            adj.add(new ArrayList<Integer>());
        }
        for(int i = 0;i< mat.length;i++) {
            for(int j = 0;j< mat.length;j++) {
                // self nodes are not considered
                if(mat[i][j] == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        return adj;
    }
}