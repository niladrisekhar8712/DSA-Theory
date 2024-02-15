import java.util.*;

class Pair{
    String word;
    int length;

    public Pair(String word, int length) {
        this.word = word;
        this.length = length;
    }
}
public class Main {
    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(ladderLength(begin,end, Arrays.stream(wordList).toList()));
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        Set<String> set = new HashSet<>(wordList);
        q.offer(new Pair(beginWord,1));
        while (!q.isEmpty()){
            String word = q.peek().word;
            int steps = q.poll().length;
            if(word.equals(endWord)) return steps;
            for(int i = 0;i< word.length();i++){
                for(char j = 'a';j<='z';j++){
                    char[] charArray = word.toCharArray();
                    charArray[i] = j;
                    String replaced = new String(charArray);
                    if(set.contains(replaced)){
                        q.offer(new Pair(replaced,steps+1));
                        set.remove(replaced);
                    }
                }
            }
        }
        return 0;
    }
}