package cp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

/**
 * Classe pour résoudre un problème de satisfaction de contraintes (CSP) en utilisant l'algorithme MACSolver
 * avec des optimisations heuristiques. Fonctionne uniquement avec des CSP contenant des contraintes unaires ou binaires.
 * 
 */
public class HeuristicMACSolver extends AbstractSolver {
    
   
    protected ArcConsistency arcConsistency;

    
    protected ValueHeuristic valHeuristic;

    
    protected VariableHeuristic varHeuristic;

    /**
     * Crée un nouveau solveur utilisant l'algorithme MacSolver avec l'aide de la cohérence d'arc et d'optimisations heuristiques.
     * @param variables les variables de notre CSP.
     * @param contraintes les contraintes de notre CSP.
     * @param varHeuristic l'heuristique de sélection des variables que nous allons utiliser ici.
     * @param valHeuristic l'heuristique de sélection des valeurs que nous allons utiliser ici.
     * @throws IllegalArgumentException si l'ensemble donné contient une contrainte avec un scope supérieur à 2.
     */
    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> contraintes, VariableHeuristic varHeuristic, ValueHeuristic valHeuristic) {
        super(variables, contraintes);
        this.arcConsistency = new ArcConsistency(contraintes);
        this.valHeuristic = valHeuristic;
        this.varHeuristic = varHeuristic;
    }

    
    
    
    /**
     * Résout le problème de satisfaction de contraintes (CSP) en appliquant l'algorithme MAC
     * avec des heuristiques de sélection de variable et de valeur.
     *
     * @return une instanciation complète des variables satisfaisant toutes les contraintes si une solution existe,
     *         sinon retourne null
     */
    @Override
    public Map<Variable, Object> solve() {
        Map<Variable, Set<Object>> domaines = new HashMap<>();

        for(Variable v : getVariables())
            domaines.put(v, v.getDomain());

        return macH(new HashMap<>(), new HashSet<>(getVariables()), domaines);
    }

    /**
     * Méthode privée exécutant le MacSolver avec ses heuristiques.
     * 
     * Complexité dans le pire des cas : identique à MacSolver.
     * 
     * @param res instanciation partielle représentant notre solution construite jusqu'à présent.
     * @param variablesRestantes un ensemble des variables restantes auxquelles nous devons attribuer une valeur.
     * @param domaines une référence à une map liant les variables de notre CSP à un domaine explorable en fonction des contraintes unaires.
     * @return une instanciation satisfaisant toutes les contraintes si possible, sinon null.
     */
    private Map<Variable, Object> macH(Map<Variable, Object> res, Set<Variable> variablesRestantes, Map<Variable, Set<Object>> domaines) {
        if(variablesRestantes.isEmpty())
            return res;
        
        if(!this.arcConsistency.ac1(domaines))
            return null;
        
        Variable xi = this.varHeuristic.best(variablesRestantes, domaines);
        variablesRestantes.remove(xi);

        for(Object vi : this.valHeuristic.ordering(xi, domaines.get(xi))) {
            Map<Variable, Object> tmpRes = new HashMap<>(res);
            tmpRes.put(xi, vi);
            if(isConsistent(tmpRes)) {
                Map<Variable, Object> nouvelleInstanciation = macH(tmpRes, variablesRestantes, domaines);
                
                if(nouvelleInstanciation != null)
                    return nouvelleInstanciation;
            }
        }

        variablesRestantes.add(xi);
        return null;
    }

    
    /**
     * Retourne une représentation textuelle de HeuristicMACSolver, incluant les heuristiques utilisées
     * pour la sélection des variables et des valeurs.
     *
     * @return une chaîne de caractères représentant l'état actuel du solveur avec les heuristiques de variable et de valeur
     */
    @Override
    public String toString() {
        return "HeuristicMACSolver" + super.toString() + " avec " + this.varHeuristic + " et " + this.valHeuristic;
    }

	
}
