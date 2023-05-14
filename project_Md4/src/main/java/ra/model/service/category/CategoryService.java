package ra.model.service.category;

import ra.model.entity.Category;
import ra.model.until.ConnectionToDB;

import javax.xml.transform.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Category_GetAll()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public boolean save(Category category) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Category_save(?)");
            callSt.setString(1, category.getCategoryName());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return true;
    }

    @Override
    public boolean update(Category category) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Category_update(?,?)");
            callSt.setInt(1, category.getId());
            callSt.setString(2, category.getCategoryName());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return true;
    }

    @Override
    public Category findById(int id) {
        Connection connection = null;
        Category category = new Category();
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Category_findById(?)");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Category_delete(?)");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return true;
    }
}
