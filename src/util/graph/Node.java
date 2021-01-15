
package util.graph;

import util.BigFraction;

import java.util.ArrayList;

/**
 * Graph node.
 */
public class Node<T> {
  /** Outgoing transitions. */
  private ArrayList<Node<T>> forwardNodes = new ArrayList<Node<T>>();
  /** Incoming transitions. */
  private ArrayList<Node<T>> backwardNodes = new ArrayList<Node<T>>();

  /** User object attached to this node. */
  private T userObject;

  public boolean mark = false;

  public BigFraction weight = new BigFraction(0,1);

  /**
   * Creates an empty node.
   */
  public Node(){
  }

  public Node(T userObject){
    this.userObject = userObject;
  }

  /**
   * Adds a node to the outgoing transitions.
   */
  public void addTransition(Node<T> node){
    forwardNodes.add(node);
    node.backwardNodes.add(this);
  }

  public void removeTransition(Node<T> node){
    forwardNodes.remove(node);
    node.backwardNodes.remove(this);
  }

  public ArrayList<Node<T>> getTransitions() {
    return forwardNodes;
  }

  /**
   * Returns the user object attached to this node.
   */
  public T getUserObject() {
    return userObject;
  }

  public void setUserObject(T userObject) {
    this.userObject = userObject;
  }

  public boolean equals(Object obj) {
    Node node = (Node) obj;
    if(userObject == null || node.userObject == null) return this == node;
    return userObject.equals(node.userObject);
  }
}
