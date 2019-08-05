import os.path
import helpers as H

# Cette function va afficher le menu
def menu():
    print("1- Ajouter un vol")
    print("2- afficher les vol")
    print("3- suprimer un vol")
    print("4- tri des vol")
    print("5- quiter")


# Une classs qui definie les propreiter d'Horaire
class Horaire:
    num_vol = 0
    villeDeprat = ""
    villeArrive = ""
    heureDepart = ""
    heureArrive = ""

    # Constructeur
    def __init__(self, num_vol, villeDeprat, villeArrive, heureDepart, heureArrive):
        self.num_vol = num_vol
        self.villeDeprat = villeDeprat
        self.villeArrive = villeArrive
        self.heureDepart = heureDepart
        self.heureArrive = heureArrive


# Cette function va sauvgarder les information sur un fichier
def ajoutVol(Horaire):

    # Virifie si le ficher existe
    if not os.path.isfile("ficher vol.txt"):
        l = 0
        with open("ficher vol.txt", 'w') as file:
            file.write("Gestion des Vol\n")
            file.write("---------------\n")
    # Ecrire dans le ficher les donner importer depui l'objet
    with open("ficher vol.txt", 'a+', encoding='utf-8') as file:
        file.tell()
        file.write("\n")
        file.write(str(Horaire.num_vol) + "\n")
        file.write(Horaire.villeDeprat + "\n")
        file.write(Horaire.villeArrive + "\n")
        file.write(Horaire.heureDepart + "\n")
        file.write(Horaire.heureArrive + "\n")
        file.write("------------------------")


# Cette function va afficher les donner sauvgarder dans le fichier
def afficheVol():
    # Virifie si le ficher existe
    if os.path.isfile("ficher vol.txt"):
        with open("ficher vol.txt", 'r', encoding='utf-8') as file:
            # ignore les 3 premier lign
            for i in range(3):
                file.readline()
                
            # Afficher les ligne
            while True:
                print("Id: ", file.readline(), end='')
                print("Ville Deprat: ", file.readline(), end='')
                print("Ville Arrive: ", file.readline(), end='')
                print("Heure Depart: ", file.readline(), end='')
                print("Heure Arrive: ", file.readline(), end='')
                print("-------------------")
                if file.readline() == '':
                    break
    else:
        print("Pas de vol pour instant\n")


# Cette fucntion va suprimer un volle
def suprimmerVol():
    # Virifie si le ficher existe
    if os.path.isfile("ficher vol.txt"):
        with open("ficher vol.txt", 'r+', encoding='utf-8') as file:
            # assigner la varibale t tout le contenue du ficher
            t = file.read()
            # Del est le numero du volle qui va etre suprimer
            Del = str(input("Donner le numero du volle"))
            # Mettre le curseur en tete du ficher
            file.seek(0)
            k = 0

            # re-ecrire lign par lign en ignorant la lign qu'elle a la valeur
            # que en veut suprimer avec ces 5 lign sucsessive
            for line in t.split('\n'):
                if line != Del and k == 0:
                    file.write(line + '\n')
                else:
                    if k == 0:
                        k = 5
                    else:
                        if k > 0:
                            k -= 1
            file.truncate()
    else:
        print("Pas de vol pour instant\n")


# Cette fucntion va tri les volle
def triVol():
    # Virifie si le ficher existe
    if os.path.isfile("ficher vol.txt"):
            x = H.fileTolist("ficher vol.txt")

            # assigner chaque attribue a un tableau
            id, vd, va, hd, ha = [], [], [], [], [],
            for i in range(len(x)):
                if i % 5 == 0:
                    id.append(x[i])
                elif i % 5 == 1:
                    vd.append(x[i])
                elif i % 5 == 2:
                    va.append(x[i])
                elif i % 5 == 3:
                    hd.append(x[i])
                elif i % 5 == 4:
                    ha.append(x[i])

            ListObj = []

            # cree une liste d'objer ces valeur sont importer depuis la phase precedant
            for i in range(len(id)):
                ListObj.append(Horaire(int(id[i]),vd[i], va[i],hd[i], ha[i]))

            # tri les liste d'objer
            ListObjj = sorted(ListObj, key=lambda x: x.num_vol)

            # supprimer et re-ecrite du ficher
            os.remove("ficher vol.txt")
            for i in range(len(id)):
                ajoutVol(ListObjj[i])
    else:
        print("Pas de vol pour instant\n")


while True:
    menu()
    choice = int(input("Option: "))
    if choice == 1:
        obj = Horaire(input("id: "), input("Ville Deprat: "), input("Ville Arrive: "), input("Heure Depart: "), input("Heure Arrive: "))
        ajoutVol(obj)
    elif choice == 2:
        afficheVol()
    elif choice == 3:
        suprimmerVol()
    elif choice == 4:
        triVol()
    elif choice == 5:
        os._exit(1)
    else:
        print("Donner une bonne option")
