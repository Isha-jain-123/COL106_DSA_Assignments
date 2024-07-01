import java.util.*;
public class BST {

    public BSTNode root;

    public BST() {
        root = null;
    }

    public void insert(int num) {
        // TO be completed by students
        BSTNode n = new BSTNode(num);
        if (root == null) {
            root = n;
            return;
        }
        else{
            BSTNode curr = root, parent = null;
            while (true){
                parent = curr;

                if (num<curr.value){
                    curr = curr.left;
                    if (curr == null){
                        parent.left = n;
                        return;
                    }
                }

                else{
                    curr = curr.right;
                    if (curr == null){
                        parent.right = n;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int num) {
        BSTNode parent = null;
        BSTNode curr = root;

        while (curr != null) {
            if (num < curr.value) {
                parent = curr;
                curr = curr.left;
            } else if (num > curr.value) {
                parent = curr;
                curr = curr.right;
            } else {
                // found the node to delete
                if (curr.left == null && curr.right == null) {
                    // case 1: node has no children
                    if (parent == null) {
                        root = null;
                    } else if (parent.left == curr) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (curr.left == null || curr.right == null) {
                    // case 2: node has one child
                    BSTNode child = (curr.left != null) ? curr.left : curr.right;
                    if (parent == null) {
                        root = child;
                    } else if (parent.left == curr) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                } else {
                    // case 3: node has two children
                    BSTNode predecessor = curr.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    int temp = curr.value;
                    curr.value = predecessor.value;
                    predecessor.value = temp;
                    parent = curr;
                    curr = curr.left;
                    num = temp;
                    continue;
                }
                return true;
            }
        }

        return false;
    }


    public boolean search(int num) {
        // TO be completed by students
        while (root != null) {
            if (num < root.value) {
                root = root.left;
            } else if (num > root.value) {
                root = root.right;
            } else {
                return true; // key found
            }
        }
        return false; // key not found
    }

    public ArrayList<Integer> inorder() {
        // TO be completed by students
        ArrayList<Integer> al = new ArrayList<>();
        Stack<BSTNode> stack = new Stack<>();

        BSTNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            al.add(current.value);
            current = current.right;
        }
        return al;
    }
    public ArrayList<Integer> preorder() {
        // TO be completed by students
        ArrayList<Integer> al = new ArrayList<>();
        Stack<BSTNode> stack = new Stack<>();

        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            BSTNode curr = stack.pop();
            al.add(curr.value);

            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return al;
    }
    public ArrayList<Integer> postorder() {
        // TO be completed by students
        ArrayList<Integer> al = new ArrayList<>();
        Stack<BSTNode> stack1 = new Stack<>();
        Stack<BSTNode> stack2 = new Stack<>();

        if (root != null) {
            stack1.push(root);
        }

        while (!stack1.isEmpty()) {
            BSTNode current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            BSTNode current = stack2.pop();
            al.add(current.value);
        }
        return al;
    }
}