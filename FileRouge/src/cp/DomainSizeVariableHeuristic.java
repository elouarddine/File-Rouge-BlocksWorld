
package cp;

import java.util.Map;
import java.util.Set;

import modelling.Variable;


/**
 * Classe DomainSizeVariableHeuristic
 * Implémente une heuristique de sélection de variable basée sur la taille du domaine de chaque variable
 * dans un problème de satisfaction de contraintes (PPC). Cette heuristique peut être configurée pour
 * privilégier les variables ayant le plus grand ou le plus petit domaine.
 */
public class DomainSizeVariableHeuristic implements VariableHeuristic{
	
	protected boolean greatest;
	
	/**
	 * Constructeur de DomainSizeVariableHeuristic.
	 *
	 * @param greatest un booléen indiquant la préférence de taille de domaine :
	 *                            - true pour sélectionner la variable ayant le plus grand domaine
	 *                            - false pour sélectionner la variable ayant le plus petit domaine
	 */
	public DomainSizeVariableHeuristic(boolean greatest) {
		this.greatest = greatest;
	}
	
	private boolean getGreatest(){
		return this.greatest ;
	}
	
	
	/**
	 * Sélectionne la meilleure variable en fonction de la taille de son domaine.
	 *
	 * @param variables l'ensemble des variables non encore instanciées
	 * @param domaines un Map associant chaque variable à son ensemble de valeurs possibles
	 * @return la variable choisie en fonction de la préférence pour le plus grand ou le plus petit domaine
	 */
	@Override
	public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domaines) {
		
		Variable variable = null;
		int minMaxCount = this.greatest ? -1 : Integer.MAX_VALUE;
		
		for (Variable var : variables) {
			int count = domaines.get(var).size();
			if ((this.greatest && count > minMaxCount) || (!this.greatest && count < minMaxCount)) {
				minMaxCount = count;
				variable = var;
			}
		}
		
		return variable;
	}
	
	/**
	 * Retourne une représentation textuelle de DomainSizeVariableHeuristic indiquant la préférence de taille de domaine.
	 *
	 * @return une chaîne de caractères représentant l'état de l'heuristique (préférence pour le plus grand ou le plus petit domaine)
	 */
	@Override
	public String toString() {
		return "DomainSizeVariableHeuristic {more=" + getGreatest() + "}";
	}
	
}

