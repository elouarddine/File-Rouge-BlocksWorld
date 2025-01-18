
package cp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

/**
 * Classe BacktrackSolver qui implémente un solveur par retour en arrière (backtracking) pour résoudre un problème de satisfaction de contraintes.
 */
public class BacktrackSolver extends AbstractSolver {
	
	/**
     * Constructeur de la classe BacktrackSolver.
     *
     * @param variables l'ensemble des variables du problème
     * @param contraintes l'ensemble des contraintes du problème
     */
	public BacktrackSolver(Set<Variable> variables, Set<Constraint> contraintes) {
		super(variables, contraintes);
	}
	
	
	
	/**
     * Résout le problème de satisfaction de contraintes en utilisant l'algorithme de backtracking.
     * La recherche s'effectue de manière récursive en instanciant progressivement les variables
     * tout en respectant les contraintes.
     *
     * @param partialInstantiation une instanciation partielle des variables
     * @param v liste des variables non instanciées
     * @return une solution sous la forme d'une map associant chaque variable à une valeur, ou null si aucune solution n'est trouvée
     */
	private Map<Variable, Object> backTrack(Map<Variable, Object> partialInstantiation , LinkedList<Variable> v){
		
		
		if(v.isEmpty()){return partialInstantiation;}
		
		Variable x = v.poll();
		
		for(Object obj : x.getDomain()){
			
			Map<Variable, Object> instantiation = new HashMap<>(partialInstantiation);
			instantiation.put(x , obj);
			
			if(isConsistent(instantiation)){
				
				Map<Variable, Object> solution = backTrack(instantiation,new LinkedList<>(v));
				
				if(solution != null){
					return solution;
				}
			}
			
		}
		v.add(x);
		return null;
		
	}
	
	
	/**
     * Méthode principale pour résoudre le problème.
     * Elle initialise l'instanciation partielle et la liste des variables non instanciées,
     * puis appelle la méthode backTrack.
     *
     * @return une solution sous la forme d'une map associant chaque variable à une valeur,
     *         ou null si aucune solution n'est trouvée
     */
	@Override
	public Map<Variable, Object> solve() {
		return backTrack(new HashMap<>() , new LinkedList<>(getVariables()));
	}
	
	/**
     * Fournit une représentation en chaîne de caractères du BacktrackSolver.
     *
     * @return une représentation en chaîne de caractères incluant le type de solveur et les informations
     *         sur les variables et les contraintes
     */
	@Override
	public String toString() {
		return "BacktrackSolver" + super.toString();
	}
}