package edu.kit.informatik;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.kit.informatik.Relation.Relationship;

public class Filereader {
    
    private static final String Productregex = "[a-zA-Z0-9]+\\s*\\(\\s*id\\s*=\\s*[0-9]+\\s*\\)";
    private static final String Predicate = "contains|contained-in|part-of|has-part|successor-of|predecessor-of";
    NodeList nl;
    String[] file;

    public Filereader(String[] file, NodeList nl) {
        this.file = file;
        this.nl = nl;
    }

    public void filereader() {

        Pattern predicate = Pattern.compile(Predicate);
        Pattern productregex = Pattern.compile(Productregex);
        for (int i = 0; i < file.length; i++) {
            Matcher mpredicate = predicate.matcher(file[i]);

            String prehelp = "";
            mpredicate.find();
            prehelp = file[i].substring(mpredicate.start(), mpredicate.end());

            String[] prodcatstring = file[i].split(prehelp);
            String first = "";
            String secound = "";
            for (int j = 0; j < prodcatstring.length; j++) {
                Matcher product = productregex.matcher(prodcatstring[j]);

                if (product.find()) {
                    String[] prodparts = prodcatstring[j].split("\\(");
                    int links = prodparts[1].indexOf("=");
                    int rechts = prodparts[1].indexOf(")");
                    String indnr = prodparts[1].substring(links + 1, rechts)
                            .trim();
                    Product newproduct = new Product(prodparts[0].trim()
                            .toLowerCase(), Integer.parseInt(indnr));
                    nl.add(newproduct);
                    if (j == 0) {
                        first = prodparts[0].trim().toLowerCase();
                    } else {
                        secound = prodparts[0].trim().toLowerCase();
                    }
                } else {
                    Category newcategory = new Category(prodcatstring[j].trim()
                            .toLowerCase());
                    nl.add(newcategory);
                    if (j == 0) {
                        first = prodcatstring[j].trim().toLowerCase();
                    } else {
                        secound = prodcatstring[j].trim().toLowerCase();
                    }
                }
            }
            nl.getNode(first).addRelation(nl.getNode(secound), StoR(prehelp));
        }
        
    }

    private Relationship StoR(String s) {
        Relationship r = null;
        switch (s) {
        case "contains":
            r = Relationship.contains;
            break;
        case "contained-in":
            r = Relationship.containedin;
            break;
        case "part-of":
            r = Relationship.partof;
            break;
        case "has-part":
            r = Relationship.haspart;
            break;
        case "successor-of":
            r = Relationship.successorof;
            break;
        case "predecessor-of":
            r = Relationship.predecessorof;
            break;
        }

        return r;
    }

}
