package cp;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

import modelling.Constraint;
import modelling.Variable;

public class MACSolver extends AbstractSolver {

    protected ArcConsistency arcConsistency;

    /**
     * Constructeur de MACSolver initialisant la cohérence d'arc et les contraintes.
     *
     * @param variables l'ensemble des variables du problème
     * @param contraintes l'ensemble des contraintes du problème
     */
    public MACSolver(Set<Variable> variables, Set<Constraint> contraintes) {
        super(variables, contraintes);
        this.arcConsistency = new ArcConsistency(contraintes);
    }

    /**
     * Méthode principale pour résoudre le problème de satisfaction de contraintes en appliquant MAC.
     *
     * @return une solution valide si elle existe, null sinon
     */
    @Override
    public Map<Variable, Object> solve() {
        Map<Variable, Set<Object>> domaines = new HashMap<>();

        for (Variable v : getVariables())
            domaines.put(v, v.getDomain());

        return mac(new HashMap<>(), new LinkedList<>(getVariables()), domaines);
    }

    /**
     * Méthode récursive appliquant l'algorithme MAC pour résoudre le problème de satisfaction de contraintes.
     *
     * @param instanciationPartielle l'instanciation partielle des variables
     * @param variablesRestantes les variables non encore instanciées
     * @param domaines l'ensemble des domaines des variables, ajusté au fur et à mesure
     * @return une solution valide si elle existe, null sinon
     */
    private Map<Variable, Object> mac(Map<Variable, Object> instanciationPartielle, LinkedList<Variable> variablesRestantes, Map<Variable, Set<Object>> domaines) {

        if (variablesRestantes.isEmpty())
            return instanciationPartielle;

        if (!this.arcConsistency.ac1(domaines))
            return null;

        Variable variableActuelle = variablesRestantes.poll();

        for (Object valeurActuelle : domaines.get(variableActuelle)) {
            Map<Variable, Object> nouvelleInstantiation = new HashMap<>(instanciationPartielle);
            nouvelleInstantiation.put(variableActuelle, valeurActuelle);

            if (isConsistent(nouvelleInstantiation)) {
                Map<Variable, Object> solution = mac(nouvelleInstantiation, variablesRestantes, domaines);

                if (solution != null)
                    return solution;
            }
        }

        variablesRestantes.addFirst(variableActuelle);
        return null;
    }

    /**
     * Représentation textuelle de MACSolver.
     *
     * @return une chaîne de caractères représentant l'état actuel du solveur
     */
    @Override
    public String toString() {
        return "MACSolver" + super.toString();
    }
}
