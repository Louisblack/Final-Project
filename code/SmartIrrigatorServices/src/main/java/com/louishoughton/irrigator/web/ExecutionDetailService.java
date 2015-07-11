package com.louishoughton.irrigator.web;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Louis on 31/05/15.
 */
public interface ExecutionDetailService {

    DayDetailItem get(String ids) throws ParseException;
}
