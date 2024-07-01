public class Polynomial extends LinkedList{
    
    public Polynomial add(Polynomial p){
        //to be implemented by the student

        Polynomial list = new Polynomial();
        Node curr1 = this.head;
        Node curr2 = p.head;


        if (this.len() >= p.len()){
            for (int i=0;i<this.len()-p.len();i++){
                list.insert(curr1.data);
                curr1 = curr1.next;
            }

            for (int i=this.len()-p.len();i<this.len();i++){
                list.insert(curr1.data+curr2.data);
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }

        else{
            for (int i=0;i<p.len()-this.len();i++){
                list.insert(curr2.data);
                curr2 = curr2.next;
            }

            for (int i=p.len()-this.len();i<p.len();i++){
                list.insert(curr1.data+curr2.data);
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }

        while (list.head.data == 0) {
            list.head = list.head.next;
        }

        return list;
    }

    public Polynomial mult(Polynomial p){
        //to be implemented by the student
        Polynomial result = new Polynomial();
        int n = this.len() + p.len() - 1;
        for (int b=0; b<n;b++){
            result.insert(0);
        }

        for (int i=0;i<this.len();i++){
            for (int j=0;j<p.len();j++){
                int newcoeff = this.get(i).data * p.get(j).data;
                int newpow = i+j;

                result.get(newpow).data+=newcoeff;
            }
        }

        while (result.head.data == 0) {
            result.head = result.head.next;
        }

        return result;
    }


}