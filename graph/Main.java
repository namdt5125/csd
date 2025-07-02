/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

/**
 *
 * @author Yourn
 */
public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.BFT(0);//A
        System.out.println("");
        g.DFS('A');
//     System.out.println("");
//     /*Session 1_______________________________________________________________*/
//     g.pathDFT(0, 6);//A->F
//     System.out.println("");
//     /*Session 2_______________________________________________________________*/
//     Dijkstra d = new Dijkstra();
//      d.ijk(0, 5);//A->F
//      Euler e = new Euler();
//      e.euler();
//      System.out.println("");
        Dijkstra d = new Dijkstra();
        d.ijk(0, 4);
        System.out.println("");
        System.out.println(d.getShortestDistance(0,4));
    }
}
