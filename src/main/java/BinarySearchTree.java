import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class BinarySearchTree {
    BST tree = new BST();
    static String path = "src/main/resources/in/";

    public void readFileInputAlgo2(String name) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + name + ".in"));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            tree.insert(Integer.parseInt(st.nextToken()));
        }

    }


    public static void main(String[] args) throws IOException {
        BinarySearchTree bst = new BinarySearchTree();
        bst.readFileInputAlgo2("pla1ocen");
        int n = bst.tree.nodesGreaterThanX(bst.tree.root, 3);
        bst.tree.display(bst.tree.root);
        System.out.println("\n" + bst.tree.root.data);
        bst.tree.root = bst.tree.buildTree(bst.tree.root);
        System.out.println("Preorder traversal of balanced BST is :");
        bst.tree.display(bst.tree.root);

        System.out.println("\n" + bst.tree.root.data);
        System.out.println("\n Nodes bigger than 4:" + n);

    }


}

class BST {

    public Node root;

    public BST() {
        this.root = null;
    }

    void storeBSTNodes(Node root, Vector<Node> nodes) {
        // Base case
        if (root == null)
            return;

        // Store nodes in Inorder (which is sorted
        // order for BST)
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }

    Node buildTreeUtil(Vector<Node> nodes, int start,
                       int end) {
        // base case
        if (start > end)
            return null;

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        /* Using index in Inorder traversal, construct
           left and right subtress */
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);

        return node;
    }

    void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    Node buildTree(Node root) {
        // Store nodes of given BST in sorted order
        Vector<Node> nodes = new Vector<Node>();
        storeBSTNodes(root, nodes);

        // Constucts BST from nodes[]
        int n = nodes.size();
        return buildTreeUtil(nodes, 0, n - 1);
    }

    public boolean find(int n) {
        Node current = root;
        while (current != null) {
            if (current.data == n) {
                return true;
            } else if (current.data > n) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }


    public int nodesGreaterThanX(Node node, int k) {
        if (node == null) {
            return 0;
        }

        int countLeft = nodesGreaterThanX(node.left, k);
        int countRight = nodesGreaterThanX(node.right, k);

        return (node.data > k ? 1 : 0) + countLeft + countRight;
    }

    public boolean delete(int id) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (current.data != id) {
            parent = current;
            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
//if i am here that means we have found the node
//Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
//Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {
//now we have found the minimum element in the right sub tree
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
//check if successor has the right child, it cannot have left child for sure
// if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert(int id) {
        Node newNode = new Node(id);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (id < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }
}


class Node {

    Node left, right;
    int data;

    public Node(int data) {
        this.data = data;
    }
//
//        public void insert(int value) {
//            if (value <= data) {
//                if (left == null) {
//                    left = new Node(value);
//                } else left.insert(value);
//            } else {
//                if (right == null) {
//                    right = new Node(value);
//                } else right.insert(value);
//            }
//        }
//
//        public boolean contains(int value) {
//            if (value == data) {
//                return true;
//            } else if (value < data) {
//                if (left == null) {
//                    return false;
//                } else {
//                    return left.contains(value);
//                }
//            } else {
//                if (right == null) {
//                    return false;
//                } else {
//                    return right.contains(value);
//                }
//            }
//        }
//
//        public void printInOrder() {
//            if (left != null) {
//                left.printInOrder();
//            }
//            System.out.println(data);
//            if (right != null) {
//                right.printInOrder();
//            }
//        }
}
