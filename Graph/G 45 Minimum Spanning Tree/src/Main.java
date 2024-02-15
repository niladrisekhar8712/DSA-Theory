import java.util.*;
class Pair{
    int first;
    int second;
    public Pair(int first,int second){
        this.first = first;
        this.second = second;
    }
}
public class Main {
    public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for (ArrayList<Integer> edge : edges) {
            // first is node, second is weight
            adj.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            adj.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        // System.out.println(adj);
        int sum = 0;
        boolean[] visited = new boolean[n];
        // first is weight, second is node
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->x.first-y.first);
        pq.offer(new Pair(0,0));
        while(!pq.isEmpty()){
            Pair current = pq.poll();
            if(!visited[current.second]){
                visited[current.second] = true;
                sum += current.first;
                for(Pair adjacent : adj.get(current.second)){
                    int node = adjacent.first;
                    int weight = adjacent.second;
                    if(!visited[node]){
                        pq.offer(new Pair(weight, node));
                    }
                }
            }
        }
        return sum;
    }
}