import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class Algorithm {

    static String path = "src/main/resources/in/";
    int noPosters = 0;
    LinkedList<Integer> openPosters = new LinkedList<>();

    public static void main(String[] args) {
        try {
            Algorithm a = new Algorithm();
            long startTime = System.currentTimeMillis();
            a.readFileInputAlgo2("pla10a");
            long startTime2 = System.currentTimeMillis();
            a.noPosters += a.openPosters.stream().distinct().count();
            long elapsedTime2 = System.currentTimeMillis() - startTime2;
            long elapsedTime = System.currentTimeMillis() - startTime;
            print("Total execution time algo2: " + elapsedTime + " ms" + " Posters: " + a.noPosters);

            print("Time of distinc values: " + elapsedTime2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void counter(int current) {

        while (openPosters.size() > 0 && openPosters.getFirst() > current) {
            noPosters += openPosters.stream().filter(i -> i > current).distinct().count();
            openPosters.removeIf(integer -> integer > current);
        }
        if (openPosters.size() == 0 || openPosters.getLast() < current) {
            openPosters.push(current);
        }
    }

    public static int countPosters(LinkedList<Integer> buildings) {
        long startTime = System.currentTimeMillis();
        LinkedList<Integer> city = new LinkedList<>();
        int current = 0;
        int noPosters = 0;
        LinkedList<Integer> openPosters = new LinkedList<>();
        city.addAll(buildings);


        for (int i = 0; i < city.size(); ++i) {
            current = city.get(i);
            while (openPosters.size() > 0 && openPosters.getFirst() > current) {
                noPosters += openPosters.stream().filter(isHigherThan(current)).distinct().count();
                openPosters.removeIf(isHigherThan(current));
            }
            if (openPosters.size() == 0 || openPosters.getLast() < current) {
                openPosters.push(current);
            }
        }
        noPosters += openPosters.stream().distinct().count();

        long elapsedTime = System.currentTimeMillis() - startTime;

        print("Total execution time: " + elapsedTime + " ms");
        return noPosters;
    }


    private static Predicate<Integer> isHigherThan(int high) {
        return b -> b > high;
    }

    private static void print(String message) {
        System.out.println(message);
    }

    public static int readFileInputAlgo(String name) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + name + ".in"));
        int n = Integer.parseInt(in.readLine());
        LinkedList<Integer> ints = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            ints.add(Integer.valueOf(st.nextToken()));

        }
        int result = Algorithm.countPosters(ints);
        return result;
    }

    public void readFileInputAlgo2(String name) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + name + ".in"));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            counter(Integer.valueOf(st.nextToken()));
        }
    }


    class BST {

        public Node root;

        public BST() {
            this.root = null;
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
}
