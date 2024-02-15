import java.util.ArrayList;
import java.util.List;

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
        // driver code
        boolean[] vis = new boolean[list.size()];
//        vis[1] = true;
        List<Integer> dfs = new ArrayList<>();
        System.out.println(DFS(1,vis,list,dfs));
    }
    public static List<Integer> DFS(int start,boolean[] vis, List<List<Integer>> adjacencyList,List<Integer> dfs){
        vis[start] = true;
        dfs.add(start);
        for(var it : adjacencyList.get(start)){
            if(!vis[it]){

                DFS(it,vis,adjacencyList,dfs);
            }
        }
        return dfs;
    }
}