import java.io.*;
import java.util.*;

public class GrammarFactorization {
    List<String> nonTerminals = new ArrayList<>();
    List<String> terminals = new ArrayList<>();
    String startSymbol = "";
    Map<String, List<String>> productions = new HashMap<>();

    public static void main(String[] args) throws IOException {
        GrammarFactorization g = new GrammarFactorization();
        g.readGrammar("grammar.txt");
        g.factorizeGrammar();
    }

    public void readGrammar(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            nonTerminals = Arrays.asList(br.readLine().split(","));
            terminals = Arrays.asList(br.readLine().split(","));
            startSymbol = br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                List<String> prod = new ArrayList<>(Arrays.asList(splitLine));
                String key = prod.remove(0);
                productions.put(key, prod);
            }
        }
    }
    public void factorizeGrammar() {
    Map<String, List<String>> newProductions = new HashMap<>();

    for (String nonTerminal : nonTerminals) {
        List<String> rules = productions.get(nonTerminal);
        Map<String, List<String>> commonPrefixes = new HashMap<>();

        // Agrupar por primer caracter en el lado derecho
        for (String rule : rules) {
            String prefix = rule.substring(0, 1);
            if (!commonPrefixes.containsKey(prefix)) {
                commonPrefixes.put(prefix, new ArrayList<>());
            }
            commonPrefixes.get(prefix).add(rule.substring(1));
        }

        // Factorizar si hay más de una regla con el mismo prefijo
        for (Map.Entry<String, List<String>> entry : commonPrefixes.entrySet()) {
            String prefix = entry.getKey();
            List<String> rest = entry.getValue();

            if (rest.size() > 1) {
                String newNonTerminal = nonTerminal + "_1";  // Añadir subíndice
                nonTerminals.add(newNonTerminal);
                newProductions.put(newNonTerminal, rest);  // Añadir reglas de producción
                if (!newProductions.containsKey(nonTerminal)) {
                    newProductions.put(nonTerminal, new ArrayList<>());
                }
                newProductions.get(nonTerminal).add(prefix + newNonTerminal);
            } else {
                if (!newProductions.containsKey(nonTerminal)) {
                    newProductions.put(nonTerminal, new ArrayList<>());
                }
                newProductions.get(nonTerminal).add(prefix + rest.get(0));
            }
        }
    }

    productions = newProductions;
}
  
}

