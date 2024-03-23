/*
 * ========================
 * TEA Reseaux Transmission - L2 Informatique, 28/05/2022.
 * 
 * ========================
 */

Le projet contient deux exécutables java (.jar).
	-reseauxJeu.jar
	-serveurJeu.jar

1) Dans un terminal, se placer dans le dossier de serveurJeu.jar et exécuter la commande:

	java -jar serveurJeu.jar

   Le serveur se lance. Ne pas fermer le terminal, cela fermerait le serveur.



2) Dans un autre terminal, se placer dans le dossier de reseauxJeu.jar.
   Il y a 4 paramètres à préciser pour lancer le jeu: le numéro du joueur, l'équipe (1 ou 2), la position X et la position Y.
   syntaxe: 

	java -jar reseauxJeu.jar <int number>, <int equipe>, <int x>, <int y>

   Le client se connecte au serveur.
