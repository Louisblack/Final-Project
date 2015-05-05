package com.louishoughton.irrigator.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

;

@Entity
public class Error {

    @Id
    @GeneratedValue
    private int id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "EXECUTION_ID", nullable = false)
    private Execution execution;

    public Error(String message) {
        this.message = message;
    }

    public Error() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }
}
