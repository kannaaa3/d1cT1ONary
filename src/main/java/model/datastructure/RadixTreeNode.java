package model.datastructure;

public class RadixTreeNode {
    public static final int NUMBER_OF_CHILD = 2;
    protected RadixTreeNode parent;
    protected RadixTreeNode[] child;
    private boolean hasString;

    /**
     * This is the default constructor, this constructor will do nothing.
     */
    public RadixTreeNode() {

    }

    /**
     * This constructor is use when we want to initialize a real node. We will need to store the
     * parent of the node because of the algorithm.
     *
     * @param parent the parent of the node
     */
    public RadixTreeNode(RadixTreeNode parent) {
        child = new RadixTreeNode[NUMBER_OF_CHILD];
        child[0] = null;
        child[1] = null;
        hasString = false;
        this.parent = parent;
    }

    public boolean isHasString() {
        return hasString;
    }

    public void setHasString(boolean hasString) {
        this.hasString = hasString;
    }
}