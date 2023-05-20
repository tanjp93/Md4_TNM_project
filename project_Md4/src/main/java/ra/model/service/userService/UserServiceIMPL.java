package ra.model.service.userService;

import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.until.ConnectionToDB;

import java.sql.*;
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
            CallableStatement callSt = conn.prepareCall("call PROC_User_getListUser()");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setFullName(resultSet.getString("fullName"));
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
    public boolean save(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            conn.setAutoCommit(false);
            CallableStatement callSt = conn.prepareCall("call PROC_User_register(?,?,?)");
           callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int newUserId = callSt.getInt(3);
            CallableStatement callSt2 = conn.prepareCall("{call PROC_Create_cart(?)}");
            callSt2.setInt(1,newUserId);
            callSt2.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
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
            CallableStatement callSt = conn.prepareCall("call PROC_User_updateUser(?,?,?,?,?,?,?,?)");
            callSt.setInt(1, user.getId());
            callSt.setString(2, user.getFullName());
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
    public User findById(int userId) {
        Connection conn = null;
        User user = new User();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("call PROC_User_findUserById(?)");
            callableStatement.setInt(1, userId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setFullName(resultSet.getString("fullName"));
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
    public boolean delete(int userId) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_User_deleteUser(?)");
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
            CallableStatement callSt = conn.prepareCall("call PROC_User_findUserByName(?)");
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
    public UserLogin userLogin(User user) {
        Connection conn = null;
        UserLogin userLogin = null;

        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_User_login(?,?)");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                userLogin = new UserLogin();
                userLogin.setId(resultSet.getInt("id"));
                userLogin.setUserName(resultSet.getString("userName"));
                userLogin.setFullName(resultSet.getString("fullName"));
                userLogin.setPhone(resultSet.getString("phone"));
                userLogin.setAddress(resultSet.getString("address"));
//                userLogin.setCartId(resultSet.getInt("cart_id"));
                userLogin.setRole(resultSet.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return userLogin;
    }
    @Override
    public  List<User> findUserByUserName(String userName){
        Connection conn = null;
        List<User> listUserSearch=new ArrayList<>();
        try{
            conn=ConnectionToDB.getConnectionToDB();
            CallableStatement callSt= conn.prepareCall("call PROC_User_findUserByName(?)");
            callSt.setString(1,userName);
            ResultSet resultSet=callSt.executeQuery();
            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setFullName(resultSet.getString("fullName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setRole(resultSet.getString("role"));
                listUserSearch.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listUserSearch;
    }
}
