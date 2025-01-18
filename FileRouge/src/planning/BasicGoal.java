package planning;

import java.util.Map;

import modelling.Variable;

/**
 * Classe représentant un but basique dans un problème de planification.
 * Un but basique est défini par une instanciation partielle des variables, et
 * il est satisfait si l'état donné affecte toutes les variables à la bonne valeur.
 */
public class BasicGoal implements Goal{


	private Map<Variable, Object> partialInstantiation ;
	
	/**
     * Constructeur de la classe BasicGoal.
     * 
     * @param partialInstantiation Un map représentant l'instanciation partielle des variables.
     */
	public BasicGoal(Map<Variable, Object> partialInstantiation) {
		
		this.partialInstantiation = partialInstantiation ; 
		
	}
	
	 /**
     * Retourne l'instanciation partielle des variables constituant le but.
     * 
     * @return Un map représentant l'instanciation partielle des variables.
     */
	public  Map<Variable, Object> getPartialInstantiation(){
		
		return this.partialInstantiation; 
		
	}

	 /**
     * Vérifie si le but est satisfait par l'état donné.
     * 
     * @param state Un map représentant l'état actuel.
     * @return true si le but est satisfait dans l'état donné, false sinon.
     */
	@Override
	public boolean isSatisfiedBy(Map<Variable, Object> state) {
        return state.entrySet().containsAll(this.partialInstantiation.entrySet());

	}
}
