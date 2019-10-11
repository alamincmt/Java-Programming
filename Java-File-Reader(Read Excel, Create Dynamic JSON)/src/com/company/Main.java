package com.company;

import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.reader.SheetReader;
import com.ebay.xcelite.sheet.XceliteSheet;
import com.ebay.xcelite.writer.SheetWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    // Total quran ayat sizes
    private static long total_ar_ayat_size = 0;
    private static long total_bn_ayat_size = 0;

    // Sura wise ayat sizes
    private static long sura_ar_ayat_total_size = 0;
    private static long sura_bn_ayat_total_size = 0;

    static List<JSONObject> ayatObjList = new ArrayList<>();
    static List<JSONObject> suraObjList = new ArrayList<>();

    public static void main(String[] args) {



        ReadExcel readExcel = new ReadExcel();
        readExcel.setInputFile("/home/al-amin/Desktop/Quran Bangla Meaning/quran_bangla_ayat_details.xls");
        readExcel.read();

        // reading excel files
        /*try {
            AdvUse.setEcxelFile("/home/al-amin/Desktop/Quran Bangla Meaning/001", "al_fatiha.xlsx");
            String data = AdvUse.getCellData(1, 2);
            System.out.println("Data " + data);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*Xcelite xcelite = new Xcelite();
        XceliteSheet sheet = xcelite.createSheet("users");
        SheetWriter<User> writer = sheet.getBeanWriter(User.class);
        List<User> users = new ArrayList<User>();
        writer.write(users);
        xcelite.write(new File("/home/al-amin/Desktop/Quran Bangla Meaning/001", "al_fatiha.xlsx"));*/

        /*File file = new File("/home/al-amin/Desktop/Quran Bangla Meaning/001/al_fatiha.xlsx");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream targetStream =  new DataInputStream(new FileInputStream(file));
            Xcelite xcelite = new Xcelite(targetStream);
            XceliteSheet sheet = xcelite.getSheet(0);
            SheetReader<User> reader = sheet.getBeanReader(User.class);
            Collection<User> users = reader.read();
            System.out.println("Data: " + users.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/


        // Reading quran details
        /*JSONObject alquran_data = new JSONObject();
        JSONObject suraObj = new JSONObject();
        JSONObject ayatObj = new JSONObject();
        JSONArray sura_details = new JSONArray();
        JSONArray ayat_details = new JSONArray();

        alquran_data.put("total_sura", 114);
        alquran_data.put("total_ar_ayat", 6236);
        alquran_data.put("total_bn_ayat", 6236);

        try {
            File rootDirectory = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura");

            if(rootDirectory.isDirectory() && rootDirectory.canRead()){

                String[] fileArr = rootDirectory.list();

                for(int suraNumber=0; suraNumber<fileArr.length; suraNumber++){

                    suraObj = new JSONObject();

                    String fileNamePrefix = "";
                    String fileRootFolderNumber = "";
                    if(suraNumber<10){
                        fileNamePrefix = "00";
                    }else if(suraNumber < 100){
                        fileNamePrefix = "0";
                    }else{
                        fileNamePrefix = "";
                    }

                    fileRootFolderNumber = fileNamePrefix + (suraNumber+1);

                    if(suraNumber == 99){
                        fileRootFolderNumber = "100";
                    }


                    if(suraNumber == 9){
                        fileRootFolderNumber = "010";
                    }

                    suraObj.put("sura_number", suraNumber+1);

                    int suraNumberNew = suraNumber+1;

                    ayatObjList.clear();
                    for(int i=0; i<fileArr.length; i++){

                        if(fileArr[i].equals(fileRootFolderNumber)){

                            suraObj.put("sura_name", fileArr[i]);

                            File ayatARFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + fileArr[i] + "/Arabic_Ayat");
                            File ayatBNFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + fileArr[i] + "/Bangla_Ayat");
                            String[] arAyatArr = ayatARFile.list();
                            String[] bnAyatArr = ayatBNFile.list();

                            suraObj.put("sura_ar_ayat_count", arAyatArr.length);
                            suraObj.put("sura_bn_ayat_count", bnAyatArr.length);

                            ayatObjList = new ArrayList<>();
                            ayat_details = new JSONArray();

                            for(int j=0; j<arAyatArr.length; j++){

                                System.out.println("Loop + " + j);

                                ayatObj = new JSONObject();

                                String arAyatPreix = "";
                                if(j<10){
                                    arAyatPreix = "00";
                                }else if(j<100){
                                    arAyatPreix = "0";
                                }else{
                                    arAyatPreix = "";
                                }

                                String ayatNumber = arAyatPreix+(j);

                                String arAyatName = getArAyatName(arAyatArr, fileRootFolderNumber, ayatNumber, fileArr[i]);
                                String bnAyatName = getBnAyatName(bnAyatArr, fileRootFolderNumber, ayatNumber, fileArr[i]);

                                ayatObj.put("ayat_number", j);
                                ayatObj.put("ayat_ar_audio_url", arAyatName);
                                ayatObj.put("ayat_bn_audio_url", bnAyatName);
                                File arAyatFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + fileArr[i] + "/Arabic_Ayat/"+arAyatName);
                                ayatObj.put("ayat_ar_size", arAyatFile.length());
                                File bnAyatFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + fileArr[i] + "/Bangla_Ayat/"+bnAyatName);
                                ayatObj.put("ayat_bn_size", bnAyatFile.length());
                                ayatObjList.add(ayatObj);
                                sura_ar_ayat_total_size += arAyatFile.length();
                                sura_bn_ayat_total_size += bnAyatFile.length();

                            }

                            if(ayatObjList.size() > 0 && ayatObjList.size() == arAyatArr.length) {
                                ayat_details.addAll(ayatObjList);
                                suraObj.put("ayat_details", ayat_details);
                                suraObj.put("sura_ar_ayat_total_size", sura_ar_ayat_total_size);
                                suraObj.put("sura_bn_ayat_total_size", sura_bn_ayat_total_size);

                                total_ar_ayat_size += sura_ar_ayat_total_size;
                                total_bn_ayat_size += sura_bn_ayat_total_size;

                                sura_ar_ayat_total_size = 0;
                                sura_bn_ayat_total_size = 0;
                                ayatObjList.clear();
                            }
                        }

                    }
                    suraObjList.add(suraObj);
                    System.out.println("Size: " + ayat_details.size());
                }

                sura_details.addAll(suraObjList);

                alquran_data.put("total_ar_ayat_size", total_ar_ayat_size);
                alquran_data.put("total_bn_ayat_size", total_bn_ayat_size);
                alquran_data.put("sura_details", sura_details);
                System.out.println(alquran_data.toString());


            }else{
                System.out.println("File Don't Exist. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + "");
        }
    }

    public static String getArAyatName(String[] arAyatArr, String fileRootFolderNumber, String ayatNumber, String suraFolder){
        for(int k=0; k<arAyatArr.length; k++){
            if(arAyatArr[k].equals("ar_" +fileRootFolderNumber+ ayatNumber+".mp3")){
                File arAyatFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + suraFolder + "/Arabic_Ayat/"+arAyatArr[k]);
//                System.out.print(suraFolder + " Arabic Ayat: " + arAyatArr[k] + " Arabic File Size: " + getFileFolderSize(arAyatFile) + "\n");
                return arAyatArr[k];
            }
        }
        return "";
    }

    public static String getBnAyatName(String[] bnAyatArr, String fileRootFolderNumber, String ayatNumber, String suraFolder){
        for(int k=0; k<bnAyatArr.length; k++){
            if(bnAyatArr[k].equals("bn_" +fileRootFolderNumber+ ayatNumber+".mp3")){
                File arAyatFile = new File("/home/al-amin/Al-Amin-Files/Quran 2/al-quran-ayat-wise/Ayat_n_Sura/" + suraFolder + "/Bangla_Ayat/"+bnAyatArr[k]);
//                System.out.print(suraFolder + " Bangla Ayat: " + bnAyatArr[k] + " Bangla File Size: " + getFileFolderSize(arAyatFile) + "\n");
                return bnAyatArr[k];
            }
        }
        return "";
    }

    public static long getFileFolderSize(File file) {
        if(file != null && file.isFile()){
            return file.length();
        }else{
            return 0;
        }
    }*/

    /*private String generateJSON(String unique_id, List<ScreenNavigation> screenNavigationList, List<Session> sessionList) {
        try {
            for(ScreenNavigation screenNavigation : screenNavigationList){
                activityDataObj.put("session_id", screenNavigation.getSession_id());
                activityDataObj.put("screen_name", screenNavigation.getScreen_name());
                activityDataObj.put("time", screenNavigation.getTime());

                activityArr.put(activityDataObj);
            }

            for(Session session : sessionList){
                sessionsDataObj.put("session_id", session.getSession_id());
                sessionsDataObj.put("start_time", session.getStart_time());
                sessionsDataObj.put("end_time", session.getEnd_time());

                sessionArr.put(sessionsDataObj);
            }


            totalObj.put("advertise_id", unique_id);
            totalObj.put("activity", activityArr);
//            totalObj.put("sessions", sessionArr);


            clientEventObj.put("client_event", totalObj);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clientEventObj.toString();
    }*/

    }

    class User {

        @Column(name="ayat")
        private String firstName;

        @Column
        private String id;
    }
}
