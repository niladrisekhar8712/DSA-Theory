import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1,0},{0,1}};
        System.out.println(canFinish(3,arr));
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            int U = edge[0];
            int V = edge[1];
            adjacencyList.get(U).add(V);
        }
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                inDegree[adjacencyList.get(i).get(j)]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            list.add(node);
            for (int it : adjacencyList.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) {
                    q.offer(it);
                }
            }
        }
        Collections.reverse(list);
        int[] arr = new int[numCourses];
        for(int i = 0;i<numCourses;i++){
            arr[i] = list.get(numCourses-i-1);
        }
        System.out.println(Arrays.toString(arr));
        return list.size() == numCourses;
    }
}