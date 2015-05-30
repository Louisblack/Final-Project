package com.louishoughton.irrigator.web;

import java.util.List;


public interface ListExecutionsService {

    List<ExecutionListItem> list(int from);
}
