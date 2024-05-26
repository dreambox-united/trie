public class Trie {

  static final int ALPHABET_SIZE = 26;

  static class TrieNode {
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    boolean isEndOfWord;

    TrieNode() {
      isEndOfWord = false;
      for (int i = 0; i < ALPHABET_SIZE; i++)
        children[i] = null;
    }
  };

  static TrieNode root;

  static void insert(String key) {
    int level;
    int length = key.length();
    int index;

    TrieNode currentNode = root;

    for (level = 0; level < length; level++) {
      index = key.charAt(level) - 'a';
      if (currentNode.children[index] == null)
        currentNode.children[index] = new TrieNode();

      currentNode = currentNode.children[index];
    }

    currentNode.isEndOfWord = true;
  }

  static boolean search(String key) {
    int level;
    int length = key.length();
    int index;
    TrieNode currentNode = root;

    for (level = 0; level < length; level++) {
      index = key.charAt(level) - 'a';

      if (currentNode.children[index] == null)
        return false;

      currentNode = currentNode.children[index];
    }

    return (currentNode != null && currentNode.isEndOfWord);
  }

  static TrieNode remove(TrieNode root, String key, int depth) {

    if (root == null)
      return null;

    if (depth == key.length()) {

      if (root.isEndOfWord)
        root.isEndOfWord = false;

      if (isEmpty(root)) {

        root = null;
      }

      return root;
    }

    int index = key.charAt(depth) - 'a'; // s - 'a' // 'a' - 'a' // 'w' - a 
    root.children[index] =   remove(root.children[index], key, depth + 1);

    if (isEmpty(root) && root.isEndOfWord == false) {
      root = null;
    }

    return root;
  }

  static boolean isEmpty(TrieNode root) {
    for (int i = 0; i < ALPHABET_SIZE; i++)
      if (root.children[i] != null)
        return false;
    return true;

  }

  public static void main(String args[]) {

    String keys[] = {
      "an",
      "and",
      "ant",
      "bad",
      "bat",
      "zoo"
    };

    String output[] = {
      "NO",
      "YES"
    };

    root = new TrieNode();

    int i;
    for (i = 0; i < keys.length; i++)
      insert(keys[i]);

    root = remove(root, "zoo", 0);

    if (search("zoo") == true)
      System.out.println("zoo --- " + output[1]);
    else System.out.println("zoo --- " + output[0]);

  }
}
