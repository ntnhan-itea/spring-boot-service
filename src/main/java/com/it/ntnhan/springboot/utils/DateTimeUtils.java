package com.it.ntnhan.springboot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {
    public static java.util.Date convertToSqlDateTime(java.time.LocalDate date) {
        return date == null
                ? null
                : java.util.Date
                .from(
                        date.atStartOfDay()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                );
    }

    public static java.util.Date convertToSqlDateTime(java.time.LocalDateTime dateTime) {
        return dateTime == null
                ? null
                : java.util.Date
                .from(
                        dateTime.atZone(ZoneId.systemDefault()).toInstant()
                );
    }

    public static java.time.LocalDate convertToLocalDate(java.util.Date date) {
        return date == null
                ? null
                : date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static java.time.LocalDate convertToLocalDate(Long epochMs) {
        return epochMs == null
                ? null
                : Instant.ofEpochMilli(epochMs)
                .atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static java.time.LocalDateTime convertToLocalDateTime(java.util.Date date) {
        return date == null
                ? null
                : date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static java.time.LocalDateTime convertToLocalDateTime(Long epochMs) {
        return epochMs == null
                ? null
                : Instant.ofEpochMilli(epochMs)
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
