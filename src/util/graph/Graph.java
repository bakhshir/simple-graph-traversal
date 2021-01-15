
package util.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Graph.
 */
public class Graph<T> {
  /** List of nodes. */
  protected ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();

  public Graph<T> copy() {
    Graph<T> graph = new Graph<T>();

    // build the nodes
    HashMap<Node,Node> freshNodes = new HashMap<Node, Node>();
    for(Node<T> node : nodes) {
      Node<T> freshNode = new Node<T>(node.getUserObject());
      freshNodes.put(node, freshNode);
      graph.addNode(freshNode);
    }

    // build the transitions
    for(Node<T> from : nodes) {
      for(Node<T> to : from.getTransitions()) {
        freshNodes.get(from).addTransition(freshNodes.get(to));
      }
    }

    return graph;
  }

  /**
   * Returns the nodes of the graph.
   */
  public ArrayList<Node<T>> getNodes() {
    return nodes;
  }

  /**
   * Adds a node to the graph.
   * (call generateNodeHulls to regenerate the hulls if needed)
   */
  public void addNode(Node<T> node){
    nodes.add(node);
  }

  /**
   * Removes a removeNode from the graph.
   * (call generateNodeHulls to regenerate the hulls if needed)
   */
  public void removeNode(Node<T> removeNode){
    // cancel all connections to this node
    for(Node<T> node : nodes) {
      node.removeTransition(removeNode);
      removeNode.removeTransition(node);
    }

    nodes.remove(removeNode);
  }

  public void removeSeparatedNodes() {
    ListIterator nodesIterator = nodes.listIterator();
    while(nodesIterator.hasNext()) {
      Node node = (Node) nodesIterator.next();
      if(node.getTransitions().size() == 0) {
        nodesIterator.remove();
      }
    }
  }
}
