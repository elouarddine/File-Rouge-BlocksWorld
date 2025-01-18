package cp;

import modelling.Variable;
import java.util.Set;
import java.util.Map ; 

/**
 * Interface VariableHeuristic
 * Définit une heuristique de sélection pour déterminer la "meilleure" variable à instancier
 * dans un problème de satisfaction de contraintes (PPC).
 */
public interface VariableHeuristic {

	/**
     * Sélectionne la meilleure variable à instancier en fonction de l'heuristique implémentée et en se basant sur un ensemble de variable et un map qui lie ces variables à leur domaines.
     *
     * @param variables l'ensemble des variables non encore instanciées dans le PPC
     * @param domains un Map associant chaque variable à son ensemble de valeurs possibles
     * @return la variable choisie comme la meilleure à instancier selon l'heuristique
     */
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains);

}
