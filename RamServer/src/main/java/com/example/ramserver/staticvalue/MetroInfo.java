package com.example.ramserver.staticvalue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import sun.reflect.generics.tree.Tree;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Component
public class MetroInfo {
    private Map<String, Pair> metroInfo = new TreeMap<String,Pair>();

    /*@Resource(name = "testDAO")
    private TestDAO testDAO;
*/

    //json파일 전처리
    //metro 정보로 custom map 생성
    //두 역 사이의 최단경로를 찾기위한 전처리 과정
    @PostConstruct
    public void init() throws IOException, ParseException {
        File file = ResourceUtils.getFile(
                "classpath:api/MetroInformation.json");

        ClassPathResource resource = new ClassPathResource("api/MetroInformation.json");
        JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(resource.getInputStream(), "UTF-8")); //json-simple
        JSONArray stationlist=(JSONArray)json.get("DATA");
        for(int i=0;i<stationlist.size();i++){
            JSONObject station= (JSONObject)stationlist.get(i);
            String stationCode=station.get("station_cd").toString();
            Pair a=new Pair(station.get("line_num").toString(),station.get("station_nm").toString());
            metroInfo.put(stationCode,a);
        }
       /* Object[] mapkey = metroInfo.keySet().toArray();
        Arrays.sort(mapkey);*/
        return;
    }

    private void MakeMap(){

    }
    //주어진 map으로 최단경로의 역을 찾아서 해당 역의 코드 return
    //BFS
    public String GetTradePlaceCode(){
        return "return";
    }

    public void resetWhiteList() {
        metroInfo.clear();
    }
}
class Pair{
    String y;
    String x;
    public Pair(String y, String x) {
        this.y = y;
        this.x = x;
    }
    public String first() {
        return y;
    }
    public String second() {
        return x;
    }
}