package com.josip.profile.of.user;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Repository
public class UserProfileImplementation implements UserProfileOperations {
	
	
	
	
	
	
	@Override
	public List<UserProfileModel> updateUser(String oib, String ime, String prezime, String spol, Date datumRodjenja,
			String email, String sifra) {
		// TODO Auto-gened method stub
		return null;
	}

	@Override
	public boolean deleteUser(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserProfileModel> ListUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
