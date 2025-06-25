package BusinessCard;

import java.util.List;

public interface BusinessCardDAO {
    boolean insertTemplate(BusinessCardTemplateVO template);
    BusinessCardTemplateVO selectTemplate(int templateNo);
    List<BusinessCardTemplateVO> selectAllTemplates();
    boolean updateTemplate(BusinessCardTemplateVO newTemplate);
    boolean deleteTemplate(int templateNo);
}
