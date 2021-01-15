
package util;

/**
 * Pair.
 */
public class Pair<S,T> {
  public S first = null;
  public T second = null;

  public Pair(S first, T second) {
    this.first = first;
    this.second = second;
  }

  public int hashCode() {
    return first.hashCode() + second.hashCode();
  }

  public boolean equals(Object obj) {
    Pair<S,T> pair = (Pair<S,T>) obj;
    return first.equals(pair.first) && second.equals(pair.second);
  }
}
