package ra.model.service.userService;

import ra.model.entity.User;
import ra.model.until.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceIMPL implements IUserService {

    @Override
    public List<User> findAll() {
        Connection conn = null;
        List<User> list = new ArrayList<>();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_getListUser()");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setRole(resultSet.getString("role"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_register(?,?)");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_updateUser(?,?,?,?,?,?,?,?)");
            callSt.setInt(1, user.getId());
            callSt.setString(2, user.getUserName());
            callSt.setString(3, user.getPassword());
            callSt.setString(4, user.getEmail());
            callSt.setString(5, user.getPhone());
            callSt.setString(6, user.getAddress());
            callSt.setString(7, user.getAvatar());
            callSt.setString(8, user.getRole());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public User findById(Integer userId) {
        Connection conn = null;
        User user = new User();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("call PROC_findUserById(?)");
            callableStatement.setInt(1, userId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return user;
    }

    @Override
    public boolean delete(Integer userId) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_deleteUser(?)");
            callSt.setInt(1, userId);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean checkExistsUsername(String userName) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_findUserByName(?)");
            callSt.setString(1, userName);
            ResultSet resultSet = callSt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean isValidPassword(String password) {
        String regex = "^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    @Override
    public User userLogin(User user) {
        Connection conn = null;
        User userLogin = null;
        int id = 0;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_login(?,?)");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            userLogin = findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return userLogin;
    }
}
