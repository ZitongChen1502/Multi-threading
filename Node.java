public class Node<E> {
    E data;
    Node next;

    public Node(E num){
    data = num;
    Node next;
    }
    public void setNext(Node nxt){
        next = nxt;
    }
    public boolean isEnd(){
        if(next == null){
            return true;
        }
        else{
            return false;
        }
    }
    public Node getNext(){
            return next;
    }
    public E getData(){
        return data;
    }

}
