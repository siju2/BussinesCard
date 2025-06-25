package BusinessCard;

import java.util.LinkedList;
import java.util.List;

public class ListBusinessCardDAO implements BusinessCardDAO {

    private List<BusinessCardTemplateVO> templateList = new LinkedList<>();
    private int templateSeq = 111; // 템플릿 번호 1씩 증가

    @Override
    public boolean insertTemplate(BusinessCardTemplateVO template) {
        template.setTemplateNo(templateSeq++);
        templateList.add(template);
        return true;
    }

    @Override
    public BusinessCardTemplateVO selectTemplate(int templateNo) {
        for (BusinessCardTemplateVO template : templateList) {
            if (template.getTemplateNo() == templateNo)
                return template;
        }
        return null;
    }

    @Override
    public List<BusinessCardTemplateVO> selectAllTemplates() {
        return templateList;
    }

    @Override
    public boolean updateTemplate(BusinessCardTemplateVO newTemplate) {
        for (int i = 0; i < templateList.size(); i++) {
            if (templateList.get(i).getTemplateNo() == newTemplate.getTemplateNo()) {
                templateList.set(i, newTemplate);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTemplate(int templateNo) {
        for (BusinessCardTemplateVO template : templateList) {
            if (template.getTemplateNo() == templateNo) {
                templateList.remove(template);
                return true;
            }
        }
        return false;
    }
}
