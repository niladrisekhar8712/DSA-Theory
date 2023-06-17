import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    private final Map<T, List<T>> map = new HashMap<>();
    public void insertVertex(T vertex){
        map.put(vertex,new LinkedList<>());
    }
    public void deleteVertex(T vertex){
        for(T i : map.keySet()){
            map.get(i).remove(vertex);
        }
        map.remove(vertex);
    }
    public void insertEdge(T source, T destination){
        if(!map.containsKey(source)){
            System.out.println("Source vertex does not exist");
            return;
        }
        if(!map.containsKey(destination)){
            System.out.println("Destination vertex does not exist");
            return;
        }
        map.get(source).add(destination);
    }
    public void deleteEdge(T source, T destination){
        if(!map.containsKey(source)){
            System.out.println("Source vertex does not exist");
            return;
        }
        if(!map.containsKey(destination)){
            System.out.println("Destination vertex does not exist");
            return;
        }
        if(!map.get(source).contains(destination)){
            System.out.println("Edge does not exist between the given source and destination");
        }
        map.get(source).remove(destination);
    }
    public void display(){
        System.out.println("The adjacency list is: ");
        StringBuilder sb = new StringBuilder();
        for(T i : map.keySet()){
            sb.append(i.toString()).append(" : ");
            for(T j : map.get(i)){
               sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}