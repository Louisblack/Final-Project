package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.web.IrrigationRequest;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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
    private IrrigationRequest irrigationRequest;

    @OneToMany
    @Cascade(SAVE_UPDATE)
    private List<com.louishoughton.irrigator.job.Error> errors;

    public Execution() {
        this.dateRun = new Date();
    }

    public Execution(Forecast forecast, IrrigationRequest irrigationRequest) {
        this.forecast = forecast;
        this.irrigationRequest = irrigationRequest;
        this.dateRun = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
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
}
