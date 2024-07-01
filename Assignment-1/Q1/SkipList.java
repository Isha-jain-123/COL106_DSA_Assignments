import java.util.ArrayList;
import java.util.Collections;


public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;
        private int size = 0;


        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        public boolean delete(int num) {
            SkipListNode node = head;
            boolean deleted = false;
            for (int i = height - 1; i >= 0; i--) {
                while (node.next.get(i) != null && node.next.get(i).value < num) {
                    node = node.next.get(i);
                }
                if (node.next.get(i) != null && node.next.get(i).value == num) {
                    node.next.set(i, node.next.get(i).next.get(i));
                    deleted = true;
                }
            }
            if (deleted) {
                size--;
                if (size == 0) {
                    height = 1;
                } else {
                    while (height > 1 && head.next.get(height - 1) == null) {
                        height--;
                    }
                }
            }
            return deleted;
        }

        public boolean search(int num){
            // TO be completed by students
            SkipListNode curr = head;
            for (int i = height - 1; i >= 0; i--) {
                while (curr.next.get(i) != null && curr.next.get(i).value < num) {
                    curr= curr.next.get(i);
                }
                if (curr.next.get(i)!=null && curr.next.get(i).value==num){
                    return true;
                }
            }
            return false;
//            return false;
        }

        public Integer upperBound(int num){ 
            // TO be completed by students
            SkipListNode curr = head;
            for (int i = height - 1; i >= 0; i--) {
                while (curr.next.get(i).value <= num) {
                    curr = curr.next.get(i);
//                    System.out.println(curr.value);
                }
            }
//            System.out.println(curr.value);
            curr = curr.next.get(0);
            if (curr == tail) {
                return Integer.MAX_VALUE;
            } else {
                return curr.value;
            }
//            return Integer.MAX_VALUE;
        }

        public void insert(int num){
            // TO be completed by students
            int h = 1;
            SkipListNode curr = head;
            
            while (h < height + 1) {
                if (randomizer.binaryRandomGen()){
                    h++;
                }
                else{break;}
            }
            while (h > height) {
                head.next.add(tail);
                head.height++;
                tail.next.add(null);
                tail.height++;
                height++;
            }
            SkipListNode n = new SkipListNode(num, h);
            for (int i=0;i<h;i++){
                n.next.add(i,null);
            }
            for (int i = height - 1; i >= 0; i--) {
                while (curr.next.get(i).value < num) {
                    curr = curr.next.get(i);
                }
                if (i < h) {
                    n.next.set(i, curr.next.get(i));
                    curr.next.set(i, n);
                }
            }
            size++;
//            System.out.println(n.value);
        }

        public boolean isEmpty() {
            return head.next.get(0) == null;
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; i--){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }
        public int size() {
            return size;
        }

}