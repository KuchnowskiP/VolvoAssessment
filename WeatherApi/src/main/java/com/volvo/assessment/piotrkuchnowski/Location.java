package com.volvo.assessment.piotrkuchnowski;

import java.math.BigDecimal;

public record Location(
        String name,
        String region,
        String country,
        BigDecimal lat,
        BigDecimal lon,
        String tz_id,
        Integer localtime_epoch,
        String localtime
) {

}
