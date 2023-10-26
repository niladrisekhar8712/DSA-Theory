import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> heap = new ArrayList<>();
        int[] arr = {80,70,45,24,59,37,12,14,10,48,32};
        heap.addFirst(-1);
        for(int i :arr){
            insert(heap,i);
        }
//        int data = 90;
//        System.out.println(heap);
//        insert(heap, data);
        System.out.println(heap);
        delete(heap,1);
        System.out.println(heap);
    }
    public static void insert(List<Integer> heap, int data){
        heap.add(data);
        int dataIndex = heap.size()-1; // last index;
        int par = dataIndex/2;
        while(par>=1 && heap.get(par) < data){
            heap.set(dataIndex, heap.get(par));
            heap.set(par,data);
            dataIndex = par;
            par = dataIndex/2;
        }
    }
    public static void delete(List<Integer> heap, int dataIndex){
        int data = heap.get(dataIndex);
        heap.remove(dataIndex);
        int dataToBeReplaced = heap.get(heap.size()-1);
        int lchild = 2*dataIndex;
        int rchild = lchild+1;
        while(rchild<heap.size()){
            if(dataToBeReplaced > heap.get(lchild) && dataToBeReplaced > heap.get(rchild)){
                heap.set(dataIndex,dataToBeReplaced);
                break;
            } else if (heap.get(lchild) > heap.get(rchild)) {
                heap.set(dataIndex,heap.get(lchild));
                dataIndex = lchild;
            }
            else {
                heap.set(dataIndex,heap.get(lchild));
                dataIndex = lchild;
            }
            lchild = 2*dataIndex;
            rchild = lchild+1;
        }
        if(lchild == heap.size() && dataToBeReplaced<heap.get(lchild)){
            heap.set(dataIndex,heap.get(lchild));
            dataIndex = lchild;
        }
        heap.set(dataIndex,dataToBeReplaced);
    }
}