package org.immregistries.vaccination_deduplication.utils;

import org.immregistries.codebase.client.CodeMap;
import org.immregistries.codebase.client.CodeMapBuilder;
import org.immregistries.codebase.client.RelatedCode;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.vaccination_deduplication.Immunization;
import org.immregistries.vaccination_deduplication.LinkedImmunization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class uses the codebase client to get some potential missing information in the given Immunization.
 * Specifically vaccine groups and product codes (directly linked to the trade name).
 */
public class ImmunizationNormalisation {

    private CodeMapBuilder codeMapBuilder = CodeMapBuilder.INSTANCE;
    private InputStream inputStream;
    private CodeMap codeMap;
    private RelatedCode relatedCode;
    private DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

    /**
     * This constructor uses the codebase file inside the codebase client jar.
     */
    public ImmunizationNormalisation() {
        inputStream = CodeMapBuilder.class.getResourceAsStream("/DQA_CM_2.1.xml");
        codeMap = codeMapBuilder.getCodeMap(inputStream);
        relatedCode = new RelatedCode(codeMap);
    }

    /**
     * This constructor uses the codebase file at the specified path.
     * @param codebaseFilePath The path to the codebase file
     * @throws FileNotFoundException if the path to the codebase file is incorrect.
     */
    public ImmunizationNormalisation(String codebaseFilePath) throws FileNotFoundException {
        File file = new File(codebaseFilePath);
        inputStream = new FileInputStream(file);
        codeMap = CodeMapBuilder.INSTANCE.getCodeMap(inputStream);
        relatedCode = new RelatedCode(codeMap);
    }

    /**
     * This method will load the codebase file at the specified path and refresh the codebase client objects.
     * @param codebaseFilePath The path to the codebase file.
     * @throws FileNotFoundException if the path to the codebase file is incorrect.
     */
    public void refreshCodebase(String codebaseFilePath) throws FileNotFoundException {
        File file = new File(codebaseFilePath);
        inputStream = new FileInputStream(file);
        codeMap = CodeMapBuilder.INSTANCE.getCodeMap(inputStream);
        relatedCode = new RelatedCode(codeMap);
    }

    /**
     * This method will use the codebase client to get vaccine groups from the CVX and the product code from the CVX, MVX and date.
     * It will then add these values to the Immunization.
     * @param immunization The immunization to normalize
     */
    public void normalizeImmunization(Immunization immunization){
        immunization.addVaccineGroupList(new ArrayList<String>(relatedCode.getVaccineGroupLabelsFromCvx(immunization.getCVX()))); // Duplicates in this list are not an issue

        Code productCode = codeMap.getProductFor(immunization.getCVX(), immunization.getMVX(), dateFormat.format(immunization.getDate()));

        if (productCode != null) {
            immunization.setProductCode(productCode.getValue());
        }
    }

    /**
     * This method will call normalizeImmunization for all the Immunizations inside the given LinkedImmunization.
     * @param linkedImmunization The LinkedImmunization containing all the Immunizations to normalize.
     */
    public void normalizeAllImmunizations(LinkedImmunization linkedImmunization){
        for (Immunization immunization : linkedImmunization) {
            normalizeImmunization(immunization);
        }
    }
}
