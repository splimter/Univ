# placer les donner d'un fichier dans une list
def fileTolist(file):
    with open(file, 'r+', encoding='utf-8') as file:
        # assigner la varibale t tout le contenue du ficher
        t = file.readlines()
        # convertire la varibale t en String et suprimer \n
        t = str(t).split('\\n')
        # x va contenire des donner propre depuis t
        arr = ''.join(e for e in str(t) if e.isalnum() or e == ',').split(',')
        # supprimer les element vide
        arr = [a for a in arr if a]
        # suprimer la 1er case
        arr.pop(0)
    return arr


# un filtre qui laisse les donner qui on un relation avec le ID
def focusNote(val, arr):
    i = 0
    while i < len(arr):
        if str(val) == arr[i]:
            i += 3
        else:
            if i == len(arr) - 1:
                break
            else:
                for j in range(3):
                    del arr[i]
            if i != 0:
                i -= 1
    return arr


# un filtre qui laisse les donner qui on un relation avec le ID
def focusMat(val, arr):
    i = 1
    while i < len(arr):
        if str(val) == arr[i]:
            i += 3
        else:
            if i == len(arr) - 1:
                break
            else:
                for j in range(3):
                    del arr[i - 1]
    return arr


# un filtre qui laisse la note et la coe
def extraTrimer(arr):
    i, j = 0, 0
    while i < len(arr):
        if i % 2 == 0 and i != 0:
            j += 1
            i = 0
            continue
        arr.pop(j)
        i += 1
    for j in range(len(arr)):
        arr[j] = int(arr[j])
