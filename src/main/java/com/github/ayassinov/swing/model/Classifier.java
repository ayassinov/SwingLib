package com.github.ayassinov.swing.model;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ayassinov on 19/09/15.
 */
@Data
public class Classifier {

    private final int id;
    private final String name;
    private final Date createdAt;
    private final boolean active;
    private final Double defaultAmount;


    public static List<Classifier> listAll() {
        final Date todayDate = Calendar.getInstance().getTime();
        return ImmutableList.<Classifier>builder()
                .add(new Classifier(1, "Money Market", todayDate, true, 25.50))
                .add(new Classifier(2, "Gap-Balance", todayDate, true, 300.12))
                .add(new Classifier(3, "Total", todayDate, false, 10.50))
                .add(new Classifier(4, "Bonds", todayDate, true, 400.0))
                .build()
                .asList();
    }


}
