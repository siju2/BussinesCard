package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import BusinessCard.BusinessCardTemplateVO;
import BusinessCard.HashMapBusinessCardDAO;

public class ObjFileHashMapBusinessCardDAO extends HashMapBusinessCardDAO implements FileBusinessCardDB {

    private String dataFilename = DATA_FILE + ".obj";

    public ObjFileHashMapBusinessCardDAO() {
        loadTemplates();
    }

    @Override
    public void saveTemplates() {
        try (
            FileOutputStream fos = new FileOutputStream(dataFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(templateDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplates() {
        try (
            FileInputStream fis = new FileInputStream(dataFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            templateDB = (Map<Integer, BusinessCardTemplateVO>) ois.readObject();
            templateSeq = Collections.max(templateDB.keySet()) + 1;
        } catch (FileNotFoundException e) {
            System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertTemplate(BusinessCardTemplateVO template) {
        boolean result = super.insertTemplate(template);
        if (result) saveTemplates();
        return result;
    }

    @Override
    public boolean updateTemplate(BusinessCardTemplateVO newTemplate) {
        boolean result = super.updateTemplate(newTemplate);
        if (result) saveTemplates();
        return result;
    }

    @Override
    public boolean deleteTemplate(int templateNo) {
        boolean result = super.deleteTemplate(templateNo);
        if (result) saveTemplates();
        return result;
    }
}
