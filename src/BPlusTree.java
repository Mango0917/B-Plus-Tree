

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/* This class is used to implement the main functions of insert, search(key) and search(key1,key2) */

public class BPlusTree {

    private BPlusNode root;  
    private int m;     

   
    public BPlusTree(int m) {
    	if (m <= 2)
			throw new IllegalArgumentException("Illegal branching factor: "+ m);
        this.m = m;
    }

   
    public void insert(double key, String val) {
        KeyValPair pair = new KeyValPair(key, val);

      
        if (root != null) insert(root, pair);
        else root = new BPlusRSearch(pair, m);
        if (root.isOverfull()) { 
        	
        	BPlusSearch t = root.split();
            t.addFirst(root);
            root = t;
        }
    }

    private void insert(BPlusNode node, KeyValPair pair) {

     
        if (!node.isPair()) {
        	BPlusNode nextNode = node.searchSubNode(pair);
            insert(nextNode, pair);
    
            if (nextNode.isOverfull()) {
            	BPlusSearch newNode = nextNode.split();
                node.mergeWith(newNode);
            }
        }
        else {
    
            node.insert(pair);
        }

    }

    public ArrayList<String> search(double key) {

        ArrayList<String> vals = new ArrayList<>();
        if (root == null) return vals; 

   
        BPlusNode n = root;
        while (!n.isPair()) {
            n = n.searchSubNode(key);
        }

   
        n.search(key, vals);
        return vals;

    }

    public ArrayList<KeyValPair> search(double key1, double key2) {

        ArrayList<KeyValPair> pairs = new ArrayList<>();
        if (root == null) return pairs;

        BPlusNode n = root;
        while (!n.isPair()) {
            n = n.searchSubNode(key2);
        }
        
        n.search(key1, key2, pairs);
        return pairs;
    }

 

}
