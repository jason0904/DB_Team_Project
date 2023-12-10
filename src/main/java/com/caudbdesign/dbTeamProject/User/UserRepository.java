package com.caudbdesign.dbTeamProject.User;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  @Transactional
  public void save(User user) {
    String sql = "insert into user (uid, username, usertype, account_status, created_at) values (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, user.getUid(), user.getUsername(), user.getUsertype(), user.getAccount_status(), user.getCreated_at());
  }

  public Optional<User> findUserByUID(int uid) {
    try {
      String sql = "select * from user where uid = ?";
      RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
      User user = jdbcTemplate.queryForObject(sql, rowMapper, uid);
      return Optional.of(user);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      // return Optional.empty();
    }
    return Optional.empty();
  }

  public Optional<Login> findByUID(int uid) {
    try {
      String sql = "select * from login where uid = ?";
      RowMapper<Login> rowMapper = BeanPropertyRowMapper.newInstance(Login.class);
      Login login = jdbcTemplate.queryForObject(sql, rowMapper, uid);
      return Optional.of(login);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public Integer findByID(String id) {
    try {
      String sql = "select uid from login where ID = ?";
      RowMapper<Login> rowMapper = BeanPropertyRowMapper.newInstance(Login.class);
      Login login = jdbcTemplate.queryForObject(sql, rowMapper, id);
      return login.getUid();
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public void updateStatusByUID(int uid) {
    //account_status = 'inactive'로 변경
    String sql = "update user set account_status = 'inactive' where uid = ?";
    jdbcTemplate.update(sql, uid);
  }

  public void updateAttemptByUID(int uid, int login_attempt) {
    //login_attempt = login_attempt + 1
    String sql = "update login set login_attempt = ? where uid = ?";
    jdbcTemplate.update(sql, login_attempt, uid);
  }

  public void updateUserSensitiveInfoByUID(int uid, SensitiveInfo sensitiveInfo) {

  }

}
