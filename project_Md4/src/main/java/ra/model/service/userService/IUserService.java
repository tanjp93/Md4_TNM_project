package ra.model.service.userService;

import ra.model.entity.User;
import ra.model.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    boolean checkExistsUsername (String user);
    boolean isValidPassword(String password);
    User userLogin(User user);
    List<User> findUserByUserName(String userName);
}
