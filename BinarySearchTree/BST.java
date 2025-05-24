public class BST{

    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root, int data){

        Node newNode = new Node(data);

        if(root == null){
            root = newNode;
            return root;
        }

        if(root.data > data){
            root.left = insert(root.left,data);
        }
        else{
            root.right = insert(root.left,data);
        }

        return root;

    }

    public static Node delete(Node root, int data){

        if(root == null){
            return root;
        }

        if(root.data > data) {
            root.left = delete(root.left, data);
        }
        else if(root.data < data){
            root.right = delete(root.right, data);
        }
        else if(root.data == data){

            if(root.left == null && root.right == null){
                return root;
            }
            else if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }
        return root;
    }

    public static Node inorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

}