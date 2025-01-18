package modelling ;

import java.util.Set;

/**
 * Cette classe représente une variable avec un nom et un domaine de valeurs possibles.
 */
public class Variable {

	
	private String nom ; 
	private Set<Object> domaine; 
	
    /**
     * Constructeur de la classe Variable.
     *
     * @param nom Le nom de la variable.
     * @param domaine Le domaine des valeurs possibles pour cette variable, sous forme d'un ensemble (Set).
     */
    public Variable(String nom, Set<Object> domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    
    @Override
    public String toString() { return " Nom : "+this.nom;}
  
    /**
     * Retourne le nom de la variable.
     *
     * @return Le nom de la variable sous forme de chaîne de caractères.
     */
    public String getName() {
        return this.nom;
    }

    /**
     * Retourne le domaine des valeurs possibles pour cette variable.
     *
     * @return Un ensemble (Set) contenant les valeurs possibles pour cette variable.
     */
    public Set<Object> getDomain() {
        return this.domaine;
    }

    /**
     * Vérifie si cette variable est égale à un autre objet.
     * Deux variables sont considérées comme égales si elles ont le même nom.
     *
     * @param o L'objet à comparer avec cette variable.
     * @return true si l'objet spécifié est une instance de Variable avec le même nom, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null && o instanceof Variable) {
            Variable v = (Variable) o;
            return this.nom.equals(v.getName());
        }
        return false;
    }

    /**
     * Retourne le code de hachage (hash code) de cette variable, basé uniquement sur son nom.
     *
     * @return Le hash code de cette variable.
     */
    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }
}
