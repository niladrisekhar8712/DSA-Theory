class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    public TreeNode insertNode(TreeNode root, int ikey){
        if(root == null){
            root = new TreeNode(ikey);
        } else if (ikey < root.data) {
            root.left = insertNode(root.left,ikey);
        } else if (ikey > root.data) {
            root.right = insertNode(root.right,ikey);
        }
//        System.out.println(root.data);
        return root;
    }
    public TreeNode deleteNode(TreeNode root, int dkey){
        TreeNode succ, tmp;
        if(root == null){
            return root; //key is not found
        } else if (dkey < root.data) {
            root.left = deleteNode(root.left,dkey);
        } else if (dkey > root.data) {
            root.right = deleteNode(root.right,dkey);
        } else {
            if(root.left != null && root.right != null){
                succ = root.right;
                while(succ.left != null){
                    succ = succ.left;
                }
                root.data = succ.data;
                root.right = deleteNode(root.right, succ.data);
            } else {
                tmp = root;
                if(root.left != null){ // only has left child
                    root = root.left;
                } else if (root.right != null) {
                    root = root.right;
                } else root = null;
                tmp = null;
            }
        }
        return root;
    }
    public void inorder(TreeNode root){
        if(root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
    public int min(TreeNode root){
        if(root == null){
            return root.data;
        }
        TreeNode tmp = root;
        while(tmp.left != null){
            tmp = tmp.left;
        }
        return tmp.data;
    }
    public int max(TreeNode root){
        if(root == null){
            return root.data;
        }
        TreeNode tmp = root;
        while(tmp.right != null){
            tmp = tmp.right;
        }
        return tmp.data;
    }
    public int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
    }
    public int countNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return leftCount+rightCount+1;
    }
}
