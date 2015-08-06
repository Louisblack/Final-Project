package com.louishoughton.irrigator.job;

import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.louishoughton.irrigator.valve.IrrigationValveException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.forecast.History;
import com.louishoughton.irrigator.web.Error;
import com.louishoughton.irrigator.web.IrrigationRequest;

@Entity
@Immutable
public class Execution {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private Date dateRun;

    @OneToOne
    @Cascade(SAVE_UPDATE)
    private Forecast forecast;


    @OneToOne
    @Cascade(SAVE_UPDATE)
    private History history;

    @OneToOne
    @Cascade(SAVE_UPDATE)
    private IrrigationRequest irrigationRequest;

    @OneToMany(mappedBy = "execution", fetch = FetchType.EAGER)
    @Cascade(SAVE_UPDATE)

    private List<com.louishoughton.irrigator.web.Error> errors = new ArrayList<>();

    public Execution() {
        this.dateRun = new Date();
    }

    public Execution(Forecast forecast, History history) {
        this.forecast = forecast;
        this.history = history;
    }

    public Execution(Forecast forecast, History history, IrrigationRequest irrigationRequest) {
        this.forecast = forecast;
        this.irrigationRequest = irrigationRequest;
        this.history = history;
    }

    public Execution(Forecast forecast, History history, IrrigationRequest irrigationRequest,
                     List<Error> errors) {
        this(forecast, history, irrigationRequest);
        this.errors = errors;
        errors.stream().forEach(error -> error.setExecution(this));

    }

    public Execution(Error error, Error... errors) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
        this.errors.addAll(Arrays.asList(errors));
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRun() {
        return dateRun;
    }

    public void setDateRun(Date dateRun) {
        this.dateRun = dateRun;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public IrrigationRequest getIrrigationRequest() {
        return irrigationRequest;
    }

    public void setIrrigationRequest(IrrigationRequest irrigationRequest) {
        this.irrigationRequest = irrigationRequest;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public Date getDateRunWithNoTime() {
        Date dateCopy = new Date(dateRun.toInstant().toEpochMilli());
        return DateUtils.truncate(dateCopy, Calendar.DATE);
    }

    public int getIrrigationDuration() {
        try {
            return irrigationRequest != null ? irrigationRequest.getSeconds() : 0;
        } catch (IrrigationValveException e) {
            return 0;
        }
    }

    public double getHistoricalHighestInchesPerHour() {
        return history != null ? history.getHighestInchesPerHour() : 0;
    }

    public boolean hasErrors() {
        return errors != null && errors.size() > 0;
    }

    public double getForecastChanceOfRainPercentage() {
        return forecast != null ? forecast.getChanceOfRainPercentage() : 0;
    }

    public double getForecastInchesPerHour() {
        return forecast != null ? forecast.getInchesPerHour() : 0;
    }

    public double getForecastMaximumTemperature() {
        return forecast != null ? forecast.getMaximumTemperature() : 0;
    }

    public List<String> getErrorMessages() {
        return errors.stream().map(e -> e.getMessage()).collect(toList());
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
