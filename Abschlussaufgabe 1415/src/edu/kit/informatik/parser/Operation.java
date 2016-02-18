package edu.kit.informatik.parser;
import edu.kit.informatik.*;
import java.util.*;
public class Operation {
    
    
    public LinkedList<Node> getStrategyNodes() {
        return null;
    }
    
    public String toString() {
        String a = "";
        for(int i = 0; i < getStrategyNodes().size(); i++) {
           a += getStrategyNodes().get(i).toString();
        }
        
        return a;
    }
}
