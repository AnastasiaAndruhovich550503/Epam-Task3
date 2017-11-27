package by.andruhovich.task.actionvar;

import by.andruhovich.task.var.Var;
import by.andruhovich.task.var.VarSequence;

public class ChangerVar {
    public void changeI() {
        Var.i += VarSequence.sequenceI.get(0);
        VarSequence.sequenceI.remove(0);
    }

    public void changeJ() {
        Var.j += VarSequence.sequenceJ.get(0);
        VarSequence.sequenceJ.remove(0);
    }
}
