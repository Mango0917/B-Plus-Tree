/* Class to implement Key-Value Pair and to get and set its value */ 
public class KeyValPair  {
	
    private final double key;
    private final String value;

    
    public KeyValPair(double key, String value) {
        this.key = key;
        this.value = value;
    }

   
    public double getPairKey() {
        return key;
    }

 
    public String getPairValue() {
        return value;
    }

    @Override
    public String toString() {
    	
    	StringBuffer str=new StringBuffer();
    	str.append("(");
    	str.append(key);
    	str.append(",");
    	str.append(value);
    	str.append(")");
        return str.toString();
    }

}
