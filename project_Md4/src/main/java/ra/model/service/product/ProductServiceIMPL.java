package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.until.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService {
    @Override
    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_getAllProduct()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName((rs.getString("productName")));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setPrice(rs.getLong("price"));
                product.setStoke(rs.getInt("stoke"));
                product.setTitle(rs.getString("title"));
                product.setImg(rs.getString("img"));
                product.setDescription1(rs.getString("description1"));
                product.setDescription2(rs.getString("description2"));
                product.setDescription3(rs.getString("description3"));
                product.setDescription4(rs.getString("description4"));
                product.setDescription5(rs.getString("description5"));
                listProduct.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listProduct;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_CreateNewProduct(?,?,?,?,?,?,?,?,?,?,?)");
            callSt.setString(1, product.getProductName());
            callSt.setInt(2, product.getCategoryId());
            callSt.setLong(3, product.getPrice());
            callSt.setInt(4, product.getStoke());
            callSt.setString(5, product.getTitle());
            callSt.setString(6, product.getImg());
            callSt.setString(7, product.getDescription1());
            callSt.setString(8, product.getDescription2());
            callSt.setString(9, product.getDescription3());
            callSt.setString(10, product.getDescription4());
            callSt.setString(11, product.getDescription5());
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
    public boolean update(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_updateProduct(?,?,?,?,?,?,?,?,?,?,?,?)");
            callSt.setInt(1, product.getId());
            callSt.setString(2, product.getProductName());
            callSt.setInt(3, product.getCategoryId());
            callSt.setLong(4, product.getPrice());
            callSt.setInt(5, product.getStoke());
            callSt.setString(6, product.getTitle());
            callSt.setString(7, product.getImg());
            callSt.setString(8, product.getDescription1());
            callSt.setString(9, product.getDescription2());
            callSt.setString(10, product.getDescription3());
            callSt.setString(11, product.getDescription4());
            callSt.setString(12, product.getDescription5());
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
    public Product findById(int productId) {
        Connection conn = null;
        Product product = new Product();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_findProductById(?)");
            callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setProductName((rs.getString("productName")));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setPrice(rs.getLong("price"));
                product.setStoke(rs.getInt("stoke"));
                product.setTitle(rs.getString("title"));
                product.setImg(rs.getString("img"));
                product.setDescription1(rs.getString("description1"));
                product.setDescription2(rs.getString("description2"));
                product.setDescription3(rs.getString("description3"));
                product.setDescription4(rs.getString("description4"));
                product.setDescription5(rs.getString("description5"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public boolean delete(int integer) {
        Connection conn = null;
        try {
            conn=ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_deleteProduct(?)");
            callSt.setInt(1, integer);
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
    public List<Product> findByName(String productName) {
        Connection conn = null;
        List<Product> listProduct = new ArrayList<>();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_Product_findProductByName(?)");
            callSt.setString(1, productName);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName((rs.getString("productName")));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setPrice(rs.getLong("price"));
                product.setStoke(rs.getInt("stoke"));
                product.setTitle(rs.getString("title"));
                product.setImg(rs.getString("img"));
                product.setDescription1(rs.getString("description1"));
                product.setDescription2(rs.getString("description2"));
                product.setDescription3(rs.getString("description3"));
                product.setDescription4(rs.getString("description4"));
                product.setDescription5(rs.getString("description5"));
                listProduct.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listProduct;
    }
}
