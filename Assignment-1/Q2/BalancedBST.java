public class BalancedBST extends BST {
    @Override
    public void insert(int num) {
        if (root == null) {
            root = new BSTNode(num);
            return;
        }

        BSTNode parent = null;
        BSTNode curr = root;
        int bfactor = 0;

        while (curr != null) {
            if (num < curr.value) {
                parent = curr;
                curr = curr.left;
                bfactor = -1;
            } else if (num > curr.value) {
                parent = curr;
                curr = curr.right;
                bfactor = 1;
            } else {
                // duplicate value, ignore
                return;
            }
        }

        // insert new node as child of parent
        BSTNode newNode = new BSTNode(num);
        if (bfactor < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // balance the tree
        rebalance(newNode);
    }

    @Override
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

                // balance the tree
                rebalance(parent);

                return true;
            }
        }

        return false;
    }

    private BSTNode rebalance(BSTNode node) {
        if (node == null) {
            return null;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balanceFactor = Bfactor(node);

        if (balanceFactor > 1 && Bfactor(node.left) >= 0) {
            return Rrotate(node);
        }

        if (balanceFactor > 1 && Bfactor(node.left) < 0) {
            node.left = Lrotate(node.left);
            return Rrotate(node);
        }

        if (balanceFactor < -1 && Bfactor(node.right) <= 0) {
            return Lrotate(node);
        }

        if (balanceFactor < -1 && Bfactor(node.right) > 0) {
            node.right = Rrotate(node.right);
            return Lrotate(node);
        }

        return node;
    }


    private int Bfactor(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private int height(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    private BSTNode Rrotate(BSTNode node) {
        BSTNode pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }

    private BSTNode Lrotate(BSTNode node) {
        BSTNode pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }
}


