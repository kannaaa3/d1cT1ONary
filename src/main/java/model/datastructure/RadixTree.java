package model.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RadixTree {
    private final RadixTreeNode root;

    /**
     * Constructor for the radix tree, creating a new root.
     */
    public RadixTree() {
        root = new RadixTreeNode(null);
    }

    /**
     * This function is used to add a new word to the tree. Please note that the added word need to
     * be in binary representation.
     *
     * @param word the added word in binary representation
     */
    public void add(Integer[] word) {
        RadixTreeNode currentNode = root;
        for (int i = 0; i < word.length; i++) {
            if (currentNode.child[word[i]] == null) {
                currentNode.child[word[i]] = new RadixTreeNode(currentNode);
            }
            currentNode = currentNode.child[i];
        }
        currentNode.setHasString(true);
    }

    /**
     * This function is used to check if the word is present in the tree.
     *
     * @param word the word we want to check in binary representation
     * @return true if the word in the tree
     */
    public boolean contain(int[] word) {
        RadixTreeNode currentNode = root;
        for (int i = 0; i < word.length; i++) {
            if (currentNode.child[word[i]] == null) {
                return false;
            }
            currentNode = currentNode.child[i];
        }
        return currentNode.isHasString();
    }

    /**
     * This function will return best match word that match the prefix in lexico order.
     *
     * @param prefix the prefix we want to match
     * @param numberOfNeededWord the number of needed word
     * @return numberOfNeededWord word that match the prefix in the ascending lexico order
     */
    public List<Integer[]> findBestMatchWord(Integer[] prefix, int numberOfNeededWord) {
        List<Integer[]> ans = new ArrayList<>();
        RadixTreeNode currentNode = root;
        for (int j : prefix) {
            // Case the prefix does not exist in the tree
            if (currentNode.child[j] == null) {
                return ans;
            }
            currentNode = currentNode.child[j];
        }

        Queue<RadixTreeNode> queue = new ArrayDeque<>();
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            RadixTreeNode node = queue.poll();
            if (node.isHasString()) {
                List<Integer> currentString = new ArrayList<>();
                RadixTreeNode thisNode = node;
                while (thisNode.parent != null) {
                    // we are comparing address
                    if (thisNode.parent.child[0] == thisNode) {
                        currentString.add(0);
                    } else {
                        currentString.add(1);
                    }
                    thisNode = thisNode.parent;
                }
                Integer[] stringData = new Integer[currentString.size()];
                for (int i = 0; i < stringData.length; i++) {
                    stringData[i] = currentString.get(stringData.length - i - 1);
                }
                ans.add(stringData);

                if (ans.size() == numberOfNeededWord) {
                    return ans;
                }
            }
        }

        return ans;
    }
}