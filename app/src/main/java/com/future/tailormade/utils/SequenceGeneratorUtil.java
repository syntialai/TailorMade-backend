package com.future.tailormade.utils;

import com.future.tailormade.model.entity.base.Sequence;

import java.text.DecimalFormat;

public class SequenceGeneratorUtil {

    public static final String DESIGN = "DSGN";
    public static final String WISHLIST = "WLST";
    public static final String ORDER = "ORDR";

    public static String generateSequence(Sequence sequence) {
        return sequence.getId() + "_" + getCountString(sequence.getCount());
    }

    public static String getId(String type, String name) {
        return type + "_" + getName(name);
    }

    public static String getName(String name) {
        return name.toUpperCase().substring(0, 4);
    }

    private static String getCountString(Long count) {
        if (count < 1000) {
            return new DecimalFormat("0000").format(count);
        }
        return count.toString();
    }
}
