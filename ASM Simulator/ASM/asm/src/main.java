import java.util.Scanner;

public class main {
    public static boolean verify(String ins) {
        /*for (Cmd c : Cmd.values()) {
            if (c.name().equals(ins)) {
                return true;
            }
        }
        return false;*/
        return true;
    }

    public static void main(String[] args) {
        Displayer a = new Displayer();
        Dealing d = new Dealing();
        String ins;
        Scanner scr = new Scanner(System.in);

        //Ini the CO
        System.out.println("Give the address of were should the program is stored");

        do {
            try {
                Regsiter.CO = scr.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Must be integer");
                scr.nextLine();
            }
        } while (true);

        //Clear the line
        scr.nextLine();

        //Set of ins
        System.out.println("Write a Set of instruction");
        while (true) {
            ins = scr.nextLine();
            if (ins.toUpperCase().equals("HALT")) break;
            if (verify(d.spliter(ins)[0].toUpperCase())) {
                d.setNbrIns();
                //split the ins using {spliter()} and setting them on Array using{setArrayList()}
                String[] subset;
                subset = d.spliter(ins);
                if (subset.length == 3)
                    d.setArrayList(subset[0].toUpperCase(), subset[1].toUpperCase(), subset[2].toUpperCase());
                else if (subset.length == 2)
                    {d.setArrayList(subset[0].toUpperCase(), subset[1].toUpperCase());}
                else
                    d.setArrayList(subset[0].toUpperCase());

            } else
                System.out.println("Wrong Instruction");
        }

        //Show header
        a.Header();

        //Other ins
//        try {
            d.manager();
//        } catch (Exception e) {
//            System.out.println("Syntax Error");
//        }
        a.InstructionHALT();
    }
}