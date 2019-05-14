Voici les sources du project rmi/socket

Ceci est un exemple d'utilisation de l'implementation des classes RMI de Java.

Le but de cet exemple est de créer une communication entre un client et un serveur par tchat.

Dans le package "src/main/java/com/vae/rmisocket", la classe Chat est utilisée par le client et par le serveur pour communiquer entre les deux parties.

Le fichier source "ChatClient.java" lance le client ( on peut préciser le host en argument de la ligne de commande ).
Le fichier source "ChatServeur.java" lance le serveur ( on peut préciser le host et le port en argument de la ligne de commande ).
Ce fichier "ChatClient.java" doit être lancer avant la classe "ChatClient.java".
Le fichier source est l'interface créée pour les besoins de l'implémentation des RMI.
