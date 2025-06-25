package BusinessCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapBusinessCardDAO implements BusinessCardDAO {

    protected Map<Integer, BusinessCardTemplateVO> templateDB = new HashMap<>();
    protected int templateSeq = 111;

    @Override
    public boolean insertTemplate(BusinessCardTemplateVO template) {
        template.setTemplateNo(templateSeq++);
    
        templateDB.put(template.getTemplateNo(), template);

        return true;
    }

    @Override
    public BusinessCardTemplateVO selectTemplate(int templateNo) {
        return templateDB.get(templateNo);
    }

    @Override
    public List<BusinessCardTemplateVO> selectAllTemplates() {
        return new ArrayList<>(templateDB.values());
    }

    @Override
    public boolean updateTemplate(BusinessCardTemplateVO newTemplate) {
        templateDB.put(newTemplate.getTemplateNo(), newTemplate);
        return true;
    }

    @Override
    public boolean deleteTemplate(int templateNo) {
        return templateDB.remove(templateNo) != null;
    }
}

