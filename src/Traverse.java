import util.graph.Graph;
import util.graph.Node;
import util.Pair;
import util.BigFraction;

import java.util.HashMap;

/**
 * Traversing graphs.
 */
public class Traverse {
  public static void main(String args[]) {
    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);
    int hops = Integer.parseInt(args[2]);

    Graph<Pair<Integer,Integer>> graph = new Graph<Pair<Integer, Integer>>();
    HashMap<Pair<Integer,Integer>,Node<Pair<Integer,Integer>>> nodeMap = new HashMap<Pair<Integer,Integer>,Node<Pair<Integer,Integer>>>();
    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(x,y);
        Node<Pair<Integer,Integer>> node = new Node<Pair<Integer,Integer>>(position);
        graph.addNode(node);
        nodeMap.put(position, node);
      }
    }
    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(x,y);
        Node<Pair<Integer,Integer>> node = nodeMap.get(position);

        if(x < width-1)  {
          Pair<Integer,Integer> position2 = new Pair<Integer,Integer>(x+1,y);
          Node<Pair<Integer,Integer>> node2 = nodeMap.get(position2);
          node.addTransition(node2);
          node2.addTransition(node);
        }

        if(y < height-1)  {
          Pair<Integer,Integer> position2 = new Pair<Integer,Integer>(x,y+1);
          Node<Pair<Integer,Integer>> node2 = nodeMap.get(position2);
          node.addTransition(node2);
          node2.addTransition(node);
        }
      }
    }

    traverse(nodeMap.get(new Pair<Integer,Integer>(width/2,height/2)), null, new BigFraction(1,1), hops);

    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(x,y);
        System.out.print(nodeMap.get(position).weight);
        System.out.print("\t");
      }
      System.out.println("");
    }
  }

  public static void traverse(Node<?> current, Node<?> from, BigFraction weight, int hops) {
    if(current.mark && hops > 0) return;

    if(hops == 0) {
      current.weight = current.weight.add(weight);
      return;
    }

    current.mark = true;

    int denominator = current.getTransitions().size();
    if(from != null && hops != 1) denominator--;
    BigFraction multiply = new BigFraction(1, denominator);
    BigFraction successorWeight = weight.multiply(multiply);
    
    for(Node successor : current.getTransitions()) {
      traverse(successor, current, successorWeight, hops-1);
    }

    current.mark = false;
  }
}
