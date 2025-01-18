package blocksworld;

import modelling.*;
import java.util.*;
public class testConstraints {

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        BWAvecConstraints exemple1 = new  BWAvecConstraints(6, 4);
        Map<Variable, Object> etat = new HashMap<>();
        System.out.println("####################################### Test sur un monde de block avec 6 blocks et 4 piles : #######################################\n\n");

        System.out.println("############################# Le 1er test pour vérifier les constraints de base :\n\n");
     
        etat.put(exemple1.getVariablesOnB().get(0), -1);
        etat.put(exemple1.getVariablesOnB().get(1), -4);
        etat.put(exemple1.getVariablesOnB().get(2), -2);
        etat.put(exemple1.getVariablesOnB().get(3), 1);
        etat.put(exemple1.getVariablesOnB().get(4), 2);
        etat.put(exemple1.getVariablesOnB().get(5), 3);

        etat.put(exemple1.getVariablesFixedB().get(0), false);
        etat.put(exemple1.getVariablesFixedB().get(1), true);
        etat.put(exemple1.getVariablesFixedB().get(2), true);
        etat.put(exemple1.getVariablesFixedB().get(3), true);
        etat.put(exemple1.getVariablesFixedB().get(4), false);
        etat.put(exemple1.getVariablesFixedB().get(5), false);


        etat.put(exemple1.getVariablesFreeP().get(1), false);
        etat.put(exemple1.getVariablesFreeP().get(2), false);
        etat.put(exemple1.getVariablesFreeP().get(3), true);
        etat.put(exemple1.getVariablesFreeP().get(4), false);

        System.out.println(afficheEtat(exemple1.stateToListForm(etat)));

        etat.put(exemple1.getVariablesOnB().get(0), -4);

        System.out.print("## Etat 1 : Deux variables onB ont la meme valeur\n\t -> le resultat de test de satisfaction de constraints est :"); 
        if(exemple1.isAllSatisfiedBy(etat)){System.out.println(" l'etat donné respect tous les constraints de bases\n");}
        else{System.out.println(" l'etat donné ne respect pas tous les constraints de bases\n");}

        etat.put(exemple1.getVariablesOnB().get(0), -1);
        etat.put(exemple1.getVariablesFixedB().get(2), false);

        System.out.print("## Etat 2 : Une variable FixedB (Block est déplaçable) a la valeur 'Vrai' alors que le block n'est pas déplaçable\n\t -> le resultat de test de satisfaction de constraints est :"); 
        if(exemple1.isAllSatisfiedBy(etat)){System.out.println(" l'etat donné respect tous les constraints de bases\n");}
        else{System.out.println(" l'etat donné ne respect pas tous les constraints de bases\n");}

        etat.put(exemple1.getVariablesFixedB().get(2), true);
        etat.put(exemple1.getVariablesFreeP().get(2), true);


        System.out.print("## Etat 3 : Une variable FreeP (Pile est libre) a la valeur 'Vrai' alors que le block n'est pas n'est pas libre\n\t -> le resultat de test de satisfaction de constraints est :"); 
        if(exemple1.isAllSatisfiedBy(etat)){System.out.println(" l'etat donné respect tous les constraints de bases\n");}
        else{System.out.println(" l'etat donné ne respect pas tous les constraints de bases\n");}

        etat.put(exemple1.getVariablesFreeP().get(2), false);

        System.out.print("## Etat 4 : Tous les variables respectant les constraints de base\n\t -> le resultat de test de satisfaction de constraints est :"); 
        if(exemple1.isAllSatisfiedBy(etat)){System.out.println(" l'etat donné respect tous les constraints de bases\n");}
        else{System.out.println(" l'etat donné ne respect pas tous les constraints de bases\n");}

        System.out.println("\n############################ Le 2eme test pour vérifier les constraints de configuration réguliere et/ou croissant :\n\n");

        System.out.println("+++++++++++++++++++++++++++++++ Etat 1 +++++++++++++++++++++++++++++++:\n");

        etat.put(exemple1.getVariablesFreeP().get(1), true);
        etat.put(exemple1.getVariablesOnB().get(0), 3);
        etat.put(exemple1.getVariablesOnB().get(5), 4);
        etat.put(exemple1.getVariablesFixedB().get(4), true);



        System.out.println(afficheEtat(exemple1.stateToListForm(etat)));

        BWReguliereConstraints bw2=new BWReguliereConstraints(exemple1);
        BWCroissantConstraints bw3=new BWCroissantConstraints(exemple1);
        System.out.println("** Les resultats de test de satisfaction de constraints est :"); 
        if(bw2.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est regulière");}
        else{System.out.println("\t -> la configuration donnée n'est pas regulière");}
        if(bw3.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est croissante\n");}
        else{System.out.println("\t -> la configuration donnée n'est pas croissante\n");}

        System.out.println("+++++++++++++++++++++++++++++++ Etat 2 +++++++++++++++++++++++++++++++:\n");

        etat.put(exemple1.getVariablesOnB().get(0), -3);
        etat.put(exemple1.getVariablesFixedB().get(3), false);
        etat.put(exemple1.getVariablesFreeP().get(3), false);



        System.out.println(afficheEtat(exemple1.stateToListForm(etat)));

        System.out.println("** Les resultats de test de satisfaction de constraints est :"); 
        if(bw2.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est regulière");}
        else{System.out.println("\t -> la configuration donnée n'est pas regulière");}
        if(bw3.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est croissante\n");}
        else{System.out.println("\t -> la configuration donnée n'est pas croissante\n");}

        System.out.println("+++++++++++++++++++++++++++++++ Etat 3 +++++++++++++++++++++++++++++++:\n");

        etat.put(exemple1.getVariablesOnB().get(4), -2);
        etat.put(exemple1.getVariablesOnB().get(2), 4);
        etat.put(exemple1.getVariablesOnB().get(0), 2);
        etat.put(exemple1.getVariablesOnB().get(5), 3);
        etat.put(exemple1.getVariablesFixedB().get(3), true);
        etat.put(exemple1.getVariablesFreeP().get(3), true);

        System.out.println(afficheEtat(exemple1.stateToListForm(etat)));

        System.out.println("** Les resultats de test de satisfaction de constraints est :"); 
        if(bw2.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est regulière");}
        else{System.out.println("\t -> la configuration donnée n'est pas regulière");}
        if(bw3.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est croissante\n");}
        else{System.out.println("\t -> la configuration donnée n'est pas croissante\n");}

        System.out.println("+++++++++++++++++++++++++++++++ Etat 4 +++++++++++++++++++++++++++++++:\n");

        etat.put(exemple1.getVariablesOnB().get(4), 2);
        etat.put(exemple1.getVariablesOnB().get(2), -2);
        etat.put(exemple1.getVariablesOnB().get(0), -1);
        etat.put(exemple1.getVariablesFixedB().get(4), false);
        etat.put(exemple1.getVariablesFreeP().get(3), true);
        etat.put(exemple1.getVariablesFreeP().get(1), false);

        System.out.println(afficheEtat(exemple1.stateToListForm(etat)));

        System.out.println("** Les resultats de test de satisfaction de constraints est :"); 
        if(bw2.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est regulière");}
        else{System.out.println("\t -> la configuration donnée n'est pas regulière");}
        if(bw3.isAllSatisfiedBy(etat)){System.out.println("\t -> la configuration donnée est croissante\n");}
        else{System.out.println("\t -> la configuration donnée n'est pas croissante\n");}

        System.out.println("##################################################### Fin de test #####################################################\n\n");


     }
    public static String afficheEtat(List<List<Integer>> etat){

        String res="";
        for(int i=0; i<etat.size();i++){
            res=res+"Pile ["+(i+1)+"] : ";
            for(int j=0;j<etat.get(i).size();j++){
                res=res+ "["+etat.get(i).get(j)+"] ";
            }
            res=res+"\n";
        }
        return res;

    }
}