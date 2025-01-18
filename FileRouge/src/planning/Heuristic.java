package planning;

import java.util.Map;
import modelling.Variable;


/**
 * Interface pour définir une heuristique utilisée dans les algorithmes de recherche,
 * fournissant une estimation du coût restant pour atteindre un objectif depuis un état donné.
 */
public interface Heuristic {
   
	/**
     * Estime le coût d'un plan optimal depuis l'état donné jusqu'au but.
     * 
     * @param state L'état à partir duquel l'heuristique doit être calculée. Cet état est représenté sous forme d'une Map associant les variables à leurs valeurs.
     * @return Une estimation heuristique du coût (de type float) pour atteindre l'objectif depuis l'état donné.
     */
	public float estimate(Map<Variable, Object> state);

}
