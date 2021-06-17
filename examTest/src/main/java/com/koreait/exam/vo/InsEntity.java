package com.koreait.exam.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsEntity {
    private int location_cd;
    private ApartmentInfoEntity[] arr;
}
