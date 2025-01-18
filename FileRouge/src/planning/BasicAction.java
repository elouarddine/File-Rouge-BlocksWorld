package planning;

import java.util.Map;

import modelling.Variable;

import java.util.HashMap;


/**
 * Classe représentant une action basique dans un problème de planification.
 * Une action basique est définie par une précondition, un effet, et un coût.
 * Si toutes les variables de la précondition sont satisfaites (l'état courant affect toutes les variables de la precondition à la bonne valeur)
 * L'action est applicable dans l'état donné, et son application produit un nouvel état.
 */
public class BasicAction implements Action {

	private Map<Variable, Object> precondition ; 
	private Map<Variable, Object> effect ; 
	private int cost ; 
	
	
	/**
     * Constructeur de la classe BasicAction.
     * 
     * @param precondition Un map représentant les préconditions de l'action.
     * @param effect Un map représentant les effets de l'action.
     * @param cost Un entier représentant le coût de l'action.
     */
	public BasicAction(Map<Variable, Object> precondition , Map<Variable, Object> effect , int cost) {
		
		this.precondition = precondition ; 
		this.effect = effect ; 
		this.cost = cost ; 
		
	}
	
	/**
     * Retourne la précondition de l'action.
     * 
     * @return Un map représentant la précondition de l'action.
     */
	public Map<Variable, Object> getPrecondition(){return this.precondition;}
	
	 /**
     * Retourne l'effet de l'action.
     * 
     * @return Un map représentant l'effet de l'action.
     */
	public Map<Variable, Object> getEffect(){return this.effect;}

	
	/**
     * Vérifie si l'action est applicable dans l'état donné.
     * 
     * @param state Un map représentant l'état actuel.
     * @return true si l'action est applicable (c'est-à-dire si toutes les préconditions sont satisfaites),
     *         false sinon.
     */
	@Override
	public boolean isApplicable(Map<Variable, Object> state) {
		return state.entrySet().containsAll(this.precondition.entrySet());
	}

	
	 /**
     * Génère un nouvel état en appliquant l'action sur l'état donnée  
     * @param state Un map représentant l'état actuel.
     * @return Un nouvel état modifié en fonction des effets de l'action, ou null si l'action n'est pas applicable.
     */
	@Override
	public Map<Variable, Object> successor(Map<Variable, Object> state) {
		
		if(!isApplicable(state)) {
			return state ;  
		}
		
	    Map<Variable, Object> nextState = new HashMap<>(state);

        for(Variable var : this.effect.keySet()) {

        	nextState.put(var, this.effect.get(var));		
		}
	
        return nextState ;
		
	}

	
	/**
     * Retourne le coût de l'action.
     * 
     * @return Un entier représentant le coût de l'action.
     */
	@Override
	public int getCost() {
		return this.cost;
	}
	
	
	 @Override
	    public String toString() { return "Action : Representer par une precondition :"+this.precondition +","+" et un effet : " +this.effect;}
	  

	
}




