import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Dealing {
    private Displayer a = new Displayer();
    private int nbrIns, j = 0, r, cmp;
    private String lab;
    private List<ArrayList<String>> al = new ArrayList<>();

    //gathering the number of ins.
    void setNbrIns() {
        nbrIns++;
    }

    //felling the ArrayList.
    void setArrayList(String... nums) {
        al.add(new ArrayList<>());
        al.get(j).add(nums[0]);
        if (nums.length >= 2)
            al.get(j).add(nums[1]);
        if (nums.length == 3)
            al.get(j).add(nums[2]);
        j++;
    }

    //ins split into parts.
    String[] spliter(String x) {
        String[] arr = x.split(" ");
        return arr;
    }

    //regroup the splited string.
    String regroup(int i) {
        String s = "";
        for (int j = 0; j < al.get(i).size(); j++) {
            s += al.get(i).get(j) + " ";
        }
        return s;
    }

    //ins manager.
    void manager() {
        for (int i = 0; i < nbrIns; i++) {
            //ADD&MOV {done}
            if (al.get(i).get(0).equals(Cmd.ADD.toString()) || al.get(i).get(0).equals(Cmd.MOV.toString()))
                ADDMOV(i);

                //INC&DEC INS {done}
            else if (al.get(i).get(0).equals(Cmd.INC.toString()) || al.get(i).get(0).equals(Cmd.DEC.toString()))
                INCDEC(i);

                //CLR INS {done}
            else if (al.get(i).get(0).equals(Cmd.CLR.toString()))
                CLR(i);

                //BR INS {done}
            else if (al.get(i).get(0).equals(Cmd.BR.toString()))
                i = BR(i) - 1;

            else if (al.get(i).get(0).endsWith(":")) {
                continue;
            }

            //CMP and her childes {cmp} store {CMP()} value
            else if (al.get(i).get(0).equals(Cmd.CMP.toString())) {
                cmp = CMP(i);
            } else
                i = LOGIC(i);
        }
    }

    /*
    Affect the Source&Destination to temporary reg {exec1&exec2}
    Set Destination Value on {r} 1 for R1  && 2 for R2
     */
    void ADDMOV(int i) {
        if (al.get(i).get(1).equals("R1")) {
            Regsiter.exec2 = Regsiter.R1;
            r = 2;
        } else if (al.get(i).get(1).equals("R2")) {
            Regsiter.exec1 = Regsiter.R2;
            r = 1;
        } else {
            if (al.get(i).get(2).equals("R1")) {
                Regsiter.exec1 = Integer.parseInt(al.get(i).get(1).toString());
                r = 1;
            } else {
                Regsiter.exec2 = Integer.parseInt(al.get(i).get(1).toString());
                r = 2;
            }
        }
        if (al.get(i).get(0).equals(Cmd.ADD.toString()))
            a.InstructionADD(regroup(i), r);
        else if (al.get(i).get(0).equals(Cmd.MOV.toString()))
            a.InstructionMOV(regroup(i), r);

    }

    void INCDEC(int i) {
        if (al.get(i).get(1).equals("R1"))
            r = 1;
        else
            r = 2;
        if (al.get(i).get(0).equals(Cmd.INC.toString()))
            a.InstructionINC(regroup(i), r, 1);
        else
            a.InstructionINC(regroup(i), r, -1);
    }

    void CLR(int i) {
        int t;
        if (al.get(i).get(1).equals("R1"))
            t = 1;
        else
            t = 2;
        a.InstructionCLR(regroup(i), t);
    }

    int BR(int i) {
        int j = 0;
        Regsiter.BRCO = Regsiter.CO;
        String s = regroup(i);
        String stop = al.get(i).get(1).toString();
        for (ArrayList e : al) {
            if (e.get(0).toString().equals(stop + ":") || e.get(0).toString().equals(stop)) {
                return j;
            }
            Regsiter.BRCO++;
            j++;
        }

        a.InstructionBR(s);
        return -1;
    }

    int CMP(int i) {
        int x = 0, y = 0;
        int t;

        if (al.get(i).get(2).equals("R1"))
            t = 1;
        else
            t = 2;

        if (al.get(i).get(1).equals("R1"))
            x = Regsiter.R1;
        else if (al.get(i).get(1).equals("R2"))
            x = Regsiter.R2;

        if (al.get(i).get(2).equals("R1"))
            y = Regsiter.R1;
        else if (al.get(i).get(2).equals("R2"))
            y = Regsiter.R2;

        if (!al.get(i).get(1).contains("R")
                && !al.get(i).get(2).contains("R")) {
            x = Integer.parseInt(al.get(i).get(1));
            y = Integer.parseInt(al.get(i).get(2));
        }

        a.InstructionCMP(regroup(i), t);
        return y - x;
    }

    int LOGIC(int i) {
        /*
        {lab} will store BR lable

        CMP X Y     false ->> DO WORK  /\  true ->> BR
        ---------------------------------------------------
        BEQ     BGE         BGT         BLE         BLT
        X = Y   X > = Y     X > Y       X < = Y     X < Y
         */
        if (al.get(i).get(0).equals(Cmd.BEQ.toString())) {
            lab = al.get(i).get(1);
            if (cmp == 0)
                i = BR(i);
            cmp = i;
        } else if (al.get(i).get(0).equals(Cmd.BGE.toString())) {
            lab = al.get(i).get(1);
            if (cmp <= 0)
                i = BR(i);
            cmp = i;
        } else if (al.get(i).get(0).equals(Cmd.BGT.toString())) {
            lab = al.get(i).get(1);
            if (cmp < 0)
                i = BR(i);
            cmp = i;
        } else if (al.get(i).get(0).equals(Cmd.BLE.toString())) {
            lab = al.get(i).get(1);
            if (cmp >= 0)
                i = BR(i);
            cmp = i;
        } else if (al.get(i).get(0).equals(Cmd.BLT.toString())) {
            lab = al.get(i).get(1);
            if (cmp > 0)
                i = BR(i);
            cmp = i;
        } else if (al.get(i).get(0).equals(lab)) {
            return cmp - 2;
        } else if (al.get(i).get(0).contains(":")) {
            i = BR(i);
        }
        return i;
    }
}