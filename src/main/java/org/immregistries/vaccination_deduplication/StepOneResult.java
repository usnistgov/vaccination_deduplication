package org.immregistries.vaccination_deduplication;

public class StepOneResult {
    private LinkedImmunization toEvaluate;
    private LinkedImmunization notToEvaluate;

    public StepOneResult() {
        this.toEvaluate = new LinkedImmunization();
        this.notToEvaluate = new LinkedImmunization();
    }

    public StepOneResult(LinkedImmunization toEvaluate, LinkedImmunization notToEvaluate) {
        this.toEvaluate = toEvaluate;
        this.notToEvaluate = notToEvaluate;
    }

    public LinkedImmunization getToEvaluate() {
        return toEvaluate;
    }

    public void setToEvaluate(LinkedImmunization toEvaluate) {
        this.toEvaluate = toEvaluate;
    }

    public LinkedImmunization getNotToEvaluate() {
        return notToEvaluate;
    }

    public void setNotToEvaluate(LinkedImmunization notToEvaluate) {
        this.notToEvaluate = notToEvaluate;
    }
}
