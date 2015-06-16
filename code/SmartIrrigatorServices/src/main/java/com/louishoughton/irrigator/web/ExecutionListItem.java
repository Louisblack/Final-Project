package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.job.Execution;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class ExecutionListItem {

    public static final String RAIN_ICON = "rain_icon";
    public static final String SUN_ICON = "sun_icon";

    private String date;
    private boolean didIrrigate;
    private int irrigationDuration;
    private String iconClass = RAIN_ICON; // Pessimistically assume rain
    private List<Integer> ids = new ArrayList<>();

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    ExecutionListItem(String date, boolean didIrrigate, int irrigationDuration, String iconClass, List<Integer> ids) {
        this.date = date;
        this.didIrrigate = didIrrigate;
        this.irrigationDuration = irrigationDuration;
        this.iconClass = iconClass;
        this.ids = ids;
    }

    public ExecutionListItem(Date date, Collection<Execution> executions) {
        this.date = formatter.format(date);
        executions.stream().forEach(this::addExecutionData);
    }

    private void addExecutionData(Execution execution) {
        ids.add(execution.getId());
        if (execution.getIrrigationDuration() > 0) {
            didIrrigate = true;
            irrigationDuration += execution.getIrrigationDuration();
            iconClass = SUN_ICON;
        }
    }

    public String getDate() {
        return date;
    }

    public boolean isDidIrrigate() {
        return didIrrigate;
    }

    public int getIrrigationDuration() {
        return irrigationDuration;
    }

    public String getIconClass() {
        return iconClass;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
