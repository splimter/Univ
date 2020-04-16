public class Displayer {

    void Header() {
        System.out.println(" _________________________________________________________________________________________________________________________");
        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "  ", "OC", "IR", "AR", "MR", "R1", "R2");
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
    }

    void InstructionCLR(String RA, int i) {
        int j = 0;
        if (i == 1) {
            i = 0;
            j = Regsiter.R2;
            Regsiter.exec1 = 0;
        } else if (i == 2) {
            i = Regsiter.R1;
            j = 0;
            Regsiter.exec2 = 0;
        }
        BESC(RA);
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, RA, RA, Regsiter.R1 = i, Regsiter.R2 = j);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");

    }

    void InstructionINC(String RA, int i, int p) {
        int j = 0;
        if (i == 1) {
            i = p;
            j = 0;

        } else if (i == 2) {
            i = 0;
            j = p;
        }
        BESC(RA);
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, RA, RA, Regsiter.R1 += i, Regsiter.R2 += j);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");

        Regsiter.exec1 = 0;
        Regsiter.exec2 = 0;
    }

    private void BESC(String RA) {
        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "BSC", Regsiter.CO, Regsiter.CO, RA, RA, Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "ESC", Regsiter.CO += 1, Regsiter.CO - 1, RA, RA, Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println("|               |               |               |                    |                    |               |               |");
    }

    void InstructionADD(String RA, int i) {
        int a, b, n;
        if (i == 1) {
            n = Regsiter.exec1;
            a = Regsiter.exec1;
            b = 0;
        } else {
            n = Regsiter.exec2;
            a = 0;
            b = Regsiter.exec2;
        }
        BESC(RA);
        System.out.printf("|     %-10s|     %-10d|     %-10d|     %-15s|     %-15s|     %-10d|     %-10d|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, RA, n, Regsiter.R1 += a, Regsiter.R2 += b);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");
    }

    void InstructionMOV(String RA, int i) {
        int a, b, n;
        if (i == 1) {
            n = Regsiter.exec1;
            a = Regsiter.exec1;
            b = Regsiter.R2;
        } else {
            n = Regsiter.exec2;
            a = Regsiter.R1;
            b = Regsiter.exec2;
        }


        BESC(RA);
        System.out.printf("|     %-10s|     %-10d|     %-10d|     %-15s|     %-15s|     %-10d|     %-10d|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, RA, n, Regsiter.R1 = a, Regsiter.R2 = b);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");

    }


    void InstructionBR(String RA) {

        BESC(RA);
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "EEC", Regsiter.BRCO, Regsiter.CO - 1, RA, RA, Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");
        Regsiter.CO = Regsiter.BRCO;

    }

    void InstructionCMP(String RA, int r) {
        int n;
        if (r == 1)
            n = Regsiter.R1;
        else
            n = Regsiter.R2;

        BESC(RA);
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, RA, n, Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");
    }

    void InstructionHALT(){

        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "BSC", Regsiter.CO, Regsiter.CO, "HALT", "HALT", Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "ESC", Regsiter.CO += 1, Regsiter.CO - 1, "HALT", "HALT", Regsiter.R1, Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println("|               |               |               |                    |                    |               |               |");
        System.out.printf("|     %-10s|     %-10s|     %-10s|     %-15s|     %-15s|     %-10s|     %-10s|\n", "EEC", Regsiter.CO, Regsiter.CO - 1, "HALT", "HALT", Regsiter.R1 , Regsiter.R2);
        System.out.println("|_______________|_______________|_______________|____________________|____________________|_______________|_______________|");
        System.out.println(" _________________________________________________________________________________________________________________________");

    }
}