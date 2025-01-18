package modelling ;

import java.util.Set;
import java.util.Map;



/**
 * une Interface qui représente une contrainte sur un ensemble de variables.
 */
public interface Constraint {

    /**
     * Retourne l'ensemble des variables impliquées dans cette contrainte.
     *
     * @return Un ensemble (Set) de variables sur lesquelles porte la contrainte.
     */
    public Set<Variable> getScope();

    /**
     * Vérifie si la contrainte est satisfaite par une instanciation donnée.
     *
     * @param instanciation Une map associant chaque variable à sa valeur.
     * @return true si la contrainte est satisfaite, sinon false.
     * @throws IllegalArgumentException Si l'instanciation ne fournit pas une valeur pour chaque variable du scope.
     */
    public boolean isSatisfiedBy(Map<Variable, Object> instanciation) throws IllegalArgumentException ;
}
