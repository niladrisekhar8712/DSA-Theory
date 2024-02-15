import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] node = {{0,2},{1,2},{3,1},{0,4}};
        System.out.println(topologicalSort(node,4,5));
        System.out.println(kahnAlgorithm(node,4,5));;
    }
    public static List<Integer> topologicalSort(int[][] edges, int e, int v) {
        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[v];
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int U = edge[0];
            int V = edge[1];
            adjacencyList.get(U).add(V);
        }
        System.out.println(adjacencyList);
        for(int i = 0;i<v;i++){
            if(!vis[i]){
                dfs(i,adjacencyList,vis,stack);
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
    private static void dfs(int node,List<List<Integer>> edges,boolean[] vis,Stack<Integer> stack){
        vis[node] = true;
        for(int i : edges.get(node)){
            if(!vis[i]){
                dfs(i,edges,vis,stack);
            }
        }
        stack.push(node);
    }
    // Kahn Algorithm
    public static List<Integer> kahnAlgorithm(int[][] edges, int e, int v) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int U = edge[0];
            int V = edge[1];
            adjacencyList.get(U).add(V);
        }
        int[] inDegree = new int[v];
        for(int i = 0;i<v;i++){
            for(int j = 0;j<adjacencyList.get(i).size();j++){
                inDegree[adjacencyList.get(i).get(j)]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<inDegree.length;i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            list.add(node);
            for(int it : adjacencyList.get(node)){
                inDegree[it]--;
                if(inDegree[it] == 0){
                    q.offer(it);
                }
            }
        }
        return list;
    }
}