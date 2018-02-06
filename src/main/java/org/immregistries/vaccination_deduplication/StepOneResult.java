package org.immregistries.vaccination_deduplication;

public class StepOneResult {
    private LinkedImmunization toEvaluate;
    private LinkedImmunization notToEvaluate;

    public StepOneResult(LinkedImmunization toEvaluate, LinkedImmunization notToEvaluate) {
        this.toEvaluate = toEvaluate;
        this.notToEvaluate = notToEvaluate;
        this.notToEvaluate.setType(LinkedImmunizationType.DIFFERENT);
    }

    public StepOneResult() {
        this(new LinkedImmunization(), new LinkedImmunization());
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
