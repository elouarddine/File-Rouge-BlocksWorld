
package cp;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import modelling.Variable;


/**
 * Classe RandomValueHeuristic
 * Implémente une heuristique de sélection de valeurs qui retourne les valeurs d'un domaine dans un ordre
 * aléatoire en utilisant un générateur aléatoire donné. 
 */
public class RandomValueHeuristic implements ValueHeuristic{
	
	private Random rand ; 
	
	/**
     * Constructeur de RandomValueHeuristic.
     *
     * @param rand un générateur aléatoire pour mélanger les valeurs du domaine
     */
	public RandomValueHeuristic(Random rand) {
		this.rand =rand;
	}
	
	public Random getRand(){
		return this.rand;
	}
	
	/**
     * Retourne une liste des valeurs du domaine mélangées de manière aléatoire.
     *
     * @param variable la variable pour laquelle les valeurs doivent être ordonnées
     * @param domain l'ensemble des valeurs possibles pour cette variable
     * @return une liste des valeurs du domaine, mélangée aléatoirement
     */
	@Override
	public List<Object> ordering(Variable variable, Set<Object> domain) {
		List<Object> res = new LinkedList<>(domain);
		Collections.shuffle(res, this.rand);
		return res;
	}
	
	
	@Override
	public String toString() {
		return "RandomValueHeuristic {rand=" + getRand() + "}";
	}
}