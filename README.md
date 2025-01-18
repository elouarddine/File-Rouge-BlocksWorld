# File-Rouge-BlocksWorld

Ce projet est une implémentation du monde des blocs (**BlocksWorld**), un environnement classique utilisé en intelligence artificielle pour expérimenter des algorithmes de planification, de satisfaction de contraintes et d’extraction de connaissances. Réalisé dans le cadre du cours **"Aide à la Décision et Intelligence Artificielle"** à l'Université de Caen Normandie.

---

## Description du projet

Le projet explore et met en œuvre différents aspects fondamentaux de l'intelligence artificielle :
1. **Résolution de contraintes (CSP)** :
   - Implémentation de contraintes régulières (écarts constants dans les piles) et croissantes (un bloc ne peut être placé que sur un bloc de numéro inférieur).
   - Utilisation de solveurs comme la cohérence d'arc, le backtracking, et MAC.

2. **Planification** :
   - Résolution de problèmes en utilisant les algorithmes **BFS**, **DFS**, et **A***.
   - Deux heuristiques personnalisées sont utilisées pour optimiser les résultats.

3. **Extraction de connaissances** :
   - Extraction de motifs fréquents avec l'algorithme **Apriori**.
   - Génération de règles d'association pour analyser les données.

4. **Visualisation** :
   - Les configurations et plans générés peuvent être visualisés graphiquement grâce à des bibliothèques tierces.

---

## Fonctionnalités principales

### **Contraintes (CSP)**
- Modélisation des contraintes régulières et croissantes.
- Validation des configurations avec des solveurs performants.

### **Planification**
- Génération de plans avec :
  - **BFS** (Breadth-First Search).
  - **DFS** (Depth-First Search).
  - **A** (Recherche heuristique avec deux heuristiques personnalisées).

### **Extraction de connaissances**
- Utilisation de l'algorithme **Apriori** pour extraire des motifs fréquents.
- Génération de règles d'association à partir des données :
  - Fréquence minimale : ≥ 2/3
  - Confiance minimale : ≥ 95%.

### **Visualisation**
- Résultats affichés en console et visualisation graphique des configurations grâce à la bibliothèque `blocksworld.jar`.

---

## Pré-requis

1. **Java Development Kit (JDK)** version 8 ou supérieure.
2. Placez les fichiers `.jar` nécessaires dans le répertoire `lib` :
   - `bwgenerator.jar`
   - `blocksworld.jar`

---

## Instructions d'exécution

1. **Configurer les droits d'exécution :**
   ```bash
   chmod +x script.sh
   ```

2. **Lancer le script principal :**
   ```bash
   ./script.sh
   ```

3. **Menu interactif :**
   - Tests des contraintes (régulières et croissantes).
   - Planification (BFS, DFS, A* avec heuristiques).
   - Résolution de CSP.
   - Extraction de connaissances.

---

## Structure du projet

- **`modelling`** : Représentation des variables et des contraintes.
- **`planning`** : Algorithmes de planification.
- **`cp`** : Résolution de problèmes de satisfaction de contraintes.
- **`datamining`** : Extraction de motifs fréquents et de règles d'association.
- **`scripts`** : Automatisation des tests et exécutions.

---

## Résultats et Tests

1. **Contraintes** :
   - Validation des configurations régulières et croissantes.

2. **Planificateurs** :
   - Génération de plans pour des états cibles, avec affichage des performances (temps, nœuds explorés).

3. **Extraction de connaissances** :
   - Extraction et génération de motifs et règles d'association à partir de bases de données simulées.

---

## Remarques
Assurez-vous que tous les fichiers nécessaires (notamment `.jar`) sont correctement placés dans le répertoire `lib`. Suivez les instructions dans le script `script.sh` pour exécuter les différentes parties du projet.

