package modelling ;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;



/**
 * Représente une contrainte unitaire de la forme (v Appartient à S) , où v est une variable et S un sous-ensemble de son domaine.
 *
 */

public class UnaryConstraint implements Constraint {
   
   private Variable v;
   private Set<Object> s;
   
   
   
   /**
    * Constructeur de la classe UnaryConstraint
    *
    * @param v La variable concernée par la contrainte unitaire.
    * @param s Le sous-ensemble du domaine de v pour lequel la contrainte est testée.
    */

   public UnaryConstraint(Variable v,Set<Object> s)
     {   
         this.v=v;
         this.s=s;
     
     }
   
   
   /**
    * Retourne la variable impliquée dans la contrainte unitaire.
    *
    * @return La variable v.
    */
   public Variable getV(){return this.v;}
   
   /**
    * Retourne le sous-ensemble du domaine de la variable v (S).
    *
    * @return Un ensemble d'objets représentant S.
    */
   public Set<Object> getS(){return this.s;}
   
   
   /**
    * Retourne l'ensemble des variables impliquées dans cette contrainte (ici, uniquement v).
    *
    * @return Un ensemble contenant la variable v.
    */
   @Override
   public Set<Variable> getScope(){ 
         return new HashSet<Variable>(){private static final long serialVersionUID = 1L;
		{ add(v); }};
      }
   
   
   /**
    * Vérifie si la contrainte unitaire est satisfaite par une instanciation donnée.
    *
    * @param instanciation Une map associant la variable à sa valeur.
    * @return true si la valeur de la variable v appartient à S, sinon false.
    * @throws IllegalArgumentException Si l'instanciation ne fournit pas une valeur pour la variable du scope.
    */
   @Override
    public boolean isSatisfiedBy(Map<Variable,Object> instanciation)  throws IllegalArgumentException {  
      for(Variable var : getScope()) { 
            
            if(!(instanciation.containsKey(var)) || !(instanciation.get(var) != null)) {
                throw new IllegalArgumentException("y'a au moins une variable du scope de la contrainte qui n'as pas de valeur") ;
        
            }
      }
      return s.contains(instanciation.get(v));
    }

   
   
   }
