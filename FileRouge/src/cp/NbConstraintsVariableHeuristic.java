
package cp;

import java.util.Map;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

/**
 * Classe NbConstraintsVariableHeuristic
 * Implémente une heuristique de sélection de variable basée sur le nombre de contraintes dans lesquelles
 * chaque variable apparaît dans un problème de satisfaction de contraintes (PPC). Cette heuristique peut
 * être configurée pour privilégier les variables apparaissant dans le plus ou le moins de contraintes.
 */
public class NbConstraintsVariableHeuristic implements VariableHeuristic{
	
	private Set<Constraint> contraintes ; 
	private boolean most ;
	
	/**
	 * Constructeur de NbConstraintsVariableHeuristic.
	 *
	 * @param contraintes l'ensemble des contraintes du problème
	 * @param most un booléen indiquant la préférence pour le nombre de contraintes :
	 *                              - true pour sélectionner la variable apparaissant dans le plus de contraintes
	 *                              - false pour sélectionner la variable apparaissant dans le moins de contraintes
	 */
	public NbConstraintsVariableHeuristic(Set<Constraint> contraintes , boolean most){
		this.contraintes = contraintes;
		this.most = most;
	}
	
	public Set<Constraint> getContraintes(){
		return this.contraintes;
	}
	
	private boolean getMost(){
		return this.most ;
	}
	
	/**
	 * Sélectionne la meilleure variable en fonction du nombre de contraintes auxquelles elle est liée.
	 *
	 * @param variables l'ensemble des variables non encore instanciées
	 * @param domaines un Map associant chaque variable à son ensemble de valeurs possibles
	 * @return la variable choisie en fonction de la préférence pour le plus ou le moins de contraintes
	 */
	@Override
	public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domaines) {
		
		Variable variable = null;
		int minMaxCount = this.most ? -1 : Integer.MAX_VALUE;
		
		for (Variable var : variables) {
			int count = 0;
			
			for (Constraint constraint : this.contraintes) {
				if (constraint.getScope().contains(var)) {
					count++;
				}
			}
			
			if ((this.most && count > minMaxCount) || (!this.most && count < minMaxCount)) {
				minMaxCount = count;
				variable = var;
			}
		}
		
		return variable;
	}
	
	
	/**
	 * Retourne une représentation textuelle de NbConstraintsVariableHeuristic indiquant la préférence pour le nombre de contraintes.
	 *
	 * @return une chaîne de caractères représentant l'état de l'heuristique (préférence pour le plus ou le moins de contraintes)
	 */
	@Override
	public String toString() {
		return "NbConstraintsVariableHeuristic {most=" + getMost() + "}";
	}
	
}


