package com.caudbdesign.dbTeamProject.Admin.Analystinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalystService {

  private final AnalystRepository analystRepository;

  public void deleteAnalyst(int analyst_id) {
    analystRepository.deleteAnalyst(analyst_id);
  }

}
