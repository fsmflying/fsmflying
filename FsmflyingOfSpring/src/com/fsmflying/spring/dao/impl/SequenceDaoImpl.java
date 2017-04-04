package com.fsmflying.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fsmflying.sys.dao.ISequenceDao;

public class SequenceDaoImpl implements ISequenceDao {

	private JdbcTemplate mJdbcTemplate = new JdbcTemplate();

	public void setDataSource(DataSource ds) {
		this.mJdbcTemplate.setDataSource(ds);
	}

	class SysSequence {
		private String mKeyName;
		private int mNextValue;
		private Date mLastUpdateTime;

		public String getKeyName() {
			return mKeyName;
		}

		public void setKeyName(String keyName) {
			mKeyName = keyName;
		}

		public int getNextValue() {
			return mNextValue;
		}

		public void setNextValue(int nextValue) {
			mNextValue = nextValue;
		}

		public Date getLastUpdateTime() {
			return mLastUpdateTime;
		}

		public void setLastUpdateTime(Date lastUpdateTime) {
			mLastUpdateTime = lastUpdateTime;
		}
	}

	class SysSequenceMapper implements RowMapper<SysSequence> {
		public SysSequence mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysSequence model = new SysSequence();
			model.setKeyName(rs.getString("KeyName"));
			model.setNextValue(rs.getInt("NextValue"));
			model.setLastUpdateTime(rs.getDate("GeneratedTime"));
			return model;
		}
	}


	@Override
	public int generateNextId(String keyName, int increment) {
		if (keyName == null || "".equals(keyName.trim()))
			throw new IllegalArgumentException(
					"The value of 'keyName' must be not null and empty !");
		
			String keyname = keyName.toLowerCase().trim();
			List<SysSequence> list;

			list = this.mJdbcTemplate.query(
					"select * from sys_sequences where keyname=?",
					new Object[] { keyname }, new SysSequenceMapper());

			Date lastUpdateTime = Calendar.getInstance().getTime();
			if (list.size() == 0) {

				this.mJdbcTemplate
						.update("insert sys_sequences(keyname,nextvalue,GeneratedTime) values(?,?,?)",
								keyname, 1 + increment, lastUpdateTime);
				this.mJdbcTemplate
						.update("insert sys_sequencehistories(keyname,GeneratedValue,GeneratedTime) values(?,?,?)",
								keyname, 1, lastUpdateTime);
				return 1;
			} else {
				SysSequence model = this.mJdbcTemplate.queryForObject(
						"select * from sys_sequences where keyname=?",
						new Object[] { keyname }, new SysSequenceMapper());
				// greater than Integer.MAX_VALUE is not allows;
				if (model.getNextValue() == -1
						|| (Integer.MAX_VALUE - model.getNextValue() < increment)) {
					this.mJdbcTemplate
							.update("update sys_sequences set nextvalue=?,GeneratedTime=? where keyname=?",
									-1, lastUpdateTime, keyname);
					return -1;
				}
				this.mJdbcTemplate
						.update("update sys_sequences set nextvalue=?,GeneratedTime=? where keyname=?",
							model.getNextValue() + increment, lastUpdateTime,
							keyname);
			this.mJdbcTemplate
					.update("insert sys_sequencehistories(keyname,GeneratedValue,GeneratedTime) values(?,?,?)",
							keyname, model.getNextValue(),
							model.getLastUpdateTime());
			return model.getNextValue();
		}
		
	}


	@Override
	public int[] generateNextIds(int generateCount, String keyName, int increment) {

		if (generateCount <= 0)
			throw new IllegalArgumentException(
					"The value of 'generateCount' must be greater than 0 !");
		if (keyName == null || "".equals(keyName.trim()))
			throw new IllegalArgumentException(
					"The value of 'keyName' must be not null and empty !");
		int[] values = new int[generateCount];

		List<SysSequence> list = this.mJdbcTemplate.query(
				"select * from sys_sequences where keyname=?",
				new Object[] { keyName.toLowerCase().trim() },
				new SysSequenceMapper());

		String keyname = keyName.toLowerCase().trim();

		Date generatedTime = Calendar.getInstance().getTime();
		if (list.size() == 0) {

			this.mJdbcTemplate
					.update("insert sys_sequences(keyname,nextvalue,GeneratedTime) values(?,?,?)",
							keyname, increment * (generateCount - 1) + 2,
							generatedTime);
			for (int i = 0; i < values.length; i++) {
				values[i] = increment * i + 1;
				// greater than Integer.MAX_VALUE is not allows;
				if ((Integer.MAX_VALUE - values[i] < increment)) {
					values[i] = -1;
					if ((i > 0) && (values[i - 1] == -1))
						this.mJdbcTemplate
								.update("insert sys_sequencehistories(keyname,GeneratedValue,GeneratedTime) values(?,?,?)",
										keyname, -1, generatedTime);
					continue;
				}
				this.mJdbcTemplate
						.update("insert sys_sequencehistories(keyname,GeneratedValue,GeneratedTime) values(?,?,?)",
								keyname, values[i], generatedTime);
			}
			return values;
		} else {
			SysSequence model = this.mJdbcTemplate.queryForObject(
					"select * from sys_sequences where keyname=?",
					new Object[] { keyname }, new SysSequenceMapper());
			this.mJdbcTemplate
					.update("update sys_sequences set nextvalue=?,GeneratedTime=? where keyname=?",
							model.getNextValue() + increment
									* (generateCount - 1) + 1, generatedTime,
							keyname);
			for (int i = 0; i < values.length; i++) {
				values[i] = increment * i + model.getNextValue();
				this.mJdbcTemplate
						.update("insert sys_sequencehistories(keyname,GeneratedValue,GeneratedTime) values(?,?,?)",
								keyname, values[i], generatedTime);
			}
			return values;
		}
	}

	

}
