package model.datastructure;

import java.util.ArrayList;
import java.util.List;

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
    public void add(int[] word) {
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
    public List<Integer[]> findTenBestMatchWord(int[] prefix, int numberOfNeededWord) {
        List<Integer[]> ans = new ArrayList<>();
        RadixTreeNode currentNode = root;
        for (int i = 0; i < prefix.length; i++) {
            // Case the prefix does not exist in the tree
            if (currentNode.child[prefix[i]] == null) {
                return ans;
            }
            currentNode = currentNode.child[prefix[i]];
        }


        return ans;
    }
}