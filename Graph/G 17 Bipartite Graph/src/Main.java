import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static boolean isGraphBirpatite(ArrayList<ArrayList<Integer>> edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>(edges.size());
        for (int i = 0; i < edges.size(); i++) {
            adjacencyList.add(new ArrayList<>());
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(i).get(j) == 1) {
                    adjacencyList.get(i).add(j);
                }
            }
        }
        int[] color = new int[edges.size()];
        Arrays.fill(color,-1);
        for(int i=0;i<color.length;i++){
            if(color[i] == -1){
                if(!check(adjacencyList, i, color)) return false;
            }
        }
        return true;
    }
    private static boolean check(List<List<Integer>> adjacencyList, int start, int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            for(int it : adjacencyList.get(node)){
                if(color[it] != -1){
                    if(color[it] == color[node]){
                        return false;
                    }
                }
                else{
                    color[it] = 1 - color[node];
                    q.add(it);
                }
            }
        }
        return true;
    }
}