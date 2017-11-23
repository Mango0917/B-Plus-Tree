
import java.util.ArrayList;
/*Class to store Key-Value pairs and search for them*/
public class BPlusRSearch extends BPlusNode {

    ArrayList<KeyValPair> pairs;      
    public BPlusRSearch lchild;       
    public BPlusRSearch rchild;      

   
    public BPlusRSearch(KeyValPair pair, int m) {
        
        isPair = true;
        pairs = new ArrayList<>();
        pairs.add(pair);
        this.m = m;
        numPairs = 1;
    }

    public BPlusRSearch(ArrayList<KeyValPair> pairs, BPlusRSearch lchild, BPlusRSearch rchild, int m) {
      
        isPair = true;
        this.pairs = pairs;
        this.lchild = lchild;
        this.rchild = rchild;
        this.m = m;
        numPairs = pairs.size();
    }

    private int searchIndex(double key) {
        int low = 0;
        int high = pairs.size() - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (key == pairs.get(mid).getPairKey()) {
                return mid;
            }
            else if (key < pairs.get(mid).getPairKey()) {
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return low;
    }

    public void search(double key, ArrayList<String> vals) {
        int index = searchIndex(key);
    	
        BPlusRSearch nodePair = this;
        int right = index;
        while (true) {
        	
        	 if (right >= nodePair.pairs.size() ) {
        		 	if(nodePair.rchild != null) {
        		 		nodePair = nodePair.rchild;
        		 		right = 0;
        		 }
        	 }
        	 if (right < nodePair.pairs.size()) {
        		 if( key == nodePair.pairs.get(right).getPairKey()) {
        		 		vals.add(nodePair.pairs.get(right).getPairValue());
        		 		right+=1;
        		 }
        		 else break;
        	 }
            else break;
        }

        nodePair = this;
        int left = index - 1;
        while (true) {
            
        	if (left < 0) {
        		if( nodePair.lchild != null) {
        			nodePair = nodePair.lchild;
        			left = nodePair.pairs.size() - 1;
        		}
        	}
            if (left >= 0 ) {
            	if( key == nodePair.pairs.get(left).getPairKey()) {
            		vals.add(0, nodePair.pairs.get(left).getPairValue());
            		left-=1;
            	}
                else break;
            }
            else break;
        }
    }

   
    public void search(double key1, double key2, ArrayList<KeyValPair> res) {
        int index = searchIndex(key2);
        BPlusRSearch nodePair = this;
        int right = index;
        while (true) {
            if (right >= nodePair.pairs.size() ) {
            	if( nodePair.rchild != null) {
                nodePair = nodePair.rchild;
                right = 0;
            	}
            	
            }
            if (right < nodePair.pairs.size()) {
            	if( key1 <= nodePair.pairs.get(right).getPairKey()) {
                    if( key2 >= nodePair.pairs.get(right).getPairKey()) {
                    	res.add(nodePair.pairs.get(right));
                    	right++;
                    }
                    else break;
            	}
            	else break;
            }
            else break;
        }

      
        nodePair = this;
        int left = index - 1;
        while (true) {
            if (left < 0) {
            	if(nodePair.lchild != null) {
            		nodePair = nodePair.lchild;
                	left = nodePair.pairs.size() - 1;
            	}
        	}
            if (left >= 0 ) {
            	if( key1 <= nodePair.pairs.get(left).getPairKey()) {
                    if(key2 >= nodePair.pairs.get(left).getPairKey()) {
                    	res.add(0, nodePair.pairs.get(left));
                    	left--;
                    }
                    else break;
            	}
            	else break;
            }
            
            else break;
        }
    }

   
    public void insert(KeyValPair pair) {
   
        int index = searchIndex(pair.getPairKey());
        pairs.add(index, pair);
        numPairs++;
    }

    public BPlusSearch split() {

        int pairsize=pairs.size()-1;
        ArrayList<KeyValPair> Pairs = new ArrayList<>();
        for (int i = pairsize; i >= m/2; i--) {
            Pairs.add(0, pairs.get(i));
            pairs.remove(i);
        }

        BPlusRSearch rchild = this.rchild;      
        BPlusRSearch lchild = this;
        BPlusRSearch node = new BPlusRSearch(Pairs, lchild, rchild, m);
        this.rchild = node;
        if (rchild != null) rchild.lchild = node;

        numPairs = pairs.size();
        BPlusSearch b=new BPlusSearch(Pairs.get(0).getPairKey(), node, m);
        return b;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        if (pairs.size() == 0) return str.toString();
        str.append(pairs.get(0));
        for (int i = 1; i < pairs.size(); i++) {
            str.append( ",");
            str.append(pairs.get(i));
        }
        return str.toString();
    }

}
