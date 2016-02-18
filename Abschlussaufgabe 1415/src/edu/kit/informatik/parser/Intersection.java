package edu.kit.informatik.parser;
import edu.kit.informatik.*;

import java.util.*;

public class Intersection extends Combined {
    public Intersection(Operation one ,Operation two) {
        super(one,two);
    }
    
    @Override
    public LinkedList<Node> getStrategyNodes() {
        LinkedList<Node> strategynodes = new LinkedList<Node>();
        LinkedList<Node> sone = stratone.getStrategyNodes();
        for(int i = 0; i< sone.size();i++ ) {
            if(strattwo.getStrategyNodes().contains(sone.get(i))) {
                strategynodes.add(sone.get(i));
            }
        }
        return strategynodes;
    }
}
