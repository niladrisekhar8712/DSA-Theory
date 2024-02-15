public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static boolean isCyclic(int[][] edges, int v, int e)
    {
        boolean[] vis = new boolean[v];
        boolean[] pathVis = new boolean[v];
        for(int i=0;i<v;i++){
            if(!vis[i]){
                if(dfs(edges,v,i,vis,pathVis)) return true;
            }
        }
        return false;
    }
    private static boolean dfs(int[][] edges, int v, int node,boolean[] vis,boolean[] pathVis){
        vis[node] = true;
        pathVis[node] = true;
        for(int i = 0;i<v;i++){
            if(!vis[i]){
                if(dfs(edges, v, i, vis, pathVis)) return true;
            } else if (pathVis[i]) {
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }

}