/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

/**
 *
 * @author Yourn
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class MyBSTree {

    Node root;

    public MyBSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void visit(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.info + "  ");
    }

    public Node search(Node p, int key) {
        if (p == null) {
            return null;
        }
        if (p.info == key) {
            return p;
        } else if (p.info > key) {
            return search(p.left, key);
        } else {
            return search(p.right, key);
        }
    }

    public void insert(int x) {
        Node p = new Node(x);
        Node f = null, q = root;
        while (q != null) {
            if (q.info == x) {
                System.out.println("Key cannot be duplicated...");
                return;
            }
            if (q.info < x) {
                f = q;
                q = q.right;
            } else {
                f = q;
                q = q.left;
            }
        }
        if (f == null) {
            root = p;
        } else if (p.info > f.info) {
            f.right = p;
        } else {
            f.left = p;
        }
    }

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    int count = 0;
//    Node preOrder3(Node p) throws Exception {
//    if (p == null) return null;
//
//    // Đếm số lượng node đã duyệt trong pre-order
//    count2++;
//    if (count2 == 3) {
//        return p;  // Trả về node thứ 3 trong pre-order
//    }
//
//    // Tiến hành duyệt pre-order
//    Node left = preOrder3(p.left);
//    if (left != null) return left;  // Trả về nếu đã tìm thấy
//
//    return preOrder3(p.right);  // Tiếp tục duyệt qua nhánh phải
//}
    //----------------------------------------------------------
//    Node postOrder2(Node p, RandomAccessFile f) throws Exception {
//    if (p == null) return null;
//    Node q = postOrder2(p.left, f);  
//    if (q != null) return q;           
//    q = postOrder2(p.right, f);
//    if (q != null) return q;
//    if (p.left != null) {
//        count++;
//        if (count == 2) return p;       
//    }
//    return null;
//}
//     int count = 0;
//Node preOrder2(Node p, RandomAccessFile f) throws Exception {
//    if (p == null) return null;
//    
//    if (p.left != null) {
//        count++;
//        if (count == 2) return p;
//    }
//    
//    Node q = preOrder2(p.left, f);
//    if (q != null) return q;
//    
//    q = preOrder2(p.right, f);
//    if (q != null) return q;
//    
//    return null;
//}
//int count = 0;
//Node inOrder2(Node p, RandomAccessFile f) throws Exception {
//    if (p == null) return null;
//    
//    Node q = inOrder2(p.left, f);
//    if (q != null) return q;
//    
//    if (p.left != null) {
//        count++;
//        if (count == 2) return p;
//    }
//    
//    q = inOrder2(p.right, f);
//    if (q != null) return q;
//    
//    return null;
//}

    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public void breadth(Node p) {
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            visit(q);
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
        }
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        } else {
            int lDepth = height(p.left);
            int rDepth = height(p.right);
            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
    }

    Node findNodeAtPosition(Node p, int position) {
        if (p == null) {
            return null;
        }
        // Perform pre-order traversal and track the position
        Node foundNode = findNodeAtPositionHelperPre(p, position, new int[]{0});
        return foundNode;
    }

    Node findNodeAtPositionHelperPre(Node p, int position, int[] counter) {
        if (p == null) {
            return null;
        }
        counter[0]++;
        if (counter[0] == position) {
            return p;
        }

        Node left = findNodeAtPositionHelperPre(p.left, position, counter);
        if (left != null) {
            return left;
        }

        return findNodeAtPositionHelperPre(p.right, position, counter);
    }

    Node findNodeAtPositionHelperInOrder(Node p, int position, int[] counter) {
        if (p == null) {
            return null;
        }

        // 1. Duyệt trái trước
        Node left = findNodeAtPositionHelperInOrder(p.left, position, counter);
        if (left != null) {
            return left;
        }

        // 2. Xử lý node hiện tại
        counter[0]++;
        if (counter[0] == position) {
            return p;
        }

        // 3. Duyệt phải
        return findNodeAtPositionHelperInOrder(p.right, position, counter);
    }

    Node findNodeAtPositionHelperPost(Node p, int position, int[] counter) {
        if (p == null) {
            return null;
        }

        // 1. Duyệt trái trước
        Node left = findNodeAtPositionHelperPost(p.left, position, counter);
        if (left != null) {
            return left;
        }

        // 2. Duyệt phải
        Node right = findNodeAtPositionHelperPost(p.right, position, counter);
        if (right != null) {
            return right;
        }

        // 3. Xử lý node hiện tại
        counter[0]++;
        if (counter[0] == position) {
            return p;
        }

        return null;
    }



    int calculateSubtreeSize(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + calculateSubtreeSize(p.left) + calculateSubtreeSize(p.right);
    }

    /*Part 2___________________________________________________________________*/
    //delete a node on BST
    public void deleteByCopy(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }

        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info > p.info) {
                q = q.left;
            } else {
                q = q.right;
            }
        }

        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } else if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } else if (p.left != null && p.right != null) {

            q = p.left;
            f = null;
            while (q.right != null) {
                f = q;
                q = q.right;
            }
            p.info = q.info;

            if (f == null) {
                p.left = q.left;
            } else {
                f.right = q.left;
            }
        }
    }
    

    public void deleteByMerging(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }

        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info > p.info) {
                q = q.left;
            } else {
                q = q.right;
            }
        }

        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } else if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } else if (p.left != null && p.right != null) {

            q = p.left;
            Node t = null;
            while (q.right != null) {
                t = q;
                q = q.right;
            }
            Node rp = p.right;
            q.right = rp;
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        }
    }

    public void buildArray(ArrayList a, Node p) {
        if (p == null) {
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

    public void balance(ArrayList a, int f, int l) {
        if (f > l) {
            return;
        }
        int m = (f + l) / 2;
        Node p = (Node) a.get(m);
        insert(p.info);
        balance(a, f, m - 1);
        balance(a, m + 1, l);
    }

    public void balance(Node p) {
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int l = a.size(), f = 0;

        MyBSTree t = new MyBSTree();
        t.balance(a, f, l - 1);
        root = t.root;
    }

    /*Part 3___________________________________________________________________*/
    public Node rotateLeft(Node p) {
        if (p.right == null) {
            return p;
        }
        Node p1 = p.right;
        p.right = p1.left;
        p1.left = p;
        return p1;
    }

    public Node rotateRight(Node p) {
        if (p.left == null) {
            return p;
        }
        Node p1 = p.left;
        p.left = p1.right;
        p1.right = p;
        return p1;
    }

    // Tìm cha của node p
    public Node searchParent(Node a) {
        if (a == null) {
            return null;
        }
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info > a.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }

    public Node rotateLeftPro(Node p) {
        if (p == null || p.right == null) {
            return p;
        }

        Node parent = searchParent(p);
        Node p1 = p.right;
        p.right = p1.left;
        p1.left = p;

        if (parent == null) {
            root = p1;
        } else if (parent.left == p) {
            parent.left = p1;
        } else if (parent.right == p) {
            parent.right = p1;
        }

        return p1;
    }

    public Node rotateRightPro(Node p) {
        if (p == null || p.left == null) {
            return p;
        }

        Node parent = searchParent(p);
        Node p1 = p.left;
        p.left = p1.right;
        p1.right = p;

        if (parent == null) {
            root = p1;
        } else if (parent.left == p) {
            parent.left = p1;
        } else if (parent.right == p) {
            parent.right = p1;
        }

        return p1;
    }
    // Tìm node có giá trị nhỏ nhất từ node p
public Node findMin(Node p) {
    if (p == null) return null;
    while (p.left != null) {
        p = p.left;
    }
    return p;
}

// Tìm node có giá trị lớn nhất từ node p
public Node findMax(Node p) {
    if (p == null) return null;
    while (p.right != null) {
        p = p.right;
    }
    return p;
}

}

class testBST {

    public static void main(String[] args) {
        MyBSTree myTree = new MyBSTree();
        myTree.insert(5);
        myTree.insert(3);
        myTree.insert(7);
        myTree.insert(1);
        myTree.insert(2);
        myTree.insert(0);
        myTree.insert(4);
        myTree.insert(10);
        myTree.insert(6);
        myTree.insert(9);
        myTree.breadth(myTree.root);
        System.out.println("");
        System.out.println(myTree.findMax(myTree.root).info);
//        myTree.deleteByMerging(5);
//        System.out.println("");
//        myTree.breadth(myTree.root);
//        myTree.search(myTree.root, 5);
//        System.out.println("");
//        System.out.println(myTree.calculateSubtreeSize(myTree.root));
//        System.out.println(myTree.height(myTree.root));
        
    }
}























