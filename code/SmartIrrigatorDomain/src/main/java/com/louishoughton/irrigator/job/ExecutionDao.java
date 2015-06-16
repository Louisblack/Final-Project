package com.louishoughton.irrigator.job;

import java.util.List;

public interface ExecutionDao {

    void save(Execution execution);

    Execution get(int id);

    List<Execution> get(List<Integer> ids);

    List<Execution> list();

    List<Execution> list(int from, int to);
}
