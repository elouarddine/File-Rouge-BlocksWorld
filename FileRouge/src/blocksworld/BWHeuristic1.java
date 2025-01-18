
package blocksworld;

import java.util.Map;
import modelling.Variable;
import planning.*;

/**
 * Classe pour estimer le coût basé sur le nombre de blocks mal placées par rapport à l'état but.
 */
public class BWHeuristic1 extends SimpleHeuristic{

    /**
     * Constructeur prenant en paramètre l'état but.
     *
     * @param etatBut L'état final que l'on souhaite atteindre
     */
    public BWHeuristic1(Map<Variable, Object> etatBut) {
        super(etatBut);
    }

    /**
     * Estime le coût d'un plan optimal depuis l'état donné jusqu'au but.
     * 
     * @param state L'état à partir duquel l'heuristique doit être calculée. Cet état est représenté sous forme d'une Map associant les variables à leurs valeurs.
     * @return Une estimation heuristique du coût (de type float) pour atteindre l'objectif depuis l'état donné.
     */
    @Override
    public float estimate(Map<Variable, Object> etat) {
        float nombreMalPlaces = 0.0f;

        for (Map.Entry<Variable, Object> entree : etat.entrySet()) {
            Variable variable = entree.getKey();
            Object valeur = entree.getValue();

            if (variable.getName().startsWith("on") && !valeur.equals(etatBut.get(variable))) {
                nombreMalPlaces++;  
            }
        }

        return nombreMalPlaces;
    }
}