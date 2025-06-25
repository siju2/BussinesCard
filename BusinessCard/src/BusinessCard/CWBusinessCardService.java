package BusinessCard;

import java.util.List;

public class CWBusinessCardService implements BusinessCardService {

    private BusinessCardDAO businessCardDAO;

    public CWBusinessCardService(BusinessCardDAO businessCardDAO) {
        this.businessCardDAO = businessCardDAO;
    }

    @Override
    public boolean registTemplate(BusinessCardTemplateVO template) {
        return businessCardDAO.insertTemplate(template);
    }

    @Override
    public List<BusinessCardTemplateVO> listTemplates() {
        return businessCardDAO.selectAllTemplates();
    }

    @Override
    public BusinessCardTemplateVO detailTemplateInfo(int templateNo) {
        return businessCardDAO.selectTemplate(templateNo);
    }

    @Override
    public boolean updateTemplatePrice(int templateNo, int price) {
        BusinessCardTemplateVO template = businessCardDAO.selectTemplate(templateNo);

        if (template != null) {
            template.setPrice(price);
            businessCardDAO.updateTemplate(template);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateTemplateStock(int templateNo, int stock) {
        BusinessCardTemplateVO template = businessCardDAO.selectTemplate(templateNo);

        if (template != null) {
            template.setStock(stock);
            businessCardDAO.updateTemplate(template);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeTemplate(int templateNo) {
        return businessCardDAO.deleteTemplate(templateNo);
    }
}
