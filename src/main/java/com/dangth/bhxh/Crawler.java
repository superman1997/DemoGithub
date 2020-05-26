package com.dangth.bhxh;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        getHamlet();
    }
    private static List<String> cityToCsv(boolean writeFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("datatest"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("city.csv"));
        List<String> strings = bufferedReader
                .lines()
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    s = s.trim();
                    s= s
                            .replace("<option", "")
                            .replace("</option>", "")
                            .replace("value=\"", "")
                            .replace("\">", ",");
                    return s.trim();
                })
                .sorted()
                .collect(Collectors.toList());
        bufferedWriter.write("id, city");
        bufferedWriter.newLine();
        if (writeFile) {
            strings.forEach(s -> {
                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedWriter.close();
        }
        List<String> idCity = new ArrayList<>();
        for (String string : strings) {
            idCity.add(string.split(",")[0]);
        }
        return idCity;
    }

    private static void getProvince() throws IOException, ParseException, InterruptedException {
        List<String> idCity = cityToCsv(false);
        String url = "https://baohiemxahoi.gov.vn/UserControls/BHXH/BaoHiemYTe/HienThiHoGiaDinh/AjaxPost.aspx/GetHuyenByLstmatinh";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("province.csv"));
        bufferedWriter.write("id,idcity,province");
        bufferedWriter.newLine();
        for (String id :idCity) {
            JSONArray array = getDataPost(url, id, "{lstmatinh:\"%s\"}");

            for (Object o : array) {
                JSONObject jsonObject = ((JSONObject)o);
                String row = jsonObject.get("MaHuyen") + "," + id +"," +jsonObject.get("TenHuyen");
                System.out.println(row);
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
    private static void getCommune() throws IOException, ParseException, InterruptedException {
        List<String> idProvince = readIdFromCsv("province.csv");
        String url = "https://baohiemxahoi.gov.vn/UserControls/BHXH/BaoHiemYTe/HienThiHoGiaDinh/AjaxPost.aspx/GetPhuongBylstmahuyen";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("commune.csv"));
        bufferedWriter.write("id,idprovince,commune");
        bufferedWriter.newLine();
        for (String id :idProvince) {
            JSONArray array = getDataPost(url, id, "{lstmahuyen:\"%s\"}");

            for (Object o : array) {
                JSONObject jsonObject = ((JSONObject)o);
                String row = jsonObject.get("MaXa") + "," + id +"," +jsonObject.get("TenXa");
                System.out.println(row);
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
    private static void getHamlet() throws IOException, ParseException, InterruptedException {
        List<String> idCommune = readIdFromCsv("commune.csv");
        String url = "https://baohiemxahoi.gov.vn/UserControls/BHXH/BaoHiemYTe/HienThiHoGiaDinh/AjaxPost.aspx/GetThonBylstmaxa";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("hamlet.csv"));
        bufferedWriter.write("id,idcommune,hamlet");
        bufferedWriter.newLine();
        for (String id :idCommune) {
            JSONArray array = getDataPost(url, id, "{lstmaxa:\"%s\"}");

            for (Object o : array) {
                JSONObject jsonObject = ((JSONObject)o);
                String row = jsonObject.get("MaThon") + "," + id +"," +jsonObject.get("TenThon");
                System.out.println(row);
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
    private static JSONArray getDataPost(String url, String id, String s) throws InterruptedException, IOException, ParseException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String json = String.format(s, id);
        Thread.sleep(500);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        InputStream in = response.getEntity().getContent();
        String res = convert(in, Charset.forName("UTF-8"));
        System.out.println(res);
        client.close();
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(res);
        JSONArray array = (JSONArray) parser.parse(object.get("d").toString());
        System.out.println(array.toJSONString());
        return array;
    }

    private static List<String> readIdFromCsv(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String> strings = new ArrayList<>();
        String line;
        boolean firstRun = true;
        while ((line = reader.readLine()) != null) {
            if (firstRun) {
                firstRun = false;
                continue;
            }
            strings.add(line.split(",")[0]);
            System.out.println(line);
        }
        reader.close();
        return strings;
    }
    public static String convert(InputStream inputStream, Charset charset) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }
}
