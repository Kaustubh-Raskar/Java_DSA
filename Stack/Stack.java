// Basic Stack operations
// Stack using LinkedList logic

class Stack {
    StackNode root;

    static class StackNode {
        int data;
        StackNode next;

        StackNode(int data){
            this.data = data;
        }
    }

    public boolean isEmpty(){
        if(root == null){
            return true;
        } else{
            return false;
        }
    }

    public void push(int data){

        StackNode newNode = new StackNode(data);

        if(root == null){
            root = newNode;
        }
        else{
            StackNode temp = root;
            root = newNode;
            newNode.next = temp;
        }
        System.out.println(data + " pushed to stack.");
    }

         
}