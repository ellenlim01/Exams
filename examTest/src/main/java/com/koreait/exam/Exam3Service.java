package com.koreait.exam;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.exam.vo.ApartmentInfoEntity;
import com.koreait.exam.vo.InsEntity;
import com.koreait.exam.vo.LocationCodeEntity;
import com.koreait.exam.vo.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class Exam3Service {

    @Autowired
    private Exam3Mapper mapper;

    public List<LocationCodeEntity> selLocationCodeList() {
        return mapper.selLocationCodeList(null);
    }

    public void saveData(SearchDTO param) {
        List<ApartmentInfoEntity> dbList = mapper.selApartmentInfoList(param);

        if(dbList.size() > 0) {
            return;
        }

        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        String decodeServiceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";

        String deal_ym = String.format("%s%02d", param.getDeal_year(), param.getDeal_month());
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", param.getExternal_cd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("numOfRows", "1000")
                .build(false);
        System.out.println();
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> respEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
        String result = respEntity.getBody();
        System.out.println(result);

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        try {
            jsonNode = om.readTree(result);
            list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item")
                    , ApartmentInfoEntity[].class );
            System.out.println("list.length : " + list.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<LocationCodeEntity> locationList = mapper.selLocationCodeList(param);
        LocationCodeEntity code = locationList.get(0);

        InsEntity param2 = new InsEntity();
        param2.setLocation_cd(code.getCd());
        param2.setArr(list);

        mapper.insApartmentInfoArr(param2);



    }
}
