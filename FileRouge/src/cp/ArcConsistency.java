package cp;

import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;

import modelling.Constraint;
import modelling.Variable;

/**
 * Classe ArcConsistency implémente des méthodes pour assurer la cohérence de noeud et la cohérence d'arc dans un problème
 * de satisfaction de contraintes (CSP). Cette classe se concentre sur les contraintes unaires et binaires.
 */

public class ArcConsistency {
	
	private Set<Constraint> constraintes ; 
	
	public ArcConsistency(Set<Constraint> constraintes) throws IllegalArgumentException{
		
		this.constraintes = constraintes ; 
		
		for(Constraint constraint : this.constraintes){
			if (constraint.getScope().size()>2) {
				throw new IllegalArgumentException("Ni unaire ni binaire");
			}	
		}
		
	}
	
	/**
	 * Assure la cohérence de noeud pour chaque domaine de variable en supprimant les valeurs non viables.
	 * La méthode vérifie toutes les contraintes unaires pour chaque variable.
	 *
	 * @param domains un Map associant chaque variable à son domaine (ensemble de valeurs possibles)
	 * @return true si tous les domaines restent non vides, false si un domaine est vide
	 */

	public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domains) {
	    
        boolean nonVide = true;

		for (Variable var : domains.keySet()) {
	        Set<Object> valeursASupprimer = new HashSet<>();

	        for (Object val : domains.get(var)) {
	            Map<Variable, Object> instanciation = new HashMap<>();
	            instanciation.put(var, val);

	            for (Constraint constraint : this.constraintes) {
	                Set<Variable> scope = constraint.getScope();

	                if (scope.size() == 1 && scope.contains(var) && !constraint.isSatisfiedBy(instanciation)) {
	                    valeursASupprimer.add(val);
	                }
	            }
	        }

	        domains.get(var).removeAll(valeursASupprimer);

	        if (domains.get(var).isEmpty()) {
	        	nonVide = false;
	        }
	    }
	    return nonVide;
	}

	
	
	
	/**
	 * Applique l'algorithme Revise pour rendre cohérent l'arc entre deux variables.
	 * Supprime, dans le domaine de la première variable, les valeurs qui ne sont pas supportées
	 * par au moins une valeur du domaine de la deuxième variable.
	 *
	 * @param var1 la première variable pour laquelle les valeurs non viables sont vérifiées
	 * @param domaine1 l'ensemble des valeurs possibles pour var1, à réviser
	 * @param var2 la deuxième variable utilisée pour vérifier le support de var1
	 * @param domaine2 l'ensemble des valeurs possibles pour var2
	 * @return true si au moins une valeur a été supprimée de domaine1, false sinon
	 */

	public boolean revise(Variable var1, Set<Object> domaine1, Variable var2, Set<Object> domaine2) {
	    
		boolean delete = false;
	    Set<Object> aSupprimer = new HashSet<>(); 
	    
	    for (Object v1 : domaine1) {
	        boolean viable = false;
	        
	        for (Object v2 : domaine2) {
	            boolean toutSatisfait = true;
	            
	            for (Constraint constraint : this.constraintes) {
	                Set<Variable> scope = constraint.getScope();
	                
	                if (scope.size() == 2) {
	                    Iterator<Variable> it = scope.iterator();
	                    Variable variable1 = it.next();
	                    Variable variable2 = it.next();
	                    
	                    if ((variable1.equals(var1) && variable2.equals(var2)) || (variable1.equals(var2) && variable2.equals(var1))) {
	                        Map<Variable, Object> instanciation = new HashMap<>();
	                        instanciation.put(var1, v1);
	                        instanciation.put(var2, v2);
	                        
	                        if (!constraint.isSatisfiedBy(instanciation)) {
	                            toutSatisfait = false;
	                            break;
	                        }
	                    }
	                }
	            }
	            
	            if (toutSatisfait) {
	                viable = true;
	                break;
	            }
	        }
	        
	        if (!viable) {
	        	aSupprimer.add(v1); 
	        	delete = true;
	        }
	    }
	    
	    domaine1.removeAll(aSupprimer);
	    return delete;
	}

	
	
	/**
	 * Applique l'algorithme ac1 pour rendre cohérent l'ensemble des arcs d'un CSP.
	 * La méthode utilise Revise pour filtrer les domaines jusqu'à ce qu'aucune réduction
	 * supplémentaire ne soit nécessaire. Elle vérifie que tous les domaines restent non vides.
	 *
	 * @param domains un Map associant chaque variable à son ensemble de valeurs possibles
	 * @return true si tous les domaines sont arc-cohérents et non vides, false si un domaine est vidé
	 */

	public boolean ac1(Map<Variable, Set<Object>> domains) {
		
		
		boolean change = true;
		Set<Variable> variables = domains.keySet();
		
		if(!enforceNodeConsistency(domains)) {return false;}
		
		while(change) {
			change = false;
			for (Variable xi : variables) {
				for (Variable xj : variables) { 
					if(!(xi.equals(xj)) && (revise(xi, domains.get(xi), xj, domains.get(xj) ))) {
						change = true;
					}
				}
			}
		}
		
		
		for(Variable var: variables) {
			if(domains.get(var).isEmpty())
				return false;
		}
		
		return true;
	}
	
	
	/**
	 * Retourne une représentation textuelle de la classe ArcConsistency, incluant les contraintes.
	 *
	 * @return une chaîne de caractères représentant l'état actuel des contraintes dans cette instance
	 */

	@Override
	public String toString() {
		return "ArcConsistency{" + "constraints=" + this.constraintes + '}';
	}
	
	
}


