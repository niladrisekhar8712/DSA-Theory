import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class Pair{
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static boolean detectCycle(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V+1];
        for(int i = 1;i<=V;i++){
            if(!vis[i]){
                if(detectCycleInComponent(i,adj,vis)){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean detectCycleInComponent(int src, List<List<Integer>> adj,boolean[] vis){
        vis[src] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src,-1));
        while(!q.isEmpty()){
            int node = q.peek().first;
            int parent = q.poll().second;
            for(int i : adj.get(node)){
                if(!vis[i]){
                    vis[i] = true;
                    q.add(new Pair(i,node));
                } else if (i != parent) {
                    return true;
                }
            }
        }
        return false;
    }
}