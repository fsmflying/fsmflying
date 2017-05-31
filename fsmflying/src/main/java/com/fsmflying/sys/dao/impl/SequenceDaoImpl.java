package com.fsmflying.sys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import com.fsmflying.sys.dao.ISequenceDao;
import com.fsmflying.sys.dm.SysSequence;

public class SequenceDaoImpl implements ISequenceDao, IDataSourceDao {

	DataSource dataSource;

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		this.dataSource = dataSource;
	}

	public Connection getConnection() {

		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("resource")
	@Override
	public int generateNextId(String keyName, int increment) {

		int value = -1;
		if (keyName == null || keyName.trim().isEmpty())
			throw new IllegalArgumentException(
					this.getClass().getCanonicalName() + ".generateNextId(..):keyName can not be null an empty !");
		else {
			keyName = keyName.toLowerCase().trim();
		}
		Connection conn = getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("select * from sys_sequences e where lower(e.keyName)=lower(?)");
			pstmt.setString(1, keyName);
			ResultSet rs = pstmt.executeQuery();

			SysSequence model = null;
			if (rs != null && rs.next()) {
				model = new SysSequence();
				model.setKeyName(rs.getString("keyName"));
				model.setNextValue(rs.getInt("nextValue"));
				model.setGeneratedTime(rs.getTimestamp("generatedTime"));
			}
			Date generatedTime = Calendar.getInstance().getTime();
			if (model != null) {
				value = model.getNextValue();

				model.setNextValue(value + increment);
				model.setGeneratedTime(new Timestamp(generatedTime.getTime()));

				pstmt = conn.prepareStatement("update sys_sequences set nextValue=?,generatedTime=? where keyName=?");

				pstmt.setInt(1, value + increment);
				pstmt.setTimestamp(2, new Timestamp(generatedTime.getTime()));
				pstmt.setString(3, keyName);
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, 1);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

			} else {
				value = 1;
				// model = new SysSequence();
				// model.setKeyName(keyName);
				// model.setNextValue(1);
				// model.setGeneratedTime(generatedTime);

				pstmt = conn
						.prepareStatement("insert into sys_sequences(keyName,nextValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return value;
	}

	@SuppressWarnings("resource")
	@Override
	public int[] generateNextIds(int generateCount, String keyName, int increment) {
		int value = -1;

		if (keyName == null || keyName.trim().isEmpty())
			throw new IllegalArgumentException(
					this.getClass().getCanonicalName() + ".generateNextId(..):keyName can not be null an empty !");
		else {
			keyName = keyName.toLowerCase().trim();
		}
		int[] values = new int[generateCount];
		for (int i = 0; i < values.length; i++) {
			values[i] = -1;
		}

		Connection conn = getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("select * from sys_sequences e where lower(e.keyName)=lower(?)");
			pstmt.setString(1, keyName);
			ResultSet rs = pstmt.executeQuery();

			SysSequence model = null;
			if (rs != null && rs.next()) {
				model = new SysSequence();
				model.setKeyName(rs.getString("keyName"));
				model.setNextValue(rs.getInt("nextValue"));
				model.setGeneratedTime(rs.getTimestamp("generatedTime"));
			}
			Date generatedTime = Calendar.getInstance().getTime();
			if (model != null) {
				value = model.getNextValue();
				model.setNextValue(value + increment * generateCount);
				model.setGeneratedTime(new Timestamp(generatedTime.getTime()));

				pstmt = conn.prepareStatement("update sys_sequences set nextValue=?,generatedTime=? where keyName=?");
				pstmt.setInt(1, value + increment * generateCount);
				pstmt.setTimestamp(2, new Timestamp(generatedTime.getTime()));
				pstmt.setString(3, keyName);
				pstmt.executeUpdate();

				for (int i = 0; i < values.length; i++) {
					values[i] = value + i * increment;
					if (i > 0) {
						pstmt = conn.prepareStatement(
								"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
						pstmt.setString(1, keyName);
						pstmt.setInt(2, values[i]);
						pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
						pstmt.executeUpdate();
					}
				}
				
				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment * generateCount);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

			} else {
				value = 1;
				// model.setNextValue(value + increment * generateCount);
				// model.setGeneratedTime(new
				// Timestamp(generatedTime.getTime()));

				pstmt = conn
						.prepareStatement("insert into sys_sequences(keyName,nextValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment * generateCount);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

				for (int i = 0; i < values.length; i++) {
					values[i] = value + i * increment;
					pstmt = conn.prepareStatement(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
					pstmt.setString(1, keyName);
					pstmt.setInt(2, values[i]);
					pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
					pstmt.executeUpdate();
				}
				
				pstmt = conn.prepareStatement(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(?,?,?)");
				pstmt.setString(1, keyName);
				pstmt.setInt(2, value + increment * generateCount);
				pstmt.setTimestamp(3, new Timestamp(generatedTime.getTime()));
				pstmt.executeUpdate();

			}

			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;
	}

}
