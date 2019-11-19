public class QueueLL {
    Node head;
    public QueueLL(){
        Node head;
    }
    public Node enqueue(Node n){
        Node end = head;
        if(head == null){
            head = n;
            return null;
        }
        else{while(end.getNext()!= null){
            end=end.getNext();
        }
        end.setNext(n);
        return end;}
    }
    public Node dequeue(){
        if(head.getNext()!= null){
            Node second = head.getNext();
            head = second;
        }
        else{
            head = null;
        }
        return head;
    }
    public Node peek(){
        return head;
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
        if(head ==null){
            return true;
        }
        else{return false;}
    }

}


/*
E enqueue(E item) - Pushes an item onto the bottom/back of this queue.
E dequeue() - Removes the object at the top of this queue and returns that object as the value of this function.
E peek()- Looks at the object at the top of this queue without removing it from the queue.
void display() - Outputs the queue order
int size() - Returns number of items in the queue
boolean isEmpty - Checks if queue is currently empty
 */