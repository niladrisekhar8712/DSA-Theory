import java.util.*;

class Pair{
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
class Tuple{
    int first;
    int second;
    int third;

    public Tuple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] i : flights){
            adj.get(i[0]).add(new Pair(i[1],i[2]));
        }
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0,src,0));
        int[] distance = new int[n];
        Arrays.fill(distance,(int) 1e9);
        distance[src] = 0;
        while(!q.isEmpty()){
            Tuple it = q.poll();
            int stops = it.first;
            int node = it.second;
            int cost = it.third;
            if(stops > k) continue;
            for(Pair iter : adj.get(node)){
                int adjNode = iter.first;
                int edW = iter.second;
                if(cost+edW < distance[adjNode] && stops<=k){
                    distance[adjNode] = cost+edW;
                    q.add(new Tuple(stops+1,adjNode,cost+edW));
                }
            }
        }
        if(distance[dst] == 1e9) return -1;
        return distance[dst];
    }
}