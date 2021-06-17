package com.koreait.exam;

import com.koreait.exam.vo.ApartmentInfoEntity;
import com.koreait.exam.vo.InsEntity;
import com.koreait.exam.vo.LocationCodeEntity;
import com.koreait.exam.vo.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Exam3Mapper {
    int insApartmentInfoArr(InsEntity param);
    List<ApartmentInfoEntity> selApartmentInfoList(SearchDTO param);
    List<LocationCodeEntity> selLocationCodeList(SearchDTO param);
}
