package TPFinale;

import java.util.ArrayList;

public class Dealing {
    Affichage a = new Affichage();
    private int nbrIns, j = 0, r, cmp;
    private String lab;
    private ArrayList[] al = new ArrayList[20];

    //ctor
    Dealing() {
        //ini al
        for (int i = 0; i < 20; i++) {
            al[i] = new ArrayList<Integer>();
        }
    }

    //gathering the number of ins.
    void setNbrIns() {
        nbrIns++;
    }

    //felling the ArrayList.
    void setArrayList(String... nums) {
        al[j].add(nums[0]);
        if (nums.length >= 2)
            al[j].add(nums[1]);
        if (nums.length == 3)
            al[j].add(nums[2]);
        j++;
    }

    //ins spliter into parts.
    String[] spliter(String x) {
        String[] arr = x.split(" ");
        return arr;
    }

    //regroup the splited string.
    String regroup(int i) {
        String s = "";
        for (int j = 0; j < al[i].size(); j++) {
            s += al[i].get(j) + " ";
        }
        return s;
    }

    //ins manager.
    void manager() {
        for (int i = 0; i < nbrIns; i++) {
            //ADD&MOV done
            if (al[i].get(0).equals(Cmd.ADD.toString()) || al[i].get(0).equals(Cmd.MOV.toString()))
                ADDMOV(i);

                //INC&DEC INS {done}
            else if (al[i].get(0).equals(Cmd.INC.toString()) || al[i].get(0).equals(Cmd.DEC.toString()))
                INCDEC(i);

                //CLR INS{done}
            else if (al[i].get(0).equals(Cmd.CLR.toString()))
                CLR(i);

                //BR INS{done}
            else if (al[i].get(0).equals(Cmd.BR.toString()))
                i = BR(i);

                //CMP and her childes {cmp} store {CMP()} value
            else if (al[i].get(0).equals(Cmd.CMP.toString())) {
                cmp = CMP(i);
            } else
                i = LOGIC(i);
        }
    }

    /*
    Affect the Source&Destination to temporary reg {exec1&exec2}
    Set Destenation Value on {r} 1 for R1  && 2 for R2
     */
    void ADDMOV(int i) {
        if (al[i].get(1).equals("R1")) {
            Regsiter.exec2 = Regsiter.R1;
            r = 2;
        } else if (al[i].get(1).equals("R2")) {
            Regsiter.exec1 = Regsiter.R2;
            r = 1;
        } else {
            if (al[i].get(2).equals("R1")) {
                Regsiter.exec1 = Integer.valueOf(al[i].get(1).toString());
                r = 1;
            } else {
                Regsiter.exec2 = Integer.valueOf(al[i].get(1).toString());
                r = 2;
            }
        }
        if (al[i].get(0).equals(Cmd.ADD.toString()))
            a.InstructionADD(regroup(i), r);
        else if (al[i].get(0).equals(Cmd.MOV.toString()))
            a.InstructionMOV(regroup(i), r);

    }

    void INCDEC(int i) {
        if (al[i].get(1).equals("R1"))
            r = 1;
        else
            r = 2;
        if (al[i].get(0).equals(Cmd.INC.toString()))
            a.InstructionINC(regroup(i), r, 1);
        else
            a.InstructionINC(regroup(i), r, -1);
    }

    void CLR(int i) {
        int t;
        if (al[i].get(1).equals("R1"))
            t = 1;
        else
            t = 2;
        a.InstructionCLR(regroup(i), t);
    }

    int BR(int i) {
        Regsiter.BRCO = Regsiter.CO;
        String s = regroup(i);
        String stop = al[i].get(1).toString();
        do {
            i++;
            Regsiter.BRCO++;
        } while (!al[i + 1].get(0).toString().equals(stop));
        a.InstructionBR(s);
        return i + 1;
    }

    int CMP(int i) {
        int x = 0, y = 0;
        int t;
        if (al[i].get(2).equals("R1"))
            t = 1;
        else
            t = 2;
        if (al[i].get(1).equals("R1"))
            x = Regsiter.R1;
        else if (al[i].get(1).equals("R2"))
            x = Regsiter.R2;
        if (al[i].get(2).equals("R1"))
            y = Regsiter.R1;
        else if (al[i].get(2).equals("R2"))
            y = Regsiter.R2;
        else {
            x = Integer.valueOf(al[i].get(1).toString());
            y = Integer.valueOf(al[i].get(2).toString());
        }
        a.InstructionCMP(regroup(i), t);
        return y - x;
    }

    int LOGIC(int i) {
        /*
        {lab} will store BR lable

        CMP X Y     FAUX ->> DO WORK  /\  VRAI ->> BR
        ---------------------------------------------------
        BEQ     BGE         BGT         BLE         BLT
        X = Y   X > = Y     X > Y       X < = Y     X < Y
         */
        if (al[i].get(0).equals(Cmd.BEQ.toString())) {
            lab = al[i].get(1).toString();
            if (cmp == 0)
                i = BR(i);
            cmp = i;
        } else if (al[i].get(0).equals(Cmd.BGE.toString())) {
            lab = al[i].get(1).toString();
            if (cmp <= 0)
                i = BR(i);
            cmp = i;
        } else if (al[i].get(0).equals(Cmd.BGT.toString())) {
            lab = al[i].get(1).toString();
            if (cmp < 0)
                i = BR(i);
            cmp = i;
        } else if (al[i].get(0).equals(Cmd.BLE.toString())) {
            lab = al[i].get(1).toString();
            if (cmp >= 0)
                i = BR(i);
            cmp = i;
        } else if (al[i].get(0).equals(Cmd.BLT.toString())) {
            lab = al[i].get(1).toString();
            if (cmp > 0)
                i = BR(i);
            cmp = i;
        } else if (al[i].get(0).equals(lab)) {
            return cmp - 2;
        }
        return i;
    }
}