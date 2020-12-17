import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.*;

import static org.graphstream.algorithm.Dijkstra.Element.EDGE;


public class Dijkstra {
    HashMap<Node, Double> queue = new HashMap<>();

        public static void main(String[] args){
            System.setProperty("org.graphstream.ui", "swing");
            Dijkstra d  = new Dijkstra();
            Graph g = d.graph_Aleatoire(100);
            Node source = g.getNode(0);
            //g.display();
            System.out.println("Nombre de noeuds : " + g.getNodeCount());
            System.out.println("Nombre de arretes : " + g.getEdgeCount());
            System.out.println("Dijkstra  Naive " );
            d.dijkstraNaive(g, source);
            System.out.println("Dijkstra GraphStream");
            d.dijkstraGraphStream(g);

    }

    //Methode permettant de générer un graphe aleatoire
    public Graph graph_Aleatoire(int taille) {
        Graph graph = new SingleGraph("Graphe aléatoire");
        Generator g = new RandomGenerator(15, false, false, "", "cap");
        g.addSink(graph);
        g.begin();
        for (int i = 0; i < taille; i++)
            g.nextEvents();
        g.end();
        return graph;
    }

    private void init(Graph graph, Node source) {
        for (Node n : graph) {
            n.setAttribute("dist", n.getId().equals(source.getId()) ? 0 : Double.POSITIVE_INFINITY);
            n.setAttribute("parent", new Stack<Node>());
        }
    }

    public void dijkstraNaive(Graph graph, Node source) {
        long debut, fin;
        debut = System.currentTimeMillis();
        init(graph, source);
        queue.put(source, (Double) source.getAttribute("dist"));
        while (queue.size() != 0) {
            Node courant = priorite(queue.keySet());
            Iterator<Edge> edgeIterator = courant.iterator();
            queue.remove(courant);
            while (edgeIterator.hasNext()) {
                Edge currEdge = edgeIterator.next();
                Node opNode = currEdge.getTargetNode();
                if (!queue.containsKey(opNode)) {
                    Double currNodeDist = (Double) courant.getAttribute("dist");
                    Double edgeWeight = (Double) currEdge.getAttribute("cap");
                    Double sum = currNodeDist + edgeWeight;
                    Double opNodeDistance = (Double) opNode.getAttribute("dist");
                    if (opNodeDistance > sum) {
                        opNode.setAttribute("dist", sum);
                        opNode.setAttribute("parent", courant);
                        queue.put(opNode, (Double) opNode.getAttribute("dist"));
                    }
                }
            }
        }
        fin = System.currentTimeMillis();
        long temps = fin - debut;
        System.out.println("Le temps d'éxécution de la methode Dijkstra du cours est : " + temps + " ms");
    }

    private Node priorite(Set<Node> nodes) {
        Node plusPetite = null;
        Double plusPetiteDist = Double.MAX_VALUE;
        for (Node node : nodes) {
            Double distance = (Double) node.getAttribute("dist");
            if (distance < plusPetiteDist) {
                plusPetiteDist = distance;
                plusPetite = node;
            }
        }
        return plusPetite;
    }

    public void dijkstraGraphStream(Graph graph) {
        long debut = System.currentTimeMillis();
        org.graphstream.algorithm.Dijkstra dijkstra = new org.graphstream.algorithm.Dijkstra(EDGE, null, null);
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode("0"));
        dijkstra.compute();
        long fin = System.currentTimeMillis();
        long temps = fin - debut;
        System.out.println("Le temps d'éxécution de la méthode Dijkstra de GraphStream est : " + temps + " ms");
    }

}

