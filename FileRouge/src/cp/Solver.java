package cp;

import modelling.Variable;
import java.util.Map;


/**
 * Interface Solver représente un solveur permettant de résoudre des problèmes de satisfaction de contraintes.
 */
public interface Solver {

	/**
     * Résout le problème de satisfaction de contraintes.
     *
     * @return une solution sous la forme d'une map associant chaque variable à une valeur,
     *         ou null si aucune solution n'est trouvée.
     */
	public Map<Variable, Object> solve();
}
