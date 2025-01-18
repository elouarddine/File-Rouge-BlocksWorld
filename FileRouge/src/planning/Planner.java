package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;


/**
 * Interface représentant un planificateur qui trouve une séquence d’actions permettant de passer de
 * l’état initial à un état qui satisfait le but.
 */
public interface Planner {

	
	/**
     * Génère un plan d'actions (une séquence d'action) pour atteindre le but à partir de l'état initial.
     * Retourne une liste d'actions à exécuter, ou null s'il n'y a pas de plan possible.
     *
     * @return Une liste d'actions à exécuter pour atteindre l'objectif, ou null
     *         s'il n'y a pas de plan possible.
     */
	public List<Action> plan();
	
	/**
     * Retourne l'état initial du probléme de planification.
     *
     * @return L'état initial.
     */
	public Map<Variable, Object> getInitialState();
	
	/**
     * Retourne l'ensemble des actions disponibles pour le planificateur.
     *
     * @return Un ensemble des actions disponibles.
     */
	public Set<Action> getActions();
	
	/**
     * Retourne le but que le planificateur cherche à atteindre.
     *
     * @return L'objectif du planificateur sous forme de Goal.
     */
	public Goal getGoal();


	/**
	 * Méthode pour obtenir le nombre de nœuds explorés pendant l'exécution de l'algorithme.
	 *
	 * @return le nombre de nœuds explorés
	 */
    public int getExploredNode() ;

}
