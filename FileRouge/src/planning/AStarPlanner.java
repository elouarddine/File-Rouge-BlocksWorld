
package planning;

import java.util.*;

import modelling.Variable;




/**
 * Classe AStarPlanner qui implémente un planificateur basé sur l'algorithme A* (AStar).
 * Cet algorithme combine les avantages de la recherche en largeur pondérée (comme Dijkstra) et de l'heuristique
 * pour trouver le chemin optimal en fonction de la distance et d'une estimation du coût restant.
 */
public class AStarPlanner implements Planner {

    protected Map<Variable, Object> initialState;
    protected Set<Action> actions;
    protected Goal goal;
    protected Heuristic heuristic;
    protected int exploredNodes;
	protected boolean nodeCountActive;  


    
    /**
     * Constructeur du planificateur A*, initialisant l'état initial, les actions disponibles, l'objectif et l'heuristique.
     * 
     * @param initialState L'état initial du problème de planification.
     * @param actions Un ensemble d'actions disponibles pour le planificateur.
     * @param goal Le but que le planificateur cherche à atteindre.
     * @param heuristic L'heuristique utilisée pour estimer le coût restant pour atteindre l'objectif.
     */
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.heuristic = heuristic;
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
     * Méthode qui exécute l'algorithme A* pour trouver un plan optimal menant de l'état initial à l'état objectif.
     * L'algorithme utilise une combinaison de la distance parcourue et d'une estimation heuristique du coût restant.
     * 
     * @return Une liste d'actions représentant le plan trouvé, ou `null` si aucun plan n'est trouvé.
     */
	@Override
	public List<Action> plan() {

		// return beamSearch(20);
	    Map<Map<Variable, Object>, Action> plan = new HashMap<>();
	    Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
	    father.put(this.initialState, null);

	    Map<Map<Variable, Object>, Float> distanceMap = new HashMap<>();
	    distanceMap.put(this.initialState, 0.0f);

	    Map<Map<Variable, Object>, Float> valueMap = new HashMap<>();
	    valueMap.put(this.initialState, this.heuristic.estimate(this.initialState));

	    PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(
	        Comparator.comparing(valueMap::get)
	    );
	    
	    open.add(this.initialState);  

	    while (!open.isEmpty()) {
	        if (nodeCountActive) {  
	            this.exploredNodes++;
	        }

	        Map<Variable, Object> instantiation = open.poll();

	        if (this.goal.isSatisfiedBy(instantiation)) {
	            return getBFSPlan(father, plan, instantiation);
	        }

	        for (Action a : this.actions) {
	            if (a.isApplicable(instantiation)) {
	                Map<Variable, Object> next = a.successor(instantiation);

	                if (!distanceMap.containsKey(next)) {
	                    distanceMap.put(next, Float.POSITIVE_INFINITY);
	                }

	                if (distanceMap.get(next) > distanceMap.get(instantiation) + a.getCost()) {
	                    distanceMap.put(next, distanceMap.get(instantiation) + a.getCost());

	                    float newValue = distanceMap.get(next) + this.heuristic.estimate(next);
	                    valueMap.put(next, newValue);

	                    father.put(next, instantiation);
	                    plan.put(next, a);

	                    if (!open.contains(next)) {
	                        open.add(next);
	                    }
	                }
	            }
	        }
	    }

	    return null;
	}

	/**
     * Méthode pour reconstruire le chemin trouvé par A* à partir des états explorés.
     *
     * @param father une Map associant chaque état à son parent
     * @param plan une Map associant chaque état à l'action qui l'a généré
     * @param goal l'état objectif à partir duquel on reconstruit le chemin
     * @return une liste d'actions représentant le chemin trouvé
     */
	private List<Action> getBFSPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, 
	                                Map<Map<Variable, Object>, Action> plan, 
	                                Map<Variable, Object> goal) {
	    LinkedList<Action> bfs_plan = new LinkedList<>();
	    while (goal != null && goal != this.initialState) {
	        bfs_plan.add(plan.get(goal));
	        goal = father.get(goal);
	    }

	    Collections.reverse(bfs_plan);
	    return bfs_plan;
	}

	
	
	/**
     * Méthode pour obtenir le nombre de nœuds explorés pendant l'exécution de l'algorithme A*.
     *
     * @return le nombre de nœuds explorés
     */
	@Override
	public int getExploredNode() {
	    return this.exploredNodes;
	}

	
	/**
     * Retourne l'heuristique utilisée par ce planificateur.
     * 
     * @return L'heuristique utilisée.
     */
    public Heuristic getHeuristic() {
        return this.heuristic;
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



	public List<Action> beamSearch(int k) { // Ajout du paramètre k pour la limite des nœuds

    Map<Map<Variable, Object>, Action> plan = new HashMap<>();
    Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
    father.put(this.initialState, null);

    Map<Map<Variable, Object>, Float> distanceMap = new HashMap<>();
    distanceMap.put(this.initialState, 0.0f);

    Map<Map<Variable, Object>, Float> valueMap = new HashMap<>();
    valueMap.put(this.initialState, this.heuristic.estimate(this.initialState));

    // PriorityQueue pour trier les nœuds ouverts selon leur valeur f(n) = g(n) + h(n)
    PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(
        Comparator.comparing(valueMap::get)
    );
    
    open.add(this.initialState);  

    // Liste pour garder les k meilleurs nœuds au niveau actuel
    List<Map<Variable, Object>> currentLevelNodes = new ArrayList<>();
    currentLevelNodes.add(this.initialState);
    
    while (!currentLevelNodes.isEmpty()) {

        List<Map<Variable, Object>> nextLevelNodes = new ArrayList<>();
        
        // Pour chaque nœud au niveau actuel
        for (Map<Variable, Object> instantiation : currentLevelNodes) {
            if (nodeCountActive) {  
                this.exploredNodes++;
            }

            // Vérifier si l'objectif est atteint
            if (this.goal.isSatisfiedBy(instantiation)) {
                return getBFSPlan(father, plan, instantiation);
            }

            // Explorer les actions possibles à partir de ce nœud
            for (Action a : this.actions) {
                if (a.isApplicable(instantiation)) {
                    Map<Variable, Object> next = a.successor(instantiation);

                    if (!distanceMap.containsKey(next)) {
                        distanceMap.put(next, Float.POSITIVE_INFINITY);
                    }

                    if (distanceMap.get(next) > distanceMap.get(instantiation) + a.getCost()) {
                        distanceMap.put(next, distanceMap.get(instantiation) + a.getCost());

                        // Calculer la nouvelle valeur f(n) = g(n) + h(n)
                        float newValue = distanceMap.get(next) + this.heuristic.estimate(next);
                        valueMap.put(next, newValue);

                        father.put(next, instantiation);
                        plan.put(next, a);

                        // Ajouter le nœud suivant à la liste de nœuds pour le niveau suivant
                        nextLevelNodes.add(next);
                    }
                }
            }
        }

        // Trier les nœuds du niveau suivant selon f(n) et garder uniquement les k meilleurs
        nextLevelNodes.sort(Comparator.comparing(valueMap::get));

        // Garder les k meilleurs nœuds
        currentLevelNodes = nextLevelNodes.size() > k ? nextLevelNodes.subList(0, k) : nextLevelNodes;  // Modification ici pour limiter à k nœuds
    }

    // Si la liste devient vide, cela signifie qu'on n'a pas trouvé de solution
    return null;
}


	
	
}
