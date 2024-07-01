public class LinkedList{ 
    
    public Node head;
    
    public LinkedList(){
        head = null;
    }

    public void insert(int c){
        //to be completed by the student
        Node n = new Node(c);

        if (head==null){
            head = n;
        }
        else{
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
        }
    }

    public int len(){
        //to be completed by the student
        int count = 0;
        Node curr = head;
        while(curr!=null){
            curr = curr.next;
            count++;
        }
        return count;
    }

    public Node get(int index){
        if (index<0){
            return null;
        }
        Node curr = null;
        if (head!=null){
            curr = head;
            for (int i=0;i<index;i++){
                if (curr.next == null){
                    return null;
                }
                curr = curr.next;
            }
        }
        return curr;
    }
}

