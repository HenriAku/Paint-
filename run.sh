#!/bin/bash

# =========================================================
# CONFIGURATION
# =========================================================

# Nom du fichier listant les sources
COMPIL_LIST="compil.list"

# Dossier de sortie des classes compilées
CLASS_DIR="class"

# Classe principale de l'application (celle qui contient le main(String[] args))
# On suppose qu'elle est dans le package par défaut ou dans le package Controller.
# Si vous créez un Main.java dans src/, vous devriez mettre simplement 'Main'
MAIN_CLASS="Controller" # Modifier si votre classe principale s'appelle Main ou autre.

# =========================================================
# 1. NETTOYAGE
# =========================================================

echo "Nettoyage du répertoire de classes..."
rm -rf "$CLASS_DIR"

# =========================================================
# 2. COMPILATION
# =========================================================

echo "Création du répertoire de classes : $CLASS_DIR"
mkdir -p "$CLASS_DIR"

echo "Compilation des fichiers listés dans $COMPIL_LIST..."
# -d $CLASS_DIR : place les .class dans le répertoire spécifié
# @$COMPIL_LIST : lit la liste des fichiers à compiler dans le fichier
javac -d "$CLASS_DIR" "@$COMPIL_LIST"

# Vérification du succès de la compilation
if [ $? -eq 0 ]; then
    echo "Compilation réussie ! Les fichiers .class sont dans le répertoire $CLASS_DIR."

    # =========================================================
    # 3. LANCEMENT
    # =========================================================
    
    echo " "
    echo "Lancement de l'application $MAIN_CLASS..."
    # -cp $CLASS_DIR : ajoute le répertoire 'class' au classpath pour que Java trouve les classes
    java -cp "$CLASS_DIR" "$MAIN_CLASS"

else
    echo "Échec de la compilation. Veuillez vérifier les erreurs ci-dessus."
    exit 1
fi