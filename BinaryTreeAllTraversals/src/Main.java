import java.util.*;
class Tuple{
    TreeNode node;
    int x;
    int y;

    public Tuple(TreeNode node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }
}
class Pair<S,T>{
    S first;
    T second;

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
    }
    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        TreeNode left2 = new TreeNode(6);
        TreeNode right2 = new TreeNode(7);
        TreeNode left = new TreeNode(2,left1,right1);
        TreeNode right = new TreeNode(3,left2,right2);
        TreeNode root = new TreeNode(1,left,right);
        getTreeTraversal(root);
    }
    public static void getTreeTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        inorder(root,inOrder);
        preorder(root,preOrder);
        postorder(root,postOrder);
        System.out.println("Inorder: "+inOrder);
        System.out.println("Preorder: "+preOrder);
        System.out.println("Postorder: "+postOrder);
        System.out.println("Preorder(Iterative): "+preorderIterative(root));
        System.out.println("Inorder(Iterative): "+inorderIterative(root));
        System.out.println("Postorder(Iterative 2 Stack): "+postorderIterative2stack(root));
        System.out.println("Postorder(Iterative): "+postorderIterative(root));
        System.out.println("Level Order: "+levelOrder(root));
        System.out.println("Zig Zag: "+zigZag(root));
        System.out.println("Boundary Traversal: "+boundaryTraversal(root));
        System.out.println("Vertical Order Traversal: "+verticalTraversal(root));
        System.out.println("Top View: "+getTopView(root));
        System.out.println("Bottom View: "+bottomView(root));
        System.out.println("Left View: "+leftView(root));
        System.out.println("Right View: "+rightSideView(root));
        System.out.println("Width Of Binary Tree: "+widthOfBinaryTree(root));
    }
    private static List<List<Integer>> levelOrder(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 1;i<=size;i++){
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                level.add(node.data);
            }
            levels.add(level);
        }
        return levels;
    }
    private static void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        inorder(root.left,list);
        list.add(root.data);
        inorder(root.right,list);
    }
    private static void preorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        list.add(root.data);
        preorder(root.left,list);
        preorder(root.right,list);

    }
    private static void postorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        postorder(root.left,list);
        postorder(root.right,list);
        list.add(root.data);
    }
    private static List<Integer> preorderIterative(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> preOrder = new ArrayList<>();
        if(root == null) return preOrder;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            preOrder.add(node.data);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return preOrder;
    }
    private static List<Integer> inorderIterative(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> inOrder = new ArrayList<>();
        TreeNode node = root;
        while(true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else{
                if(stack.isEmpty()) break;
                node = stack.pop();
                inOrder.add(node.data);
                node = node.right;
            }
        }
        return inOrder;
    }
    private static List<Integer> postorderIterative2stack(TreeNode root){
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            if(node.left != null) stack1.push(node.left);
            if(node.right != null) stack1.push(node.right);
            stack2.push(node);
        }
        List<Integer> postOrder = new ArrayList<>();
        while(!stack2.isEmpty()){
            postOrder.add(stack2.pop().data);
        }
        return postOrder;
    }
    private static List<Integer> postorderIterative(TreeNode root){
        List < Integer > postOrder = new ArrayList < > ();
        TreeNode cur = root;
        if (cur == null) return postOrder;
        Stack <TreeNode> st = new Stack < > ();
        while (cur != null || !st.isEmpty()) {
            if (cur != null) {
                st.push(cur);
                cur = cur.left;
            } else {
                TreeNode temp = st.peek().right;
                if (temp == null) {
                    temp = st.peek();
                    st.pop();
                    postOrder.add(temp.data);
                    while (!st.isEmpty() && temp == st.peek().right) {
                        temp = st.peek();
                        st.pop();
                        postOrder.add(temp.data);
                    }
                } else cur = temp;
            }
        }
        return postOrder;
    }
    private static List<List<Integer>> zigZag(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;
        q.offer(root);
        boolean flag = true;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 1;i<=size;i++){
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                if(flag) level.add(node.data);
                else level.addFirst(node.data);
            }
            levels.add(level);
            flag = !flag;
        }
        return levels;
    }
    private static boolean isLeaf(TreeNode node){
        if(node == null) return false;
        return (node.left == null && node.right == null);
    }
    private static void addLeft(TreeNode root, List<Integer> res){
        TreeNode curr = root.left;
        while(curr != null){
            if(!isLeaf(curr)){
                res.add(curr.data);
            }
            if(curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }
    private static void addLeaves(TreeNode root, List<Integer> res){
        if(root == null) return;
        if(isLeaf(root)){
            res.add(root.data);
            return;
        }
        addLeaves(root.left,res);
        addLeaves(root.right,res);
    }
    private static void addRight(TreeNode root, List<Integer> res){
        TreeNode curr = root.right;
        Stack<Integer> stack = new Stack<>();
        while(curr != null){
            if(!isLeaf(curr)){
                stack.add(curr.data);
            }
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
    }
    private static List<Integer> boundaryTraversal(TreeNode root){
        List<Integer> boundary = new ArrayList<>();
        if(root == null) return boundary;
        if(!isLeaf(root)) boundary.add(root.data);
        addLeft(root,boundary);
        addLeaves(root,boundary);
        addRight(root,boundary);
        return boundary;
    }
    private static List<List<Integer>> verticalTraversal(TreeNode root){
        Queue<Tuple> q = new LinkedList<>();
        List<List<Integer>> levels = new ArrayList<>();
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();
        if(root == null) return levels;
        q.offer(new Tuple(root,0,0));
        while(!q.isEmpty()){
            TreeNode node = q.peek().node;
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();
            if(!map.containsKey(x)){
                map.put(x,new TreeMap<>());
            }
            if(!map.get(x).containsKey(y)){
                map.get(x).put(y,new PriorityQueue<>());
            }
            map.get(x).get(y).offer(node.data);
            if(node.left != null) q.offer(new Tuple(node.left,x-1,y+1));
            if(node.right != null) q.offer(new Tuple(node.right,x+1,y+1));
        }
        for(TreeMap<Integer,PriorityQueue<Integer>> xs : map.values()){
            List<Integer> level = new ArrayList<>();
            for(PriorityQueue<Integer> ys : xs.values()){
                while(!ys.isEmpty()){
                    level.add(ys.poll());
                }
            }
            levels.add(level);
        }
        return levels;
    }
    public static List<Integer> getTopView(TreeNode root) {
        if(root == null) return null;
        Map<Integer,Integer> map = new TreeMap<>();
        Queue<Pair<Integer,TreeNode>> q = new LinkedList<>();
        List<Integer> topView = new ArrayList<>();
        q.offer(new Pair<>(0,root));
        while(!q.isEmpty()){
            int pos = q.peek().first;
            TreeNode node = q.poll().second;
            if(!map.containsKey(pos)){
                map.put(pos, node.data);
            }
            if(node.left != null) q.offer(new Pair<>(pos-1,node.left));
            if(node.right != null) q.offer(new Pair<>(pos+1,node.right));
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            topView.add(entry.getValue());
        }
        return topView;
    }
    public static List<Integer> bottomView(TreeNode root) {
        if(root == null) return null;
        Queue<Pair<Integer,TreeNode>> q = new LinkedList<>();
        Map<Integer,Integer> map = new TreeMap<>();
        q.offer(new Pair(0,root));
        while(!q.isEmpty()){
            int pos = q.peek().first;
            TreeNode node = q.poll().second;
            if(!map.containsKey(pos)){
                map.put(pos, node.data);
            }
            else map.put(pos, node.data);
            if(node.left != null) q.offer(new Pair<>(pos-1,node.left));
            if(node.right != null) q.offer(new Pair<>(pos+1,node.right));
        }
        List<Integer> bottomView = new ArrayList<>();
        for(int i : map.keySet()){
            bottomView.add(map.get(i));
        }
        return bottomView;
    }
    public static List<Integer> leftView(TreeNode root) {
        if(root == null) return null;
        Queue<Pair<Integer,TreeNode>> q = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        q.offer(new Pair<>(0,root));
        while(!q.isEmpty()){
            TreeNode node = q.peek().second;
            int pos = q.poll().first;
            if(!map.containsKey(pos)){
                map.put(pos,node.data);
            }
            if(node.left != null) q.add(new Pair<>(pos+1,node.left));
            if(node.right != null) q.add(new Pair<>(pos+1,node.right));
        }
        List<Integer> leftView = new ArrayList<>();
        for(int i : map.keySet()){
            leftView.add(map.get(i));
        }
        return leftView;
    }
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if(root == null) return rightView;
        Queue<Pair<Integer,TreeNode>> q = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        q.offer(new Pair<>(0,root));
        while(!q.isEmpty()){
            TreeNode node = q.peek().second;
            int pos = q.poll().first;
            map.put(pos,node.data);
            if(node.left != null) q.add(new Pair<>(pos+1,node.left));
            if(node.right != null) q.add(new Pair<>(pos+1,node.right));
        }
        for(int i : map.keySet()){
            rightView.add(map.get(i));
        }
        return rightView;
    }
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<>();
        int width = 0;
        q.add(new Pair<>(root,0));
        while(!q.isEmpty()){
            int mmin = q.peek().second;

            int size = q.size();
            int first = 0,last = 0;
            for(int i = 0;i<size;i++){
                TreeNode node = q.peek().first;
                if(i == 0) first = q.peek().second;
                if(i == size - 1) last = q.peek().second;
                int currInd = q.peek().second - mmin;
                q.poll();
                if(node.left!=null) q.add(new Pair<>(node.left,2*currInd+1));
                if(node.right!=null) q.add(new Pair<>(node.right,2*currInd+2));
            }
            width = Math.max(width,last-first+1);

        }
        return width;
    }
}