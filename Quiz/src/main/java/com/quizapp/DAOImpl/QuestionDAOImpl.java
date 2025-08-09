package com.quizapp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quizapp.DAO.QuestionDAO;
import com.quizapp.model.Question;
import com.quizapp.util.DBConnection;

public class QuestionDAOImpl implements QuestionDAO {

	 @Override
	    public List<Question> getQuestionsBySubject(int subjectId) {
	        List<Question> list = new ArrayList<>();
	        String sql = "SELECT * FROM questions WHERE subject_id=?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, subjectId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(mapQuestion(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    @Override
	    public List<Question> getRandomQuestions(int subjectId, int limit) {
	        List<Question> list = new ArrayList<>();
	        String sql = "SELECT * FROM questions WHERE subject_id=? ORDER BY RAND() LIMIT ?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, subjectId);
	            ps.setInt(2, limit);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(mapQuestion(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    private Question mapQuestion(ResultSet rs) throws SQLException {
	        return new Question(
	                rs.getInt("id"),
	                rs.getInt("subject_id"),
	                rs.getString("question_text"),
	                rs.getString("option_a"),
	                rs.getString("option_b"),
	                rs.getString("option_c"),
	                rs.getString("option_d"),
	                rs.getString("correct_option").charAt(0)
	        );
	    }
}
