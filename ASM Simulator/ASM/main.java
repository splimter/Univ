package TPFinale;

import java.util.Scanner;

public class main {
    public static boolean verif(String ins){
        for (Cmd c : Cmd.values()) {
            if (c.name().equals(ins)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Affichage a = new Affichage();
        Dealing d = new Dealing();
        String ins;
        Scanner scr = new Scanner(System.in);

        //Ini the CO
        System.out.println("donner l'adress ou le programme est ranger");
        Regsiter.CO = scr.nextInt();

        //Clear the line
        scr.nextLine();

        //Set of ins
        System.out.println("donner les instruction");
        while (true) {
            ins = scr.nextLine();
            if(ins.toUpperCase().equals("HALT")) break;
            if(verif(d.spliter(ins)[0].toUpperCase())){
                d.setNbrIns();
                //split the ins using {spliter()} and setting them on Array using{setArrayList()}
                if (d.spliter(ins).length == 3)
                    d.setArrayList(d.spliter(ins)[0].toUpperCase(), d.spliter(ins)[1].toUpperCase(), d.spliter(ins)[2].toUpperCase());
                else if(d.spliter(ins).length == 2)
                    d.setArrayList(d.spliter(ins)[0].toUpperCase(), d.spliter(ins)[1].toUpperCase());
                else
                    d.setArrayList(d.spliter(ins)[0].toUpperCase());
            }
            else
                System.out.println("wrong instruction");
        }
        //Show header
        a.Header();
        //Other ins
        d.manager();
        a.InstructionHALT();
    }
}