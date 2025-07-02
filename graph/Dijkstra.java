/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

/**
 *
 * @author Yourn
 */


public class Dijkstra {
    
    int[][] a;
    int n;
    char[] vertext = "ABCDEFGHI".toCharArray();
    int INF = 99;

    public Dijkstra() {
        int[][] b = {
            // A   B   C   D   E   F
            {0, 7, 9, INF, INF, 14},  // A
            {7, 0, 10, 15, INF, INF}, // B
            {9, 10, 0, 11, INF, 2},   // C
            {INF, 15, 11, 0, 6, INF}, // D
            {INF, INF, INF, 6, 0, 9}, // E
            {14, INF, 2, INF, 9, 0},  // F
        };
        a = b;
        n = a.length;
    }

    public void ijk(int u, int v) {
        boolean[] c = new boolean[n];
        int[] s = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = u;
            d[i] = a[u][i];
        }

        int curr = u;
        while (curr != v) {
            int k = -1, min = INF;
            for (int i = 0; i < n; i++) {
                if (!c[i] && d[i] < min) {
                    min = d[i];
                    k = i;
                }
            }
            if (min == INF) {
                System.out.println("Shortest path from " + vertext[u] + " to " + vertext[v] + " not found.");
                return;
            }
            curr = k;
            c[k] = true;

            for (int i = 0; i < n; i++) {
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    s[i] = k;
                }
            }
        }

        System.out.println("Shortest distance from " + vertext[u] + " to " + vertext[v] + " is: " + d[v]);

        int[] h = new int[n]; // path
        int hn = 0;
        h[hn] = v;
        int[] w = new int[n]; // edge weights
        int wn = 0;
        int x, y = v;

        while (u != v) {
            v = s[v];
            h[++hn] = v;
        }

        for (int i = hn; i >= 0; i--) {
            x = y;
            y = h[i];
            w[wn++] = a[x][y];
        }

        // 1. In đường đi với trọng số từng cạnh
        System.out.print("Path with edge weights: ");
        int k = 1;
        System.out.print(vertext[h[hn]]);
        for (int i = hn - 1; i >= 0; i--) {
            System.out.print("->" + vertext[h[i]] + "(" + w[k++] + ")");
        }
        System.out.println();

        // 2. In đường đi và độ dài ngắn nhất từ u đến mỗi đỉnh trên đường đi
        System.out.print("Path with cumulative distances: ");
        System.out.print(vertext[h[hn]]);
        for (int i = hn - 1; i >= 0; i--) {
            System.out.print("->" + vertext[h[i]] + "(" + d[h[i]] + ")");
        }
        System.out.println();
    }

    public int getShortestDistance(int u, int v) {
        boolean[] c = new boolean[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = a[u][i];
        }

        c[u] = true;
        while (true) {
            int k = -1, min = INF;
            for (int i = 0; i < n; i++) {
                if (!c[i] && d[i] < min) {
                    min = d[i];
                    k = i;
                }
            }
            if (k == -1 || k == v) break;
            c[k] = true;
            for (int i = 0; i < n; i++) {
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                }
            }
        }
        return d[v] == INF ? -1 : d[v];
    }
}

