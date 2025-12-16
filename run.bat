@echo off
REM =========================================================
REM CONFIGURATION
REM =========================================================

set "COMPIL_LIST=compil.list"
set "CLASS_DIR=class"
REM Nom de la classe principale avec son package
set "MAIN_CLASS=Main.Controller" 

REM =========================================================
REM 1. NETTOYAGE
REM =========================================================
echo Nettoyage du repertoire de classes...
if exist "%CLASS_DIR%" (
    rd /s /q "%CLASS_DIR%"
)

REM =========================================================
REM 2. COMPILATION
REM =========================================================

echo Creation du repertoire de classes : %CLASS_DIR%
mkdir "%CLASS_DIR%"

echo Compilation des fichiers listes dans %COMPIL_LIST%...

REM **** MODIFICATION CRITIQUE ****
REM -sourcepath src : Indique que 'src' est le dossier racine de TOUS les packages (Main, Model, View).
javac -d "%CLASS_DIR%" -sourcepath src "@%COMPIL_LIST%"

if errorlevel 1 goto :COMPILATION_ECHEC

echo Compilation reussie ! Les fichiers .class sont dans le repertoire %CLASS_DIR%.

REM =========================================================
REM 3. LANCEMENT
REM =========================================================

echo.
echo Lancement de l'application %MAIN_CLASS%...
REM -cp %CLASS_DIR% : ajoute le repertoire 'class' au classpath
java -cp "%CLASS_DIR%" "%MAIN_CLASS%"

goto :FIN

:COMPILATION_ECHEC
echo Echec de la compilation. Veuillez verifier les erreurs ci-dessus.
exit /b 1

:FIN
exit /b 0