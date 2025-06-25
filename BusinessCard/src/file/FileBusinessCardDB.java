package file;

public interface FileBusinessCardDB {
    String DATA_FILE = "./data/businessCardDB";
    void saveTemplates();
    void loadTemplates();
}
