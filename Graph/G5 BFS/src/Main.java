import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(null);
        list.add(List.of(2,6));
        list.add(List.of(1,3,4));
        list.add(List.of(2));
        list.add(List.of(2,5));
        list.add(List.of(4,8));
        list.add(List.of(1,7,9));
        list.add(List.of(6,8));
        list.add(List.of(5,7));
        list.add(List.of(6));
        System.out.println("Graph is: "+list);
        System.out.println("BFS is: "+BFS(9,list));
    }
    public static List<Integer> BFS(int noOfNodes, List<List<Integer>> adjacencyList){
        List<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[noOfNodes+1]; // n+1 for 1-based indexing
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;
        while(!q.isEmpty()){
            int node = q.poll();
            bfs.add(node);
            for(int i : adjacencyList.get(node)){
                if(!vis[i]){
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
        return bfs;
    }
}