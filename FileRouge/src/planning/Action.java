package planning;
import java.util.Map;

import modelling.Variable;



/**
 * Interface représentant une action dans un problème de planification.
 * Une action est spécifiée par des préconditions, des effets et un coût.
 * Elle permet de déterminer si elle est applicable dans un état donné,
 * et de générer l'état suivant après son application.
 */
public interface Action {

	/**
     * Vérifie si l'action est applicable dans l'état donné.
     * 
     * @param state Un map représentant l'état courant, où chaque Variable est associée à sa valeur.
     * @return true si l'action est applicable (c'est-à-dire si toutes les préconditions sont satisfaites),
     *         false sinon.
     */
	public boolean isApplicable(Map<Variable,Object> state);
	
	
	/**
     * Applique l'action sur l'état donné et génère un nouvel état.
     * 
     * @param state Un map représentant l'état actuel.
     * @return Un nouvel état modifié en fonction des effets de l'action, ou l'etat courant si l'action n'est pas applicable.
     */
	public Map<Variable, Object> successor(Map<Variable, Object> state);
	
	
	/**
     * Retourne le coût de l'action.
     * 
     * @return Un entier représentant le coût de l'action.
     */
	public int getCost();
}
