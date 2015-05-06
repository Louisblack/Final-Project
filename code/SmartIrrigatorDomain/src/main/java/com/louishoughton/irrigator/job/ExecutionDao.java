package com.louishoughton.irrigator.job;

public interface ExecutionDao {

    void save(Execution execution);

    Execution get(int id);
}
