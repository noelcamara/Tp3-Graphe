# Tp3-Graphe

Cet Tp consiste à l'implémenter l'algorithme de Dijkstra 

Introduction

L’Objectif de ce TP est de se servir d’un générateur de graphes pour créer de façon aléatoire un graphe 
et d’implémenter l’algorithme du plus court chemin vu en cours et par la suite le comparer à celui fournie par graph Stream à travers des tests.
Le générateur de graphe utiliser est le RamdonGenerator qui permet de créer des graphes de toute taille avec un degré moyen donné,
 de ou pas supprimer des arrêtes existantes à chaque étape, la possibilité d’orienter ou pas le graphe, 
d’ajouter des valeurs numériques au hasard sur les attributs des arrêtes ou des nœuds.

On aura alors Generator g = new RandomGenerator(15,false, false,"","cap");
Le 15 indique le degré moyen de chaque nœud
Le premier false indique que les arrêtes déjà existantes ne seront pas supprimer en à chaque étape
Le second false indique que le graph n’est pas orienté 
Le caractère vide sera attribué aux nœuds (attribue des nœuds)
Et le cap indique la capacité des arrêtes.

Dijkstra du cours

Comme expliquer dans le cours on commence par initialiser la distance des nœuds 0 pour la source et infini pour les autres. 
Après une liste qui va contenir les nœuds pour le traitement ici un HashMap pour stoker le nœud et sa capacité(distance).
 Après on effectue un parcours en largeur sur le graphe tout en tenant compte des priorités, et cette prise ne compte des priorités est gérer par la méthode priorite.

Dijkstra Graphstream
Le principe est le même pour le Dijkstra vue en cours, on commence par initialiser un graphe avec les paramètres approprier,
 après on initialise l’algorithme avec le init() et après la définition de la source avec le setSource() 
et après c’est la méthode compute qui en quelque sorte fait tout le travail vu que c’est elle qui effectue le calcul du plus cours chemin.

Tests

Comme souligner dans l’énoncé le temps d’exécution de Dijkstra varie en fonction du nombre de nœuds et d’arrêtes du graphe
 et ses nombres de nœuds et d’arrêtes varient aussi en fonction du degré moyen choisit. Et on remarque aussi que pour avoir moins de nœuds isolés il est préférable d’avoir un degré moyen grand.
Pour des graphes de tailles allant jusqu’à 1000 on prend comme degré 15 et jusqu’à 10000 on prend comme degré 30.
Après les tests réaliser et la représentation des deux courbes, on voit que pour des graphes de petites tailles qui tendent vers 1000 la courbe de Dijkstra naïve est plus intéressante que celui de Graph stream 
mais pour ceux de tailles supérieures à 1000 jusqu’à vers 2000 on voit un léger  variation de la courbe de Dijkstra naïve
 et ceux de tailles supérieures à 2000 jusqu’à 10500 on a une très grande croissance de Dijkstra naïve par rapport à Dijkstra de Graph Stream qui reste presque constante. 
 
on a les resultats des tests dans deux fihiers dijkstraN et dijkstraG qui va permettre le tarcer des courbes avec l'outil gnuplot.

Conclusion 

En conclusion, a vu que le temps de calcul de l’algorithme de Dijkstra évolue avec le nombre de nœuds et d’arrêtes du graphe
 et par analyse des deux l’algorithmes on voit que pour des graphes de tailles très importante l’algorithme de Dijkstra de Graph Stream est plus intéressant d’utilisation que le naïve implémenter.   
 
