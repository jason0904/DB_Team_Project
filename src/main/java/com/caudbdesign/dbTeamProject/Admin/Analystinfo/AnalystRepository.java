package com.caudbdesign.dbTeamProject.Admin.Analystinfo;

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

  public boolean isAnalystExist(int analyst_id) {
    String sql = "select count(*) from AnalystInfo where analyst_id = ?";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, analyst_id);
    if(count == null) {
      return false;
    }
    return count != 0;
  }
}
