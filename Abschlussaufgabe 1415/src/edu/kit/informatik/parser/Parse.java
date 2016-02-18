package edu.kit.informatik.parser;

import java.util.StringTokenizer;
import edu.kit.informatik.*;

public class Parse {
    private String lookahead = "";
    NodeList nodelist;
    
    String idregex = "[0-9]+";
    
    String strategyregex = "S1|S2|S3";
    private StringTokenizer tokenizer = new StringTokenizer(lookahead, ",\\(\\)",
            true);

    public Parse(String input, NodeList nodelist) {
        this.lookahead = input;
        this.nodelist = nodelist;
    }

    private void next() {
        lookahead = tokenizer.nextToken();

    }

    private void match(String expected) throws ParseException {
        if (lookahead.equals(expected) == false) {
            throw new ParseException();
        } else {
            next();
        }
    }

    String in = lookahead.replaceAll(" ", "");
    /* einfache strategie wie "recommend S1 105" funktioniert, aber nur ohne next zeigt nicht auf das nächste
    token? */
    public Operation parseterm() throws ParseException {
        Operation operation;
        operation = parsefinale();
        
        if (operation == null) {
            if (in.startsWith("UNION") || in.startsWith("INTERSECTION")) {
                next();
                match("(");

                Operation one = parseterm();
                match(",");
                Operation two = parseterm();
                match(")");

                if (in.startsWith("UNION") || in.startsWith("INTERSECTION")) {
                    Union union = new Union(one, two);
                    return union;
                } else {
                    Intersection intersection = new Intersection(one, two);
                    return intersection;
                }
            }
        }
        else {
            return operation;
        }
        throw new ParseException();
    }

    private Operation parsefinale() throws ParseException {
        String strategy = "";
        int id = -1;
        String [] insplit = lookahead.split(" ");
        if (insplit[1].matches(strategyregex)) {
            strategy = insplit[1];
            //next();
        }

        if (insplit[2].matches(idregex)) {
            id = Integer.parseInt(insplit[2]);
            //next();
            Final finale = new Final(strategy, id, nodelist);
            return finale;
        }
        throw new ParseException();
    }

}
