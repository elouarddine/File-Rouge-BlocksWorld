#!/bin/bash

# Nettoyer la console
clear

# Compilation des fichiers Java
echo "Compilation des fichiers Java en cours..."
javac -cp .:../lib/blocksworld.jar:../lib/bwgenerator.jar -d ../build/ ../src/datamining/*.java ../src/modelling/*.java ../src/blocksworld/*.java ../src/planning/*.java ../src/cp/*.java

# Vérification de la réussite de la compilation
if [ $? -eq 0 ]; then
    echo "Compilation réussie."
else
    echo "Erreur lors de la compilation. Vérifiez votre code."
    exit 1
fi

# Boucle principale du menu
while true; do
    # Nettoyer la console avant d'afficher le menu
    clear

    # Afficher un grand titre
    echo "#########################################"
    echo "##                                     ##"
    echo "##          MONDE DE BLOCKS            ##"
    echo "##                                     ##"
    echo "#########################################"
    echo ""

    # Menu pour l'utilisateur
    echo "Choisissez une option :"
    echo "1) Test constraints"
    echo "2) Test plannificateurs"
    echo "3) Test CSP"
    echo "4) Test Extraction des connaissances"
    echo "5) Quitter"
    echo ""

    # Lire le choix de l'utilisateur
    read -p "Votre choix : " choix

    case $choix in
        1)
            clear
            echo "Exécution du test de constraints..."
            java -cp ../build:../lib/blocksworld.jar:../lib/bwgenerator.jar blocksworld.testConstraints
            if [ $? -eq 0 ]; then
                echo "Exécution de Test constraints réussie."
            else
                echo "Erreur lors de l'exécution de Test constraints."
            fi
            ;;
        2)
            clear
            echo "Exécution du test des plannificateurs..."
            java -cp ../build:../lib/blocksworld.jar:../lib/bwgenerator.jar blocksworld.testPlanning
            if [ $? -eq 0 ]; then
                echo "Exécution de Test plannificateurs réussie."
            else
                echo "Erreur lors de l'exécution de Test plannificateurs."
            fi
            ;;
        3)
            clear
            echo "Exécution du test CSP..."
            java -cp ../build:../lib/blocksworld.jar:../lib/bwgenerator.jar blocksworld.testCSP
            if [ $? -eq 0 ]; then
                echo "Exécution de Test CSP réussie."
            else
                echo "Erreur lors de l'exécution de Test CSP."
            fi
            ;;
        4)
            clear
            echo "Exécution du test d'extraction des connaissances..."
            java -cp ../build:../lib/blocksworld.jar:../lib/bwgenerator.jar blocksworld.testExtract
            if [ $? -eq 0 ]; then
                echo "Exécution de Test Extraction des connaissances réussie."
            else
                echo "Erreur lors de l'exécution de Test Extraction des connaissances."
            fi
            ;;
        5)
            clear
            echo "Au revoir !"
            break
            ;;
        *)
            clear
            echo "Choix invalide, veuillez réessayer."
            ;;
    esac

    # Pause avant de revenir au menu
    echo ""
    read -p "Appuyez sur Entrée pour revenir au menu..."
done
