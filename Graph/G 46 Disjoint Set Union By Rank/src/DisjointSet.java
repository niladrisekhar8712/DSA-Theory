import java.util.Arrays;

public class DisjointSet {
    public int[] rank;
    public int[] parent;
    public int[] size;
    public DisjointSet(int n){
        size = new int[n+1];
        Arrays.fill(size,1);
        rank = new int[n+1];
        parent = new int[n+1];
        for(int i = 0;i<=n;i++){
            parent[i] = i;
        }
    }
    public int findUPar(int node){
        if(parent[node] == node){
            return node;
        }
        return parent[node] = findUPar(parent[node]);
    }
    public void unionByRank(int u, int v){
        int up_u = findUPar(u);
        int up_v = findUPar(v);
        if(up_v == up_u) return;
        if(rank[up_u] > rank[up_v]){
            parent[up_v] = up_u;
        }
        else if(rank[up_v] > rank[up_u]){
            parent[up_u] = up_v;
        }
        else{
            parent[up_v] = up_u;
            rank[up_u]++;
        }
    }
    public void unionBySize(int u, int v){
        int up_u = findUPar(u);
        int up_v = findUPar(v);
        if(up_v == up_u) return;
        if(size[up_u] > size[up_v]){
            parent[up_v] = up_u;
            size[up_u] += size[up_v];
        }
        else if(size[up_v] > size[up_u]){
            parent[up_u] = up_v;
            size[up_v] += size[up_u];
        }
        else{
            parent[up_u] = up_v;
            size[up_v] += size[up_u];
        }
    }
}
