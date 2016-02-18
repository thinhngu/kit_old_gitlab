package edu.kit.informatik.parser;
import edu.kit.informatik.*;

import java.util.*;
public class Final extends Operation {
    String strategy;
    int id;
    NodeList nodelist;
    
    public Final(String strategy, int id,NodeList nodelist) {
        this.strategy  = strategy;
        this.id = id;
        this.nodelist = nodelist;
    }
    
    @Override
    public LinkedList<Node> getStrategyNodes() {
        
        if(strategy.equals("S1")) {
            return nodelist.getSiblingsOf(nodelist.getNodeById(id));
            }
        if(strategy.equals("S2")) {
           return nodelist.getSuccesor(nodelist.getNodeById(id));
            }
        if(strategy.equals("S3")) {
            return nodelist.getPredecessor(nodelist.getNodeById(id));
            }
        return null;
    }
}
