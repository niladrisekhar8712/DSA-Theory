import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println(eventualSafeNodes(arr));
    }
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adjRev = new ArrayList<>();
        for(int i = 0;i< graph.length;i++){
            adjRev.add(new ArrayList<>());
        }
        int[] inDegree = new int[graph.length];
        for(int i = 0;i<graph.length;i++){
            for(int it : graph[i]){
                adjRev.get(it).add(i);
                inDegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> safeNodes = new ArrayList<>();
        for(int i = 0;i< graph.length;i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            safeNodes.add(node);
            for(int it : adjRev.get(node)){
                inDegree[it]--;
                if(inDegree[it] == 0){
                    q.offer(it);
                }
            }
        }
        safeNodes.sort(Comparator.naturalOrder());
        return safeNodes;
    }
}