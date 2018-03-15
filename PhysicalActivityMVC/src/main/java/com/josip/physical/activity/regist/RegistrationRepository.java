package com.josip.physical.activity.regist;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;





public interface RegistrationRepository {

	public boolean spremiPodatke(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra) throws UnsupportedEncodingException;
	public boolean deleteUser(String email);
	public Registration korisnik(String username);
	public boolean Update(String oib,String ime, String prezime,String email,String sifra,String name);
}
