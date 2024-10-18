package com.carblre.controller;

import com.carblre.dto.ItemDTO;
import com.carblre.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@SpringBootApplication
public class ProtoApplication {

    private static final String DB_URL = "jdbc:mysql://192.168.0.36:3306/carblre?serverTimezone=Asia/Seoul";
    private static final String DB_USER = "yhj";
    private static final String DB_PASSWORD = "1234";

    public static void main(String[] args) {
        SpringApplication.run(ProtoApplication.class, args);
        insertAccidentData();
    }

    public static void insertAccidentData() {
        int[] years = {2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012};
        String[] sidoCodes = {
                "1100" ,"1200", "2500", "2200", "2400", "2300", "2600",
                "2700", "1300", "1400", "1600", "1500", "1800", "1700",
                "2000", "1900", "2100"
        };

        String[][] gugunCodes = {
                {"1116", "1117", "1124", "1111", "1115", "1123", "1125", "1122", "1107", "1105", "1114", "1110", "1109", "1119",
                        "1104", "1106", "1118", "1120", "1113", "1103", "1108", "1101", "1102", "1121"}, // 서울특별시
                {"1212", "1211", "1216", "1207", "1203", "1206", "1208", "1215", "1210",
                        "1202", "1214", "1201", "1205", "1209"}, // 부산광역시
                {"2309", "2308", "2303", "2305", "2305", "2302", "2304", "2306", "2307", "2310", "2301"}, // 인천광역시
                {"2404", "2405", "2401", "2403", "2402"}, // 광주광역시
                {"2505", "2501", "2503", "2504", "2502"}, // 대전광역시
                {"2602", "2603", "2604", "2605", "2601"}, // 울산광역시
                {"2701"}, // 세종특별자치시
                {"1322", "1318", "1332", "1309", "1319", "1310", "1333", "1327", "1334", "1330", "1306",
                        "1303", "1302", "1316", "1307", "1326", "1305", "1311", "1323", "1313", "1320", "1335",
                        "1325", "1336", "1304", "1324", "1317", "1308", "1321", "1337", "1315", "1315"}, // 경기도
                {"1404", "1422", "1403", "1407", "1405", "1420", "1423", "1415", "1402", "1421",
                        "1417", "1418", "1401", "1406", "1416", "1412", "1419", "1413"}, // 강원특별자치도
                {"1516", "1520", "1512", "1514", "1513", "1517", "1503", "1521", "1515", "1511", "1501", "1502"}, // 충청북도
                {"1624", "1605", "1611", "1611", "1615", "1623", "1604", "1616", "1606", "1617", "1603",
                        "1613", "1621", "1602", "1619", "1612", "1620"}, // 충청남도
                {"1719", "1702", "1706", "1705", "1713", "1720", "1717", "1711", "1723", "1715", "1714", "1715",
                        "1717", "1711", "1723", "1715", "1714", "1701", "1704", "1712"}, // 전북특별자치도
                {"1822", "1818", "1813", "1808", "1814", "1806", "1812", "1802", "1825", "1819", "1804", "1832",
                        "1803", "1828", "1824", "1830", "1829", "1821", "1831", "1827", "1823", "1820"}, // 전라남도
                {"1935", "1903", "1923", "1906", "1912", "1904", "1909", "1932", "1910", "1924", "1905",
                        "1917", "1916", "1907", "1908", "1930", "1934", "1933", "1913", "1922", "1915", "1925", "1902"}, // 경상북도
                {"2010", "2028", "2022", "2008", "2024", "2001", "2009", "2023", "2026", "2016", "2012",
                        "2003", "2005", "2014", "2004", "2030", "2006", "2025", "2013", "2027", "2029"}, // 경상남도
                {"2102", "2101"} // 제주특별자치도
        };

        for (int year : years) {
            for (int j = 0; j < sidoCodes.length; j++) {
                String sido = sidoCodes[j];
                for (String gugun : gugunCodes[j]) {
                    String urlString = String.format(
                            "https://opendata.koroad.or.kr/data/rest/accident/death?authKey=pGtXhqGS6ZCrYNh88D%%2Bhn2wukoTfmPw51sT7QXIo8f02CwmNNRxsAmOKW%%2FJbRMl9&searchYear=%d&siDo=%s&guGun=%s",
                            year, sido, gugun
                    );
                    String jsonData = fetchData(urlString);
                    if (jsonData != null) {
                        insertDataIntoDB(jsonData);
                    }
                }
            }
        }
    }

    public static String fetchData(String urlString) {
        StringBuilder resultBuilder = new StringBuilder();
        try {
            URL url = new URL(urlString);
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                resultBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

    public static void insertDataIntoDB(String jsonData) {
        try {
            JSONObject jObject = XML.toJSONObject(jsonData); // XML을 JSON으로 변환
            ObjectMapper objectMapper = new ObjectMapper();

            // ResponseDTO를 사용하여 JSON을 파싱
            ResponseDTO response = objectMapper.readValue(jObject.toString(), ResponseDTO.class);

            // 빈 문자열 체크 및 초기화
            if (response.getResponse().getBody().getItems().getItem() == null ||
                    response.getResponse().getBody().getItems().getItem().isEmpty()) {
                // 아이템이 없을 경우 무시하고 넘어감
                System.out.println("No items to insert. Skipping...");
                return; // 데이터를 삽입하지 않고 종료
            }


            // 데이터베이스에 연결
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            for (ItemDTO item : response.getResponse().getBody().getItems().getItem()) {
                String sql = " INSERT INTO crushApi_tb (acc_year, dth_dnv_cnt, injpsn_cnt, se_dnv_cnt, sl_dnv_cnt, " +
                        " occrrnc_lc_sido_cd, occrrnc_lc_sgg_cd, acc_ty_lclas_cd, acc_ty_mlsfc_cd, " +
                        " acc_ty_cd, aslt_vtr_cd, road_frm_lclas_cd, road_frm_cd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                System.out.println("Inserting: " + item); //
                pstmt.setInt(1, item.getAccYear());
                pstmt.setInt(2, item.getDthDnvCnt());
                pstmt.setInt(3, item.getInjpsnCnt());
                pstmt.setInt(4, item.getSeDnvCnt());
                pstmt.setInt(5, item.getSlDnvCnt());
                pstmt.setInt(6, item.getOccrrncLcSidoCd());
                pstmt.setInt(7, item.getOccrrncLcSggCd());
                pstmt.setString(8, item.getAccTyLclasCd());
                pstmt.setString(9, item.getAccTyMlsfcCd());
                pstmt.setString(10, item.getAccTyCd());
                pstmt.setString(11, item.getAsltVtrCd());
                pstmt.setString(12, item.getRoadFrmLclasCd());
                pstmt.setString(13, item.getRoadFrmCd());
                pstmt.executeUpdate();

                System.out.println("TotalCount : " + response.getResponse().getBody().getTotalCount());
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Error processing JSON data : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
