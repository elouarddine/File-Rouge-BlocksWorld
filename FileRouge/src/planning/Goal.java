package planning;

import java.util.Map;

import modelling.Variable;


/**
 * Interface représentant un but dans un problème de planification.
 * Un but est défini par une condition qui doit être satisfaite dans un état donné.
 * Cette interface permet de vérifier si un état satisfait un but.
 */
public interface Goal {

	
	/**
     * Vérifie si le but est satisfait par l'état donné.
     * 
     * @param state Un map représentant l'état courant, où chaque Variable est associée à sa valeur.
     * @return true si le but est satisfait dans l'état donné, false sinon.
     */
	public boolean isSatisfiedBy(Map<Variable,Object> state);
     public  Map<Variable, Object> getPartialInstantiation();

}
