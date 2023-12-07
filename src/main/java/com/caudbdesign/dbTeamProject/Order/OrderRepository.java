package com.caudbdesign.dbTeamProject.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

  private final JdbcTemplate jdbcTemplate;


}
