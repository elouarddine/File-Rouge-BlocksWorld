package blocksworld;

import java.util.*;


/**
 * Cette classe représente un couple d'entiers x et y
 * Cette classe va nous aider a representer un couple de deux block (ou un couple de block et pile) sous forme d'une  
 */
public class Couple {

    //x va représenter la valeur d'un block
    //y va representer la valeur d'un block ou d'une pile
    protected Integer x, y;

    /**
     * Constructeur de la classe.
     *
     * @param x la valeur de x.
     * @param y la valeur de y.
     * 
     * le constructeur va initialiser la valeur de x et y
     */
    public Couple(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne x.
     *
     * @return x.
     */
    public Integer getX() {
        return this.x;
    }

    /**
     * Retourne y
     *
     * @return y
     */
    public Integer getY() {
        return this.y;
    }

    /**
     * Retourne un boolean qui represente l'egalité entre l'objet courant et l'objet 'o' passe en parametre
     *
     * @param o l'objet concerne par la comparaison 
     * 
     * @return "true" si l'objet courant et 'o' sont egaux (ont la meme x et le meme y)
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Couple) {
            Couple c = (Couple) o;
            return this.x.equals(c.getX()) && this.y.equals(c.getY());
        }
        return false;
    }

    /**
     * Retourne une représentation textuelle de la classe.
     *
     * @return une chaîne de caractères représentant la classe.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Retourne le code de hachage (hash code) de ce Couple, basé sur son x et y.
     *
     * @return Le hash code de ce Couple.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}