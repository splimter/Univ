import os
import helpers as H

# Cette function va afficher le menu
def menu():
    print("1- Ajouter un Etudiant")
    print("2- Ajouter un Matieres")
    print("3- Ajouter un Note")
    print("4- afficher la Moyenne d'un Etudiant")
    print("5- Modifie le numIns d'un Etudiant")
    print("6- suprimer une Note")
    print("7- quiter")


# Une classs qui definie les propreiter d'Etudiant
class Etudiant:
    numInsc = 0
    nom = ""
    prenom = ""
    group = 0

    # Constructeur
    def __init__(self, numInsc, nom, prenom, group):
        self.numInsc = numInsc
        self.nom = nom
        self.prenom = prenom
        self.group = group


# Une classs qui definie les propreiter d'Matieres
class Matieres:
    codeMat = ""
    libelle = ""
    coef = 0

    # Constructeur
    def __init__(self, codeMat, libelle, coef):
        self.codeMat = codeMat
        self.libelle = libelle
        self.coef = coef


# Une classs qui definie les propreiter d'Note
class Note:
    numInsc = 0
    codeMat = ""
    note = 0.0

    # Constructeur
    def __init__(self, numInsc, codeMat, note):
        self.numInsc = numInsc
        self.codeMat = codeMat
        self.note = note


def ajoutEtudiant(Etudiant):

    # Virifie si le ficher existe
    if not os.path.isfile("Etudiants.dat"):
        with open("Etudiants.dat", 'w') as file:
            file.write("Gestion des Etudiants\n")
            file.write("----------------------\n")
    # Ecrire dans le ficher les donner importer depui l'objet
    with open("Etudiants.dat", 'a+', encoding='utf-8') as file:
        file.tell()
        file.write("\n")
        file.write(str(Etudiant.numInsc) + "\n")
        file.write(Etudiant.nom + "\n")
        file.write(Etudiant.prenom + "\n")
        file.write(str(Etudiant.group) + "\n")
        file.write("------------------------")


def ajoutMatiere(Matieres):

    # Virifie si le ficher existe
    if not os.path.isfile("Matieres.dat"):
        with open("Matieres.dat", 'w') as file:
            file.write("Gestion des Matieres\n")
            file.write("---------------\n")
    # Ecrire dans le ficher les donner importer depui l'objet
    with open("Matieres.dat", 'a+', encoding='utf-8') as file:
        file.tell()
        file.write("\n")
        file.write(Matieres.libelle + "\n")
        file.write(str(Matieres.codeMat) + "\n")
        file.write(str(Matieres.coef) + "\n")
        file.write("------------------------")


def ajoutNote(Note):

    # Virifie si le ficher existe
    if not os.path.isfile("Notes.dat"):
        with open("Notes.dat", 'w') as file:
            file.write("Gestion des Notes\n")
            file.write("---------------\n")
    # Ecrire dans le ficher les donner importer depui l'objet
    with open("Notes.dat", 'a+', encoding='utf-8') as file:
        file.tell()
        file.write("\n")
        file.write(str(Note.numInsc) + "\n")
        file.write(Note.codeMat + "\n")
        file.write(str(Note.note) + "\n")
        file.write("------------------------")


def MoyenneEtudiant(NumInsc):
    if os.path.isfile("Notes.dat"):
        n = H.fileTolist("Notes.dat")
    else:
        return print("Pas de fichier Notes")

    if os.path.isfile("Matieres.dat"):
        m = H.fileTolist("Matieres.dat")

    else:
        return print("Pas de fichier Matieres")

    n = (H.focusNote(NumInsc, n))
    if not n:
        return print("Etudiant indisponible")
    k = []
    for i in range(1, len(n), 3):
        if n[i] not in k:
            cm = m.copy()
            k.append(H.focusMat(n[i], cm))

    m = []
    for i in k:
        for j in i:
            m.append(j)

    if not m:
        return print("Matieres indisponible")

    H.extraTrimer(m)
    H.extraTrimer(n)

    s = 0
    for i in range(len(m)):
        s += float(m[i]) * float(n[i])

    return print("la moyen est: ", s / sum(m))


def ModifEtudiant(NumInsc, newId):
    # Virifie si le ficher existe
    if os.path.isfile("Notes.dat"):
        n = H.fileTolist("Notes.dat")
    else:
        return print("Pas de fichier Notes")

    if os.path.isfile("Etudiants.dat"):
        e = H.fileTolist("Etudiants.dat")
    else:
        return print("Pas de fichier Etudiants")

    if str(NumInsc) not in e:
        return print("Etudiants indisponible")
    if str(NumInsc) not in n:
        return

    print(n)
    print(e)

    note, std = [], []

    os.remove("Etudiants.dat")
    i = 0
    while i < len(e):
        if e[i] == str(NumInsc):
            e[i] = str(newId)
        ajoutEtudiant(Etudiant(e[i], e[i+1], e[i+2], e[i+3]))
        i += 4

    os.remove("Notes.dat")
    i = 0
    while i < len(n):
        if n[i] == str(NumInsc):
            n[i] = str(newId)
        ajoutNote(Note(n[i], n[i+1], n[i+2]))
        i += 3


def SupprimerNote(NumInsc, CodeMat):
    if os.path.isfile("Notes.dat"):
        n = H.fileTolist("Notes.dat")
    else:
        return print("Pas de fichier Notes")

    os.remove("Notes.dat")
    i = 0
    while i < len(n):
        if n[i] == str(NumInsc) and n[i+1] == str(CodeMat):
            i += 3
            continue
        ajoutNote(Note(n[i], n[i+1], n[i+2]))
        i += 3


while True:
    menu()
    choice = int(input("Option: "))
    if choice == 1:
        obj = Etudiant(input("Numero d'inscription: "), input("Nom: "), input("Prenom: "), input("Group: "))
        ajoutEtudiant(obj)
    elif choice == 2:
        obj = Matieres(input("Code du matieres: "), input("Libelle: "), input("Coef: "))
        ajoutMatiere(obj)
    elif choice == 3:
        obj = Note(input("Numero d'inscription: "), input("Code du matieres: "), input("Note: "))
        ajoutNote(obj)
    elif choice == 4:
        MoyenneEtudiant(input("donner NumInsc d'Etudiant"))
    elif choice == 5:
        ModifEtudiant(input("donner NumInsc d'Etudiant"), input("donner le nouveau NumInsc"))
    elif choice == 6:
        SupprimerNote(input("donner NumInsc d'Etudiant"), input("donner le Code du matiere"))
    elif choice == 7:
        os._exit(1)
    else:
        print("Donner une bonne option")

