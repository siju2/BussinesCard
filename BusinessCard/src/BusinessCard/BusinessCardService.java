package BusinessCard;

import java.util.List;

public interface BusinessCardService {
    boolean registTemplate(BusinessCardTemplateVO template);
    List<BusinessCardTemplateVO> listTemplates();
    BusinessCardTemplateVO detailTemplateInfo(int templateNo);
    boolean updateTemplatePrice(int templateNo, int price);
    boolean updateTemplateStock(int templateNo, int stock);
    boolean removeTemplate(int templateNo);
}
