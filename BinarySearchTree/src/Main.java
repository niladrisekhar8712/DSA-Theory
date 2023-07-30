public class Main {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        TreeNode root = null;
        root = binarySearchTree.insertNode(root,50);
        root = binarySearchTree.insertNode(root,30);
        root = binarySearchTree.insertNode(root,60);
        root = binarySearchTree.insertNode(root,38);
        root = binarySearchTree.insertNode(root,35);
        root = binarySearchTree.insertNode(root,55);
        root = binarySearchTree.insertNode(root,22);
        root = binarySearchTree.insertNode(root,59);
        root = binarySearchTree.insertNode(root,94);
        root = binarySearchTree.insertNode(root,13);
        root = binarySearchTree.insertNode(root,98);
        binarySearchTree.inorder(root);
        System.out.println();
        root = binarySearchTree.deleteNode(root,13);
        binarySearchTree.inorder(root);
        System.out.println();
        root = binarySearchTree.deleteNode(root,99);
        binarySearchTree.inorder(root);
        System.out.println();
        root = binarySearchTree.insertNode(root,98);
        binarySearchTree.inorder(root);
        System.out.println();
        root = binarySearchTree.insertNode(root,69);
        binarySearchTree.inorder(root);
        System.out.println();
        root = binarySearchTree.deleteNode(root,59);
        binarySearchTree.inorder(root);
        System.out.println(binarySearchTree.min(root));
        System.out.println(binarySearchTree.max(root));
        System.out.println(binarySearchTree.height(root));
        System.out.println(binarySearchTree.countNodes(root));
    }
}