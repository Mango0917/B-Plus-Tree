
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class treesearch {

public static void main(String[] args) throws IOException {

	  	BufferedReader in = new BufferedReader(new FileReader(args[0]));
	      String line = in.readLine();
	
	      int m = Integer.parseInt(line);
	      BPlusTree tree = new BPlusTree(m);
	  
	      PrintWriter write = new PrintWriter("output_file.txt", "UTF-8");
	      while (true){
	      	line = in.readLine();
	      	if (line == null) break;
	      	int i = line.indexOf('(');
	      	String type = line.substring(0, i);
	      	
	     
	      	double key; 
	      	String val; 
	      	int j = line.indexOf(',');
	     	int k = line.indexOf(')');
	      	if (j == -1) { 
	            key = Double.parseDouble(line.substring(i+1, k));
	            val = null;
	        } else { 
	            key = Double.parseDouble(line.substring(i+1, j));
	            val = line.substring(j+1, k);
	        }
	 
	        StringBuffer out =new StringBuffer();
	        if (type.equals("Insert")) {
	            tree.insert(key, val);
	        }
	        else if (type.equals("Search")) {
	            if (val == null) { 
	                ArrayList<String> vals = tree.search(key);
	                if (vals.size() == 0) out.append("Null"); 
	                else {
	                    out.append(vals.get(0)) ;
	                    for (int x = 1; x < vals.size(); x++) {
	                        out.append(",");
	                        out.append(vals.get(x));
	                    }
	                }
	            } 
	            else { 
	               
	                ArrayList<KeyValPair> pairs = tree.search(key, Double.parseDouble(val));
	                if (pairs.size() == 0) out.append("Null");
	                else {
	                    out.append(pairs.get(0));
	                    for (int x = 1; x < pairs.size(); x++) {
	                        out.append(","); 
	                        out.append(pairs.get(x));
	                    }
	                }
	            }
	        }
	        else System.out.println("Method not present");
	        if (out != null) {
	              System.out.print(out);
	              write.print(out);
	        }
	      }
	
	      write.close();
	      in.close();

	         
	    }

}
