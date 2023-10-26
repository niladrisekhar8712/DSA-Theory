import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
        randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
        randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
        randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
        randomizedSet.insert(2); // 2 was already in the set, so return false.
        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2. // Since 2 is the only number in the set, getRandom() will always return 2.
    }
}
class RandomizedSet {
//    public HashSet<Integer> set; // for contains
    public LinkedList<Integer> arr; // for random

    public RandomizedSet() {
//        this.set = new HashSet<>();
        this.arr = new LinkedList<>();
    }

    public boolean insert(int val) {
        if (arr.contains(val)) {
            return false;
        }
        arr.add(val);
//        arr.add(val);
        return true;
    }

    public boolean remove(int val) {
        return arr.remove(val);
    }

    public int getRandom() {
//        arr.addAll(set);
        Random random = new Random();
        int in = random.nextInt(0, arr.size());
        System.out.println(arr);
        return arr.get(in);
    }
}
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */