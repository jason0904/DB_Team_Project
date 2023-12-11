package com.caudbdesign.dbTeamProject.User;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {

  private final UserRepository userRepository;
  public boolean validateLogin(String id, String pw) {
    Integer uid = userRepository.findByID(id);
    if(uid == null) {
      return false;
    }
    String password = userRepository.findPasswordHashByUID(uid);
    if(pw.equals(password)) {
      if(userRepository.getStatusByUID(uid).equals("inactive")) {
        return false;
      }
      userRepository.insertLoginLog(uid, "success");
      userRepository.updateLoginTime(uid);
      return true;
    } else {
      userRepository.insertLoginLog(uid, "fail");
      if(userRepository.getLoginAttemptByUID(uid) >= 10) {
        userRepository.updateStatusByUID(uid);
      }
      return false;
    }
  }

  public User getUserbyUID(int uid) {
    Optional<User> user = userRepository.findUserByUID(uid);
    return user.orElse(null);
  }


  public Integer getUIDbyID(String id) {
    return userRepository.findByID(id);
  }

  public UserinformationForm getUserInformation(int uid) {
    Address address = userRepository.getAddressByUID(uid);
    SensitiveInfo sensitiveInfo = userRepository.getSensitiveInfoByUID(uid);
    AdditionalContactInfo additionalContactInfo = userRepository.getAdditionalContactInfoByUID(uid);
    UserinformationForm userinformationForm = new UserinformationForm();
    userinformationForm.setUid(uid);
    userinformationForm.setAddress(address.getAddress());
    userinformationForm.setPostal_code(address.getPostal_code());
    userinformationForm.setCountry(address.getCountry());
    userinformationForm.setEmail(sensitiveInfo.getEmail());
    userinformationForm.setPhone(sensitiveInfo.getPhone());
    userinformationForm.setAge(sensitiveInfo.getAge());
    userinformationForm.setSex(sensitiveInfo.getSex());
    userinformationForm.setContactType(additionalContactInfo.getContactType());
    userinformationForm.setContactValue(additionalContactInfo.getContactValue());
    return userinformationForm;
  }

  public void updateUserInformation(UserinformationForm userinformationForm) {
    userRepository.updateAddressByUID(userinformationForm.getUid(), userinformationForm.getAddress(), userinformationForm.getPostal_code(), userinformationForm.getCountry());
    userRepository.updateAdditionalContactInfoByUID(userinformationForm.getUid(), userinformationForm.getContactType(), userinformationForm.getContactValue());
    userRepository.updateUserSensitiveInfoByUID(userinformationForm.getUid(), userinformationForm.getEmail(), userinformationForm.getPhone(), userinformationForm.getAge(), userinformationForm.getSex());
  }

}
