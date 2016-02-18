package edu.kit.informatik.parser;
import edu.kit.informatik.*;
import java.util.*;
public class Union extends Combined{
    
    public Union(Operation one ,Operation two) {
        super(one,two);
    }
    
    @Override
    public LinkedList<Node> getStrategyNodes() {
        LinkedList<Node> strategynodes = strattwo.getStrategyNodes();
        LinkedList<Node> sone = stratone.getStrategyNodes();
        for(int i = 0; i< sone.size();i++ ) {
            if(strategynodes.contains(sone.get(i)) == false) {
                strategynodes.add(sone.get(i));
            }
        }
        return strategynodes;
    }
}
