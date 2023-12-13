package com.caudbdesign.dbTeamProject.Analystinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnalystRepository {

  private final JdbcTemplate jdbcTemplate;

  public void deleteAnalyst(int analyst_id) {
    String sql = "delete from AnalystInfo where analyst_id = ?";
    jdbcTemplate.update(sql, analyst_id);
  }
}
