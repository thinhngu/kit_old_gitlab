package edu.kit.informatik.parser;

import java.util.StringTokenizer;
import edu.kit.informatik.*;

public class Parse {
    private String lookahead = "";
    NodeList nodelist;

    String idregex = "[0-9]+";

    String strategyregex = "S1|S2|S3";
    private StringTokenizer tokenizer;

    public Parse(String input, NodeList nodelist) {
        this.lookahead = input;
        this.nodelist = nodelist;
        tokenizer = new StringTokenizer(lookahead, ",\\(\\)", true);
        next();
    }

    private void next() {
        if (tokenizer.hasMoreTokens()) {
            lookahead = tokenizer.nextToken();
        }

    }

    private void match(String expected) throws ParseException {
        if (lookahead.equals(expected) == false) {
            throw new ParseException();
        } else {
            next();
        }
    }

    /*
     * einfache strategie wie "recommend S1 105" funktioniert, aber nur ohne
     * next zeigt nicht auf das nächste token? recommend UNION(S1 105, S1 105)
     * recommend UNION(UNION ( S1 105,S1 105), S1 105)
     */
    public Operation parseterm() throws ParseException {

        lookahead = lookahead.trim();
        if (lookahead.startsWith("UNION")
                || lookahead.startsWith("INTERSECTION")) {

            String in = lookahead;
            next();
            match("(");

            Operation one = parseterm();
            match(",");
            Operation two = parseterm();
            match(")");

            if (in.startsWith("UNION")) {
                Union union = new Union(one, two);
                return union;
            } else {
                Intersection intersection = new Intersection(one, two);
                return intersection;
            }
        }
        return parsefinale();

    }

    private Operation parsefinale() throws ParseException {
        String strategy = "";
        int id = -1;
        String[] insplit = lookahead.split(" ");
        if (insplit[0].matches(strategyregex)) {
            strategy = insplit[0];

        }

        if (insplit[1].matches(idregex)) {
            id = Integer.parseInt(insplit[1]);
            next();
            Final finale = new Final(strategy, id, nodelist);
            return finale;
        }
        throw new ParseException();
    }

}
