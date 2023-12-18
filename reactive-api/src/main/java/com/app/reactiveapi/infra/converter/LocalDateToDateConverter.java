package com.app.reactiveapi.infra.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDate;

public class LocalDateToDateConverter implements Converter<LocalDate, Date>  {
    @Override
    public Date convert(LocalDate locDate) {
        return locDate == null ? null : Date.from(Instant.from(locDate));
    }
}
