

import java.util.ArrayList;
import java.util.Collections;
/* Class to implement searching of the Value given the Key */
public class BPlusSearch extends BPlusNode {

    private ArrayList<Double> keys;     
    private ArrayList<BPlusNode> subnode;   

  
    public BPlusSearch(ArrayList<Double> keys, ArrayList<BPlusNode> subnode, int m) {
        this.keys = keys;
        this.subnode = subnode;
        this.m = m;
        numPairs = keys.size();
    }

   
    public BPlusSearch( double key, BPlusNode node, int m) {
        
        keys = new ArrayList<>();
        keys.add(key);
        subnode = new ArrayList<>();
        subnode.add(node);
        this.m = m;
        numPairs = 1;
    }

   
    public ArrayList<Double> getKeys() {
        return keys;
    }

    public ArrayList<BPlusNode> getSubNodes() {
        return subnode;
    }
    
   
    public void addFirst(BPlusNode node) {
        subnode.add(0, node);
    }

    
    public BPlusNode searchSubNode(KeyValPair pair) {
        double key = pair.getPairKey();
        int index=Collections.binarySearch(keys,key);
        index= index >= 0 ? index + 1 : -index - 1;
        return subnode.get(index);
    }

    public BPlusNode searchSubNode(double key) {
    	 int index=Collections.binarySearch(keys,key);
         index= index >= 0 ? index + 1 : -index - 1;
        return subnode.get(index);
    }

    public BPlusSearch split() {

        ArrayList<Double> newKeys = new ArrayList<>();
        ArrayList<BPlusNode> newsubnode = new ArrayList<>();
        int index=subnode.size()-1;
        int keysize=keys.size()-1;
        newsubnode.add(subnode.get(index));
        subnode.remove(index);
        for (int i = keysize; i > m/2; i--) {
            newKeys.add(0, keys.get(i));
            newsubnode.add(0, subnode.get(i));
            keys.remove(i);
            subnode.remove(i);
        }

        
        BPlusSearch node = new BPlusSearch(newKeys, newsubnode, m);

     
        double key = keys.get(keys.size()-1);
        keys.remove(keys.size()-1);
        numPairs = keys.size();
         
        BPlusSearch b=new BPlusSearch(key, node, m);
        return b;

    }

    
    public void mergeWith(BPlusSearch node) {
        double key = node.getKeys().get(0);
        BPlusNode sub = node.getSubNodes().get(0);
        int index=Collections.binarySearch(keys,key);
        index= index >= 0 ? index + 1 : -index - 1;
        keys.add(index, key);
        subnode.add(index+1, sub);
        numPairs++;
       
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        if (keys.size() == 0) return str.toString();
        str.append(keys.get(0));
        for (int i = 1; i < keys.size(); i++) {
            str.append(","); 
            str.append(keys.get(i));
        }
        return str.toString();
    }
}
