package cn.hutool.jackie.algorithm.design;

enum Color {
    RED, BLACK
}

class Node<T extends Comparable<T>, V> {
    T key;
    V value;
    Color color;
    Node<T, V> left, right, parent;

    Node(T key, V value) {
        this.key = key;
        this.value = value;
        color = Color.RED;
        left = right = parent = null;
    }
}

/**
 * @author rcao1
 */
public class RedBlackTree<T extends Comparable<T>, V> {
    private Node<T, V> root;

    RedBlackTree() {
        root = null;
    }

    private void leftRotate(Node<T, V> x) {
        Node<T, V> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T, V> y) {
        Node<T, V> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    private void fixInsert(Node<T, V> z) {
        while (z != root && z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node<T, V> y = z.parent.parent.right;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node<T, V> y = z.parent.parent.left;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    public void insert(T key, V value) {
        Node<T, V> node = new Node<>(key, value);
        Node<T, V> y = null;
        Node<T, V> x = root;

        while (x != null) {
            y = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key.compareTo(y.key) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        fixInsert(node);
    }

    private Node<T, V> search(Node<T, V> node, T key) {
        if (node == null || node.key.equals(key)) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        }
        return search(node.right, key);
    }

    public Node<T, V> search(T key) {
        return search(root, key);
    }

    private void rangeSearchUtil(Node<T, V> node, T low, T high) {
        if (node == null) {
            return;
        }

        if (low.compareTo(node.key) < 0) {
            rangeSearchUtil(node.left, low, high);
        }

        if (low.compareTo(node.key) <= 0 && high.compareTo(node.key) >= 0) {
            System.out.print(node.key + "#" + node.value + " ");
        }

        if (high.compareTo(node.key) > 0) {
            rangeSearchUtil(node.right, low, high);
        }
    }

    public void rangeSearch(T low, T high) {
        rangeSearchUtil(root, low, high);
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.insert(10, "ten");
        tree.insert(5, "five");
        tree.insert(15, "fifteen");
        tree.insert(3, "three");
        tree.insert(7, "seven");
        tree.insert(12, "twelve");
        tree.insert(18, "eighteen");

        Node<Integer, String> found = tree.search(7);
        if (found != null) {
            System.out.println("Found: " + found.key + "#" + found.value);
        } else {
            System.out.println("Not Found");
        }

        System.out.println("Range search between 7 and 15:");
        tree.rangeSearch(7, 15);
    }
}

