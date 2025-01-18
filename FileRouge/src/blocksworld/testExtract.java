package blocksworld;

import modelling.*;
import java.util.*;

import bwgeneratordemo.*;
import datamining.*;
public class testExtract {

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n######################## Test extraction de connaissanses sur une base de données de 10_000 instances ########################\n");

        //creation d'une base de donner
        BWVariablesBoolean bw = new BWVariablesBoolean(Demo.NB_BLOCKS, Demo.NB_STACKS);
        BooleanDatabase database = new BooleanDatabase(bw.getBooleanVariables());
        int nb_transactions = 10_000;
        Random rand = new Random();
        for(int i=0; i < nb_transactions; i++) {

            List<List<Integer>> state = Demo.getState(rand);

            Set<BooleanVariable> transaction = bw.getBooleanVariablesForState(state);

            database.add(transaction);
        }
        System.out.println("\n+++++++++++ Résultat d'extraction de motif de fréquence au moins 2/3 ++++++++++++\n");


        Apriori extract1=new Apriori(database);
        float freq=(float)(2f/3f);
        System.out.println("Nombre de motifs extraits est :"+extract1.extract(freq).size()+"\n");
        System.out.println("les motifs sont :\n"+extract1.extract(freq)+"\n");


        System.out.println("\n+++++++++++ Résultat d'extraction des règles d'association de fréquence au moins 2/3 et de confiance au moins 95/100 ++++++++++++\n");

        BruteForceAssociationRuleMiner extract2=new BruteForceAssociationRuleMiner(database);
        float confiance=(float)(95f/100f);
        System.out.println("Nombre de règles d'association extraits est :"+extract2.extract(freq,confiance).size()+"\n");
        System.out.println("les règles sont :\n"+extract2.extract(freq,confiance)+"\n");

        System.out.println("\n######################## Fin de test ########################\n");
    
    }
}