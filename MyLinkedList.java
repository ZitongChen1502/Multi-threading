public class MyLinkedList {
    Node head;

    public MyLinkedList(Node n){
        head = n;
    }
    public boolean add(Node head, Node n){
        if(head.isEnd()==true){
            head.setNext(n);
            return true;
        }
        else{
            return add(head.getNext(), n);
        }
    }

    public void addFirst(Node n){
        Node k = n;
        k.setNext(head);
        head = k;
    }

    public void addLast(Node n){
        boolean finish = this.add(head, n);
    }

    public void clear(){
        head = null;
    }

    public Node getFirst(){
        return head;
    }

    public Node getLast(){
        Node last = head;
        while(last.isEnd() == false){
            last = last.getNext();
        }
        return last;
    }

    public String displayList(){
        String print ="";
        if(head != null) {
            Node last = new Node(head.getData());
            last.setNext(head.getNext());
            while (last.isEnd() == false) {
                print+= last.getData();
                last = last.getNext();
            }
            print+= (String)this.getLast().getData();
        }
        return print;
    }

    public Node removeFirst(){
        Node first = head;
        head = first.getNext();
        return first;
    }

    public boolean remove(Node n){
        if(head.getData()== n.getData()){
            Node finish =this.removeFirst();
            return true;
        }

        else{
            Node search = head;
            boolean exist = false;
            while(search.isEnd()==false){
                if(search.getNext().getData() != n.getData()){
                    search = search.getNext();
                }
                else{search.setNext(search.getNext().getNext());
                    exist = true;
                }
            }
            return exist;
        }
    }

    public Node set(int index, Node n){
        Node bye;
        if(this.size() +1 < index){
            return null;
        }
        else if(this.size() == index-1){
            this.addLast(n);
            return null;
        }
        else if(index ==0) {
            bye = head;
            n.setNext(head.getNext());
            head = n;
            return bye;
        }
        else{
            bye = head;
            for(int i = 1; i<index-1; i++){
                bye = head.getNext();
            }
            Node orig = bye;
            n.setNext(bye.getNext().getNext());
            bye.setNext(n);
            return orig.getNext();
        }
    }

    public int size(){
        int size = 0;
        Node pointer= head;
        while(pointer != null){
            size++;
            pointer= pointer.getNext();
        }
        return size;
    }
    public String print(){
        String print = ""+ head.getData();
        Node n = head;
        while(n.getNext()!= null){
            print += n.getNext().getData();
            n=n.getNext();
        }
        return print;
    }

}
