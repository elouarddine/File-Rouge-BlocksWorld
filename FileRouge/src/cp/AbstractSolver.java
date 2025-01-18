
package cp;

import java.util.Map;
import java.util.Set; 
import modelling.Constraint;
import modelling.Variable;


/**
 * Classe abstraite AbstractSolver implémente l'interface Solver et fournit des méthodes pour gérer
 * les variables et les contraintes dans un problème de satisfaction de contraintes (CSP).
 */
public abstract class AbstractSolver implements Solver {
	
	
	private Set<Variable> variables ;
	private Set<Constraint> contraintes;
	
	
	/**
     * Constructeur de la classe AbstractSolver.
     *
     * @param variables   l'ensemble des variables du problème
     * @param contraintes l'ensemble des contraintes du problème
     */
	public AbstractSolver(Set<Variable> variables , Set<Constraint> contraintes){
		
		this.variables = variables; 
		this.contraintes = contraintes;
		
	}
	
	/**
     * Accesseur qui retourne l'ensemble des contraintes du problème.
     *
     * @return l'ensemble des contraintes
     */
	public Set<Constraint> getContraintes(){
		return this.contraintes;
	}
	 
	/**
     * Accesseur retourne l'ensemble des variables du problème.
     *
     * @return l'ensemble des variables
     */
	public Set<Variable> getVariables(){
		return this.variables;
	}
	
	/**
     * Vérifie si une instanciation partielle satisfait toutes les contraintes du probléme.
     *
     * @param partialInstantiation une instanciation partielle des variables
     * @return true si toutes les contraintes sur les variables instanciées sont satisfaites, false sinon
     */
	public boolean isConsistent(Map<Variable,Object> partialInstantiation){
		
		for(Constraint contrainte : this.contraintes) {
			
			if(partialInstantiation.keySet().containsAll(contrainte.getScope())) {
				
				if (!contrainte.isSatisfiedBy(partialInstantiation)) {
					
					return false; 
				}		
				
			}
			
		}
		
		return true;
	}
	
	/**
     * Fournit une représentation en chaîne de caractères de l'AbstractSolver.
     *
     * @return une représentation en chaîne de caractères des contraintes et des variables
     */
	 @Override
	  public String toString() {
	        return "{Contraintes=" + this.contraintes + ", Variables=" + this.variables + "}";
	    }
}