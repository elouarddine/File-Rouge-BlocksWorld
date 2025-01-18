package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;

import modelling.Variable;




/**
 * Classe DFSPlanner qui implémente un planificateur basé sur l'algorithme de recherche en profondeur (DFS).
 * Cette classe explore chaque chemin en profondeur avant de revenir en arrière si le chemin ne mène pas à l'objectif.
 */
public class DFSPlanner implements Planner{

	private Map<Variable,Object> initialState ;
	private Set<Action> actions ;
	private Goal goal;
	protected int exploredNodes;
    protected boolean nodeCountActive;  

	public DFSPlanner(Map<Variable,Object> initialState , Set<Action> actions , Goal goal) {
		
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
     * Méthode 'plan' qui initialise l'exploration en profondeur à partir de l'état initial.
     * 
     * @return Une 'List<Action>' contenant les actions permettant d'atteindre l'objectif, ou 'null'
     * si aucun plan n'est trouvé.
     */
	@Override
	public List<Action> plan() {
		 
        this.exploredNodes = 0;
		Map<Variable,Object> instantiation = this.initialState;
		Stack<Action> plan = new Stack<Action>();
        Set<Map<Variable,Object>> closed = new HashSet<Map<Variable,Object>>();
		closed.add(initialState);
		return plan(instantiation , plan , closed);
		
	}		
 	
	
	
	/**
     * Méthode 'plan' qui exécute l'algorithme de recherche en profondeur (DFS) pour trouver un plan
     * menant de l'état initial à l'état objectif.
     * 
     * @param instantiation L'état actuel de l'exploration.
     * @param plan Une pile d'actions représentant le plan actuel.
     * @param closed Un ensemble des états déjà explorés pour éviter les cycles.
     * @return Une 'List<Action>' contenant les actions menant à l'objectif ou 'null' si aucune solution n'est trouvée.
     */
		
	    private List<Action> plan(Map<Variable, Object> instantiation, Stack<Action> plan, Set<Map<Variable, Object>> closed) {
	        if (nodeCountActive) {
	            this.exploredNodes++;
	        }
	    	if(this.goal.isSatisfiedBy(instantiation)) {
	 			
	 			return plan;
	 		}
	         for(Action action : actions){
	 			
	         	if(action.isApplicable(instantiation)) {
	     			
	         		Map<Variable,Object> nextState = action.successor(instantiation);
	           	   
	         		if(!closed.contains(nextState)) {
	           	    	
	         			plan.push(action);
	           	    	
	         			closed.add(nextState);
	           	    	
	         			List<Action> subplan = plan(nextState, plan, closed);
	           	    	
	         			if(subplan != null)
	                    
	         				return subplan;
	                    
	         			else
	                    
	         				plan.pop();
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
