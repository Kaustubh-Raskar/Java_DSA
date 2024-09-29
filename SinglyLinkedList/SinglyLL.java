class Node {
    int data;
    Node next;
    
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class SinglyLL {
   
    public static void displayList(Node head){

        Node current = head;

        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static Node insertAtFirst(Node head,int data){

        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        return head;
    }

    public static Node insertAtLast(Node head, int data){

        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return head;
        }

        Node current = head;

        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;

        return head;
    }


    public static void main(String[] args){

        Node head = null;

        head = insertAtFirst(head, 10);
        head = insertAtFirst(head, 20);
        head = insertAtLast(head, 30);
        System.out.println("List elements are as below: ");
        displayList(head);


    }
}

