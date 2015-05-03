package com.louishoughton.irrigator.job;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Error {

    @Id
    private int id;

    private String message;

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
}
