----------------------------------
	TEST LA FOURCHETTE
----------------------------------

J'ai pas eu beaucoup de temps pour faire l'application, mais j'ai aissey� de faire au max.
Pour la r�cuperation des variables, "Japonais 30�", "avis", et systeme de notation
je n'est pas trouver les valeurs dans l'api,
du coup je les ai laisser en dur, sinon tout le reste est dynamique.
J'ai aissey� de respecter au mieux le design.
J'ai integr� le bouton partager.
En bonus j'ai gerer le mode offline, la gestion des favoris,
l'envoie d'email (email crypter) et l'apel au restaurant.
Je n'est pas pu tester la compatibilit� avec les tablettes car je n'est pas de tablette pour pouvoir tester
et l'�mulateur sur mon pc galere �normement, idem pour les autres type d'�cran (dev sous samsung galaxy note 4)
mais normalement sa devrai �tre bon.
Je pense avoir respecter tout se qui � �tait demand�.

Pour tester la r�cuperation de donn�e des restaurants, il suffit simplement de changer la valeur de la variable statique "restaurantID" dans
le Splashscreen.

Lors du lancement de l'application, l'appli va verifier en local si le restaurant selectionner � �tait enregistrer en base,
si oui il se lancera, sinon, il vous demandera de vous connecter � internet pour r�cuperer les informations du restaurant
avant de pouvoir ce lancer en mode offline.

En esp�rant que sa vous plaise, m�me si je n'est pas eu assez de temps pour pouvoir finir au mieux le test.