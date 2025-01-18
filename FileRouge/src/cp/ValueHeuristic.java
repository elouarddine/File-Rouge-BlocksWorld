package cp;

import java.util.List;
import java.util.Set;
import modelling.Variable;

/**
 * Interface ValueHeuristic
 * Définit une heuristique de sélection pour déterminer l'ordre dans lequel les valeurs d'une variable
 * doivent être explorées dans un problème de satisfaction de contraintes (PPC).
 */
public interface ValueHeuristic {

	/**
     * Retourne une liste ordonnée des valeurs possibles pour une variable en fonction de l'heuristique implémentée.
     *
     * @param variable la variable pour laquelle l'ordre des valeurs est déterminé
     * @param domain l'ensemble des valeurs possibles pour la variable
     * @return une liste de valeurs ordonnées selon l'heuristique pour être explorées dans l'ordre
     */
	public List<Object> ordering(Variable variable, Set<Object> domain);
}