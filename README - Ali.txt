----------------------------------
	TEST LA FOURCHETTE
----------------------------------

J'ai pas eu beaucoup de temps pour faire l'application, mais j'ai aisseyé de faire au max.
Pour la récuperation des variables, "Japonais 30€", "avis", et systeme de notation
je n'est pas trouver les valeurs dans l'api,
du coup je les ai laisser en dur, sinon tout le reste est dynamique.
J'ai aisseyé de respecter au mieux le design.
J'ai integré le bouton partager.
En bonus j'ai gerer le mode offline, la gestion des favoris,
l'envoie d'email (email crypter) et l'apel au restaurant.
Je n'est pas pu tester la compatibilité avec les tablettes car je n'est pas de tablette pour pouvoir tester
et l'émulateur sur mon pc galere énormement, idem pour les autres type d'écran (dev sous samsung galaxy note 4)
mais normalement sa devrai être bon.
Je pense avoir respecter tout se qui à était demandé.

Pour tester la récuperation de donnée des restaurants, il suffit simplement de changer la valeur de la variable statique "restaurantID" dans
le Splashscreen.

Lors du lancement de l'application, l'appli va verifier en local si le restaurant selectionner à était enregistrer en base,
si oui il se lancera, sinon, il vous demandera de vous connecter à internet pour récuperer les informations du restaurant
avant de pouvoir ce lancer en mode offline.

En espérant que sa vous plaise, même si je n'est pas eu assez de temps pour pouvoir finir au mieux le test.