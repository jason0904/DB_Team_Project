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
    String sql = "insert into User (uid, username, usertype, account_status, created_at) values (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, user.getUid(), user.getUsername(), user.getUsertype(), user.getAccount_status(), user.getCreated_at());
  }

  public Optional<User> findUserByUID(int uid) {
    try {
      String sql = "select * from User where uid = ?";
      RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
      User user = jdbcTemplate.queryForObject(sql, rowMapper, uid);
      return Optional.of(user);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      // return Optional.empty();
    }
    return Optional.empty();
  }


  public Integer findByID(String id) {
    try {
      String sql = "select uid from UserLoginMeta where login_id = ?";
      return jdbcTemplate.queryForObject(sql, Integer.class, id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public String findPasswordHashByUID(int uid) {
    try {
      String sql = "select password_hash from UserLoginPassword where uid = ?";
      return jdbcTemplate.queryForObject(sql, String.class, uid);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public void updateStatusByUID(int uid) {
    //account_status = 'inactive'로 변경
    String sql = "update User set account_status = 'inactive' where uid = ?";
    jdbcTemplate.update(sql, uid);
  }

  @Transactional
  public void insertLoginLog(int uid, String status) {
    String sql = "insert into UserLoginLog (uid, login_time, login_status) values (?, NOW(), ?)";
    jdbcTemplate.update(sql, uid, status);
  }

  public void updateLoginTime(int uid) {
    String sql = "update UserLoginPassword set updated_at = NOW() where uid = ?";
    jdbcTemplate.update(sql, uid);
  }

  public String getStatusByUID(int uid) {
    String sql = "select account_status from User where uid = ?";
    return jdbcTemplate.queryForObject(sql, String.class, uid);
  }

  public int getLoginAttemptByUID(int uid) {
    String sql = "select login_attempt from UserLoginPassword where uid = ?";
    return jdbcTemplate.queryForObject(sql, Integer.class, uid);
  }

  public void updateUserSensitiveInfoByUID(int uid, String email, String phone, int age, String sex) {
    String sql = "update SensitiveInfo set email = ?, phone = ?, age = ?, sex = ? where uid = ?";
    jdbcTemplate.update(sql, email, phone, age, sex, uid);
  }

  public void updateAdditionalContactInfoByUID(int uid, String contactType, String contactValue) {
    String sql = "update AdditionalContactInfo set contactType = ?, contactValue = ? where uid = ?";
    jdbcTemplate.update(sql,contactType, contactValue, uid);
  }

  public void updateAddressByUID(int uid, String address, int postal_code, String country) {
    String sql = "update Address set address = ?, postal_code = ?, country = ? where uid = ?";
    jdbcTemplate.update(sql, address, postal_code, country, uid);
  }

  public AdditionalContactInfo getAdditionalContactInfoByUID(int uid) {
    String sql = "select * from AdditionalContactInfo where uid = ?";
    RowMapper<AdditionalContactInfo> rowMapper = BeanPropertyRowMapper.newInstance(AdditionalContactInfo.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, uid);
  }

  public Address getAddressByUID(int uid) {
    String sql = "select * from Address where uid = ?";
    RowMapper<Address> rowMapper = BeanPropertyRowMapper.newInstance(Address.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, uid);
  }

  public SensitiveInfo getSensitiveInfoByUID(int uid) {
    String sql = "select * from SensitiveInfo where uid = ?";
    RowMapper<SensitiveInfo> rowMapper = BeanPropertyRowMapper.newInstance(SensitiveInfo.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, uid);
  }


}
