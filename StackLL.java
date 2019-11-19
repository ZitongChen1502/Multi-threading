public class StackLL {
    Node head;
    public StackLL(){
        Node head;
    }
    public void setHead(Node h){
        head =h;
    }
    public Node push(Node n){
        if(head == null){
            head = n;
            return null;
        }
       else{
        Node first = head;
        this.setHead(n);
        head.setNext(first);
        return first;}
    }
    public Node pop(){
        Node bye = head;
        if(head!=null && head.getNext()!=null){
        this.setHead(head.getNext());
        return bye;}
        else if(head!=null){
            this.setHead(null);
            return bye;
        }
        else{
            return null;
        }
    }
    public Object peek(){
        if(head!=null){
        return head.getData();}
        else{
            return null;
        }
    }
    public String display(){
        MyLinkedList ll = new MyLinkedList(head);
        return ll.displayList();
    }
    public int size(){
        MyLinkedList ll = new MyLinkedList(head);
        return ll.size();
    }
    public boolean isEmpty(){
        if(head!=null){
            return false;
        }
        else{
            return true;
        }
    }
}


/*
E push​(E item) - Pushes an item onto the top of this stack.
E pop() - Removes the object at the top of this stack and returns that object as the value of this function.
E peek()- Looks at the object at the top of this stack without removing it from the stack.
void display() - Outputs the stack order
int size() - Returns number of items in the stack
boolean isEmpty - Checks if stack is currently empty
 */