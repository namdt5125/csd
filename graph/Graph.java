/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

/**
 *
 * @author Yourn
 */
import java.util.*;

public class Graph {

    int[][] a;//adjcency matrix
    int n;//total vertex
    char[] vertex = "ABCDEFGHIJKLMN".toCharArray();
    

    public Graph() {
        int[][] b = {
            //A  B  C  D  E  F  G  H  I
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //A
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //B 
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, //C
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, //D
            {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //E
            {0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1}, //F
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1}, //G
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0}, //H
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},//I
        };
        a = b;
        n = a.length;
    }

    //visit vertex i
    public void visit(int i) {
        System.out.print(vertex[i] + "  ");
    }

    //Breadth first traversal
    public void BFT(int u, boolean[] c) {
        MyQueue mq = new MyQueue();
        mq.enqueue(u);
        c[u] = true;
        while (!mq.isEmpty()) {
            int j = (int) mq.dequeue();
            visit(j);
            for (int i = 0; i < n; i++) {
                if (!c[i] && a[j][i] > 0) {
                    mq.enqueue(i);
                    c[i] = true;
                }
            }
        }
    }

    //Breath first traversal
    public void BFT(int u) {
        boolean[] c = new boolean[n];
        BFT(u, c);
        for (int i = 0; i < n; i++) {
            if (!c[i]) {
                BFT(i, c);
            }
        }
    }

    //Depth first traversal
    public void DFT(int i, boolean[] c) {
        c[i] = true;
        visit(i);
        for (int j = 0; j < n; j++) {
            if (!c[j] && a[i][j] > 0) {
                DFT(j, c);
            }
        }
    }

    public void DFT(int i) {
        boolean[] c = new boolean[n];
        DFT(i, c);
        for (int j = 0; j < n; j++) {
            if (!c[j]) {
                DFT(j, c);
            }
        }
    }
    /*Session 1_______________________________________________________________*/
    //connectivity
    int[] tp;
    int con;

    public void conDFT(int i, boolean[] c) {
        c[i] = true;
        tp[i] = con;
        for (int j = 0; j < n; j++) {
            if (!c[j] && a[i][j] > 0) {
                conDFT(j, c);
            }
        }
    }

    public void conDFT() {
        con = 0;
        tp = new int[n];
        boolean[] c = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!c[i]) {
                con++;
                conDFT(i, c);
            }
        }
        //output connectivity
        System.out.println("Connectivity: " + con);
        for (int i = 1; i <= con; i++) {
            System.out.println("Connectivity = " + i);
            for (int j = 0; j < n; j++) {
                if (tp[j] == i) {
                    visit(j);
                }
            }
            System.out.println("");
        }
    }

    //a path between u and v
    int u, v;
    int[] t;
    boolean found = false;

    public void pathDFT(int i, boolean[] c) {
        c[i] = true;
        for (int j = 0; j < n; j++) {
            if (!c[j] && a[i][j] > 0) {
                t[j] = i;
                if (j == v) {
                    found = true;
                    return;
                }
                pathDFT(j, c);
            }
        }
    }

    public void pathDFT(int u, int v) {
        boolean[] c = new boolean[n];
        t = new int[n];
        this.u = u;
        this.v = v;
        pathDFT(u, c);
        if (found == false) {
            System.out.println("not found a path from " + vertex[u] + " to " + vertex[v]);
        } else {
            System.out.println("a path from " + vertex[u] + " to " + vertex[v]);
            int[] h = new int[n];
            int hn = 0;
            h[0] = v;
            while (u != v) {
                v = t[v];
                h[++hn] = v;
            }
            for (int i = hn; i >= 0; i--) {
                visit(h[i]);
                if (i > 0) {
                    System.out.print("->");
                }
            }
        }
    }

    /*Session 2_______________________________________________________________*/
    // Tính bậc của các đỉnh
    public int degreeOfVertex(int index) {
        int degree = 0;
        for (int j = 0; j < n; j++) {
            if (a[index][j] > 0) {
                degree++;
            }
        }
        return degree;
    }
    
    int toInt(char c){
        return c-65;
    }

    void DFS(char x){
        boolean[] visited = new boolean[n];
        for(int i=0; i < visited.length; i++){
            visited[i] = false;
        }
        DFS(toInt(x), visited);
    }
    void DFS(int p, boolean[] visited){
        visited[p] = true;
        visit(p);
        for(int i = 0; i< n; i++){
            if(!visited[i] && a[i][p] != 0){
                DFS(i, visited);
            }
        }
    }
    

}



//void dijkstra(int fro, int to, RandomAccessFile f) throws Exception {
//        int INF = 999;
//        boolean[] S = new boolean[n];
//        int[] d = new int[n]; //luu gia tri duong di ngan nhat tai dinh do
//        int[] p = new int[n]; //luu gia tri dinh gan no nhat
//
//        for (int i = 0; i < n; i++) {
//            S[i] = false;
//            d[i] = a[fro][i];
//            p[i] = fro;
//        }
//
//        ArrayList<Integer> ss = new ArrayList<>(); //cac dinh duoc lay
//        S[fro] = true;
//        ss.add(fro);
//
//        int k, t;
//        while (true) {
//            k = -1;
//            t = INF;
//            for (int i = 0; i < n; i++) {
//                if (S[i] == true) {
//                    continue;
//                }
//                if (d[i] < t) {
//                    k = i;
//                    t = d[i];
//                }
//            }
//            if (k == -1) {
//                return;
//            }
//            S[k] = true;
//            ss.add(k);
//            if (k == to) {
//                break;
//            }
//            for (int i = 0; i < n; i++) {
//                if (S[i] == true) {
//                    continue;
//                }
//                if (d[i] > d[k] + a[k][i]) {
//                    d[i] = d[k] + a[k][i];
//                    p[i] = k;
//                }
//            }
//        }
//        Stack s = new Stack();
//        int x = to;
//        while (true) {
//            s.push(x);
//            if (x == fro) {
//                break;
//            }
//            x = p[x];
//        }
//
//        ArrayList<Integer> pp = new ArrayList<>();
//        while (!s.isEmpty()) {
//            x = s.pop();
//            pp.add(x);
//        }
//        //pp la mang chua cac dinh (theo so) la ket qua sau khi thuc hien thuat toan dijkstra
//        //ss la mang cac dinh (theo so) duoc chon trong qua trinh thuc hien thuat toan dijkstra
//        //muon doi sang dang ten (A, B,...) thi phai viet V[pp.get(i)] , v[ss.get(i)]
//        //d la mang chua label cua cac dinh d[pp.get(i)], d[ss.get(i)], shortest distance : d[ss.get[ss.size()-1]]
//        for (int i = 0; i < pp.size(); i++) {
//            if (i == 2 || i == 3) {
//                f.writeBytes(v[pp.get(i)] + "(" + d[pp.get(i)] + ") ");
//            } else {
//                f.writeBytes(v[pp.get(i)] + " ");
//            }
//        }
//        f.writeBytes("\r\n");
//        for (int i = 0; i < 6; i++) {
//            f.writeBytes(v[ss.get(i)] + " ");
//        }
//        f.writeBytes("\r\n");
//        for (int i = 0; i < ss.size(); i++) {
//            if (i == ss.size() - 1) {
//                f.writeBytes(d[ss.get(i)] + "");
//            }
//        }
//
//    }