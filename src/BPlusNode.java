


import java.util.ArrayList;
/*Abstract Class to implement both Range search and normal search*/

public abstract class BPlusNode {

    protected int m;               
    protected boolean isPair;   
    protected int numPairs;               


    public BPlusNode() {

    }

 
    public BPlusNode searchSubNode(KeyValPair pair) {
        return null;
    }

    public BPlusNode searchSubNode(double key) {
        return null;
    }


    public void search(double key, ArrayList<String> vals) {

    }

   
    public void search(double key1, double key2, ArrayList<KeyValPair> res) {

    }

    public void insert(KeyValPair pair) {

    }


    public BPlusSearch split() {
        return null;
    }


    public void mergeWith(BPlusSearch node) {

    }

    /** is this node a data node ? */
    public boolean isPair() {
        return isPair;
    }

    /** is this node overfull? */
    public boolean isOverfull() {
        return this.numPairs >= m;
    }

}
