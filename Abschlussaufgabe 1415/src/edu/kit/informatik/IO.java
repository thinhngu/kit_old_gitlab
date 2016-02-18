package edu.kit.informatik;

import java.util.LinkedList;

import edu.kit.informatik.parser.*;
public class IO {
        public static void main(String [] args) {
            String [] file = FileInputHelper.read(args[0]);
            LinkedList<Node> listofnodes = new LinkedList<Node>();
            NodeList nl = new NodeList(listofnodes);
            Filereader filereader = new Filereader(file,nl);
            
            filereader.filereader();
            boolean quit = false; 
            while(quit == false) {
                String input = Terminal.readLine();
                
                LinkedList<Node> allnodes = nl.getListOfNodes();
                if(input.startsWith("nodes")) {
                    String s = "";
                    for(int i = 0 ;i < allnodes.size() - 1;i++) {
                        s += allnodes.get(i).toString() + ",";
                    }
                    s += allnodes.getLast().toString();
                    Terminal.printLine(s);
                    
                }
                
                if(input.startsWith("edges")) {
                    String a = "";
                    for(int i = 0 ;i < allnodes.size() - 1;i++) {
                         a += allnodes.get(i).RelationtoString();
                    }
                    Terminal.printLine(a);
                    
                }
                if(input.startsWith("recommend")) {
                    String inputsplit[] = input.split("recommend");
                    
                    Parse parser = new Parse(inputsplit[1],nl);
                    String nodesofstrategy = "";
                    try {
                    nodesofstrategy += parser.parseterm().toString();
                    Terminal.printLine(nodesofstrategy);
                    }
                    catch(ParseException parse) {
                        Terminal.printLine("wrong input");
                    }
                }
            }
        }
}
