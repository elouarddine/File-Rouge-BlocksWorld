package planning;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import modelling.Variable;


/**
 * Classe BFSPlanner qui implémente un planificateur basé sur l'algorithme de recherche en largeur (BFS).
 * Cette classe explore les états par niveau et trouve le chemin le plus court vers l'objectif.
 */
public class BFSPlanner implements Planner{

	private Map<Variable,Object> initialState ;
	private Set<Action> actions ;
	private Goal goal;
    protected int exploredNodes;
    protected boolean nodeCountActive;  // Attribut pour activer/désactiver la sonde

	
	public BFSPlanner(Map<Variable,Object> initialState , Set<Action> actions , Goal goal) {
		
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
     * Méthode `getBFSPlan` qui reconstruit le plan à partir de  l'objectif en remontant
     * la chaîne d'états parents.
     * 
     * @param father Un `Map` associant chaque état à son état parent (pour reconstruire le chemin).
     * @param plan Un `Map` associant chaque état à l'action qui a permis de l'atteindre.
     * @param goal L'état objectif à atteindre.
     * @return Une `LinkedList<Action>` contenant le plan d'actions du premier état à l'état objectif.
     */
	
	private List<Action> getBFSPlan(Map<Map<Variable, Object>,Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> goal) {
        LinkedList<Action> bfs_plan = new LinkedList<>();

        while(goal != null && goal!=this.initialState) {
            bfs_plan.add(plan.get(goal));
            goal = father.get(goal);
        }

        Collections.reverse(bfs_plan);
        return bfs_plan;
    }	
 	
	
	
	 /**
     * Méthode `plan` qui exécute l'algorithme de recherche en largeur (BFS) pour trouver un plan
     * menant de l'état initial à l'état objectif.
     * 
     * @return Une `List<Action>` contenant les actions permettant d'atteindre l'objectif, ou `null`
     * si aucun plan n'est trouvé.
     */
		
	  @Override
	    public List<Action> plan() {

		  Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
		  Map<Map<Variable, Object>, Action> plan = new HashMap<>();

	        Set<Map<Variable, Object>> closed = new HashSet<>();
	        closed.add(this.initialState);
	       
	        Queue<Map<Variable, Object>> open = new LinkedList<>();
	        open.add(this.initialState);
	        
	        father.put(this.initialState, null);

	        if(this.goal.isSatisfiedBy(this.initialState)) {
	            
	        	return new LinkedList<>();

	        }
	        	
	        while(!open.isEmpty()) {
	            
	        	 if (nodeCountActive) {  
	                 this.exploredNodes++;
	             }
	        	Map<Variable, Object> instantiation = open.remove();
	            
	        	closed.add(instantiation);
	            
	        	for(Action action : this.actions) {
	            
	        		if(action.isApplicable(instantiation)) {
	                    Map<Variable, Object> next = action.successor(instantiation);
	                
	                    if(!(closed.contains(next)) && !(open.contains(next))) {
	                        
	                    	father.put(next, instantiation);
	                        plan.put(next, action);
	                    
	                        if(this.goal.isSatisfiedBy(next))
	                            return getBFSPlan(father, plan, next);
	                        else
	                            open.add(next);
	                    }
	                }
	            }
	        }
	        return null;
	    }		
		
	   
	  
	  
	   /**
		 * Méthode pour obtenir le nombre de nœuds explorés pendant l'exécution de l'algorithme.
		 *
		 * @return le nombre de nœuds explorés
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
