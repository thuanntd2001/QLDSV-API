package com.qlsvtc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.qlsvtc.mapper.RowMapper;
import com.qlsvtc.statics.InfoConnection;

public class AbstractDAO<T>  {

	// PARA

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				} else if (parameter instanceof Float) {
					statement.setFloat(index, (Float) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//PHANMANH


	

	public Connection getConnectionPM(HttpSession session) {
		try {
			Class.forName(InfoConnection.getDriverPM());
			String url = (String)session.getAttribute("url");
			String user = (String)session.getAttribute("username");
			String password = (String)session.getAttribute("password");
			try {
				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("ket noi that bai side PM");
				return null;
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		}
	}

	
	// CHU

	public Connection getConnectionChu() {
		try {
			Class.forName(InfoConnection.getDriverChu());
			String url = InfoConnection.getUrlChu();
			String user = InfoConnection.getUserNameChu();
			String password = InfoConnection.getPassWordChu();
			try {
				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("ket noi that bai side chu");
				return null;
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		}
	}

	public void updateChu(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnectionChu();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	
	public Long insertChu(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = getConnectionChu();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}


	public <T> List<T> queryChu(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnectionChu();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public <T> List<T> queryPM(String url ,String username, String password, String sql, RowMapper<T> rowMapper,
			Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnectionPM(url ,username, password);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			//System.out.print(statement.toString();
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public Connection getConnectionPM(String url, String rUser, String rPassword) {
		try {
			Class.forName(InfoConnection.getDriverPM());
			String user = rUser;
			String password = rPassword;
			try {
				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("ket noi that bai side PM");
				return null;
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public <T> List<T> queryPM(HttpSession session, String sql, RowMapper<T> rowMapper,
			Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnectionPM((String)session.getAttribute("url"),(String)session.getAttribute("username"),(String)session.getAttribute("password"));
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			//System.out.print(statement.toString();
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	

}
