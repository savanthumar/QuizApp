package com.quizapp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quizapp.DAO.SubjectDAO;
import com.quizapp.model.Subject;
import com.quizapp.util.DBConnection;

public class SubjectDAOImpl implements SubjectDAO {
	
	@Override
    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Subject(rs.getInt("id"), rs.getString("subject_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Subject getSubjectById(int id) {
        String sql = "SELECT * FROM subjects WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getInt("id"), rs.getString("subject_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
