package modelling ;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;



/**
 * Représente une contrainte de différence entre deux variables, de la forme v1 != v2.
 *
 */
public class DifferenceConstraint implements Constraint {


    protected Variable v1;
    protected Variable v2;

    
    
    /**
     * Constructeur de la classe DifferenceConstraint
     *
     * @param v1 La première variable impliquée dans la contrainte.
     * @param v2 La seconde variable impliquée dans la contrainte.
     */
    public DifferenceConstraint(Variable v1,Variable v2){
    
       this.v1=v1;
       this.v2=v2;
    
    }
    
    
    /**
     * Retourne la première variable de la contrainte de différence.
     *
     * @return La première variable v1.
     */
    public Variable getV1(){return this.v1;}
   
    /**
     * Retourne la seconde variable de la contrainte de différence.
     *
     * @return La seconde variable v2.
     */
    public Variable getV2(){return this.v2;}
    

    /**
     * Retourne l'ensemble des variables impliquées dans cette contrainte (v1 et v2).
     *
     * @return Un ensemble de deux variables (v1 et v2).
     */
    @Override  
    public Set<Variable> getScope(){ 
         return new HashSet<Variable>(){private static final long serialVersionUID = 1L;
         {add(v1); add(v2);}};
      }
   
     
     /**
      * Vérifie si la contrainte de différence est satisfaite par une instanciation donnée.
      *
      * @param instanciation Une map associant chaque variable à sa valeur.
      * @return true si v1 et v2 ont des valeurs différentes, sinon false.
      * @throws IllegalArgumentException Si l'instanciation ne fournit pas une valeur pour au moins chaque variable du scope.
      */
     @Override 
     public boolean isSatisfiedBy(Map<Variable,Object> instanciation) throws IllegalArgumentException{ 
           
    	 for(Variable var : getScope()) { 
        	   
               if(!(instanciation.containsKey(var)) || !(instanciation.get(var) != null)) {
                   throw new IllegalArgumentException("y'a au moins une variable du scope de la contrainte qui n'as pas de valeur") ;
           
               }
    	 }


   	     return instanciation.get(this.v1).equals((instanciation.get(this.v2)))?false:true;
     
     }

	 

}
