package com.example.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TestLocalDateTimeVO {
    private String name;

    private LocalDateTime localDateTime;

    private Date date;
}
