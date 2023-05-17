package ra.model.service.certificate;


import ra.model.entity.Certificate;
import ra.model.until.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CertificateIMPL implements ICertificateService {
    @Override
    public List<Certificate> findAll() {
        Connection connection = null;
        List<Certificate> certificates = new ArrayList<>();
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Cer_findAll()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(rs.getInt("id"));
                certificate.setCertificateName(rs.getString("certificateName"));
                certificate.setDescription(rs.getString("description"));
                certificate.setImg(rs.getString("img"));
                certificate.setType(rs.getInt("type"));
                certificates.add(certificate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return certificates;
    }

    @Override
    public boolean save(Certificate certificate) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Cer_save(?,?,?,?)");
            callSt.setString(1, certificate.getCertificateName());
            callSt.setString(2, certificate.getDescription());
            callSt.setString(3, certificate.getImg());
            callSt.setInt(4, certificate.getType());
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
    public boolean update(Certificate certificate) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Cer_update(?,?,?,?,?)");
            callSt.setInt(1, certificate.getId());
            callSt.setString(2, certificate.getCertificateName());
            callSt.setString(3, certificate.getDescription());
            callSt.setString(4, certificate.getImg());
            callSt.setInt(5, certificate.getType());
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
    public Certificate findById(int id) {
        Connection connection = null;
        Certificate certificate = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Cer_findById(?)");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                certificate = new Certificate();
                certificate.setId(rs.getInt("id"));
                certificate.setCertificateName(rs.getString("certificateName"));
                certificate.setDescription(rs.getString("description"));
                certificate.setImg(rs.getString("img"));
                certificate.setType(rs.getInt("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return certificate;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = connection.prepareCall("call PROC_Cer_delete(?)");
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
