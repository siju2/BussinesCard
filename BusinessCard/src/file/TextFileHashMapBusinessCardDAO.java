package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import BusinessCard.BusinessCardTemplateVO;
import BusinessCard.HashMapBusinessCardDAO;

public class TextFileHashMapBusinessCardDAO extends HashMapBusinessCardDAO implements FileBusinessCardDB {

    private String dataFilename = DATA_FILE + ".txt";
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void saveTemplates() {
        try (
            FileWriter fw = new FileWriter(dataFilename);
            PrintWriter pw = new PrintWriter(fw);
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            for (BusinessCardTemplateVO template : templateDB.values()) {
                pw.println(template.getTemplateNo());
                pw.println(template.getName());
                pw.println(template.getDesignCode());
                pw.println(template.getBrand());
                pw.println(template.getPrice());
                pw.println(template.getStock());
                pw.println(sdf.format(template.getRegdate()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplates() {
        try (
            FileReader fr = new FileReader(dataFilename);
            BufferedReader br = new BufferedReader(fr);
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            while (br.ready()) {
                int templateNo = Integer.parseInt(br.readLine());
                String name = br.readLine().strip();
                String designCode = br.readLine().strip();
                String brand = br.readLine().strip();
                int price = Integer.parseInt(br.readLine());
                int stock = Integer.parseInt(br.readLine());
                Date regdate = sdf.parse(br.readLine());

                templateDB.put(templateNo, new BusinessCardTemplateVO());

                if (templateSeq <= templateNo) templateSeq = templateNo + 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("[로딩] " + dataFilename + "이 없습니다.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
