
package planning;

import java.util.*;

import modelling.Variable;



/**
 * Classe DijkstraPlanner qui implémente un planificateur basé sur l'algorithme de Dijkstra.
 * Cet algorithme explore les états en fonction de la plus petite distance cumulée depuis l'état initial.
 * Il est utilisé pour trouver le chemin le plus court dans un graphe pondéré.
 */

public class DijkstraPlanner implements Planner{
	
	private Map<Variable,Object> initialState ;
	private Set<Action> actions ;
	private Goal goal;
	protected int exploredNodes;
	protected boolean nodeCountActive;  

	public DijkstraPlanner(Map<Variable,Object> initialState , Set<Action> actions , Goal goal) {
		
		this.initialState = initialState ; 
		this.actions = actions ; 
		this.goal = goal ; 
		this.exploredNodes = 0;
		this.nodeCountActive = false;

	}

	
	
	/**
	 * Active ou désactive la sonde pour compter les nœuds explorés.
	 *
	 * @param activate Si true, active le comptage des nœuds, sinon le désactive.
	 */
	public void activateNodeCount(boolean activate) {
	    this.nodeCountActive = activate;
	}
	
	
	
	/**
     * Méthode pour reconstruire le plan optimal trouvé par l'algorithme de Dijkstra à partir des états explorés.
     *
     * @param father Une Map associant chaque état à son parent, qui est l'état précédent dans le chemin.
     * @param plan Une Map associant chaque état à l'action qui a permis de l'atteindre.
     * @param goals Un ensemble d'états qui satisfont l'objectif.
     * @param distance Une Map associant chaque état à sa distance cumulée depuis l'état initial.
     * @return Une liste d'actions représentant le plan optimal trouvé par Dijkstra.
     */
	private List<Action> getDijkstraPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
			
			Map<Map<Variable, Object>, Action> plan,
			Set<Map<Variable, Object>> goals,
			Map<Map<Variable, Object>, Float> distance) {
	
		
		LinkedList<Action> dijkstra_plan = new LinkedList<>();
		Map<Variable, Object> goal = argmin(distance, goals);
		
		while (goal != null && goal != this.initialState) {
			dijkstra_plan.add(plan.get(goal));
			goal = father.get(goal);
		}
		
		Collections.reverse(dijkstra_plan);
		return dijkstra_plan;
	}
	
	
	
	/**
     * Méthode qui exécute l'algorithme de Dijkstra pour trouver un plan optimal menant
     * de l'état initial à l'état objectif en explorant les états avec la plus petite distance cumulée.
     *
     * @return Une liste d'actions représentant le plan trouvé, ou `null` si aucun plan n'est trouvé.
     */	
	@Override
	public List<Action> plan() {
	   
		Map<Map<Variable, Object>, Action> plan = new HashMap<>();
	    Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
	    Set<Map<Variable, Object>> goals = new HashSet<>();
	    Map<Map<Variable, Object>, Float> distanceMap = new HashMap<>();

	    PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(
	            Comparator.comparing(distanceMap::get)
	    );
	    
	    distanceMap.put(this.initialState, 0.0f);
	    father.put(this.initialState, null);
	    open.add(this.initialState);
	    
	    while (!open.isEmpty()) {
	        if (nodeCountActive) {  
	            this.exploredNodes++;
	        }

	        Map<Variable, Object> instantiation = open.poll();

	        if (this.goal.isSatisfiedBy(instantiation)) {
	            goals.add(instantiation);
	        }

	        for (Action a : this.actions) {
	            if (a.isApplicable(instantiation)) {
	                Map<Variable, Object> next = a.successor(instantiation);

	                if (!distanceMap.containsKey(next)) {
	                    distanceMap.put(next, Float.POSITIVE_INFINITY);
	                }

	                if (distanceMap.get(next) > distanceMap.get(instantiation) + a.getCost()) {
	                    distanceMap.put(next, distanceMap.get(instantiation) + a.getCost());
	                    father.put(next, instantiation);
	                    plan.put(next, a);

	                    if (!open.contains(next)) {
	                        open.add(next);  
	                    }
	                }
	            }
	        }
	    }

	    if (goals.isEmpty()) {
	        return null;
	    }

	    return getDijkstraPlan(father, plan, goals, distanceMap);
	}
	/**
     * Méthode pour trouver l'état avec la plus petite distance dans un ensemble d'états ouverts.
     * Cette méthode est utilisée pour sélectionner le prochain état à explorer dans l'algorithme.
     *
     * @param distance Une Map contenant les distances cumulées pour chaque état.
     * @param open Un ensemble d'états ouverts à explorer.
     * @return L'état avec la plus petite distance.
     */
	private Map<Variable, Object> argmin(Map<Map<Variable, Object>, Float> distance, Set<Map<Variable, Object>> open) {
		
		Map<Variable, Object> res = null;
		Float min = null;
		
		for (Map<Variable, Object> state : open) {
			if (res == null || distance.get(state) < min) {
				res = state;
				min = distance.get(state);
			}
		}
		return res;
	}
	
	
	
	
	/**
     * Méthode pour obtenir le nombre de nœuds explorés pendant l'exécution de l'algorithme.
     *
     * @return Le nombre de nœuds explorés.
     */
	@Override
	public int getExploredNode() {
		return this.exploredNodes;
	}
	
	/**
	 * Retourne l'état initial du probléme de planification.
	 *
	 * @return L'état initial.
	 */
	@Override
	public Map<Variable, Object> getInitialState() {
		return this.initialState;
	}
	
	
	/**
	 * Retourne l'ensemble des actions disponibles pour le planificateur.
	 *
	 * @return Un ensemble des actions disponibles.
	 */
	@Override
	public Set<Action> getActions() {
		return this.actions;
	}
	
	
	/**
	 * Retourne le but que le planificateur cherche à atteindre.
	 *
	 * @return L'objectif du planificateur sous forme de Goal.
	 */
	@Override
	public Goal getGoal() {
		return this.goal;
	}
	
	
	
}
