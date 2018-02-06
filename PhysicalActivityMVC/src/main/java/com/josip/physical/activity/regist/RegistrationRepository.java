package com.josip.physical.activity.regist;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;





public interface RegistrationRepository {
	//List<Registration> listaKorisnika();
	public List<Registration> spremiPodatke(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra) throws UnsupportedEncodingException;
	public Registration pronadjiPoOibu(String OIB) throws UnsupportedEncodingException;
	public List<Registration> ListUser(String email);
	public boolean updateUser(String oib,String ime,String prezime,String spol,Date datumRodjenja, String email,String sifra);
	public boolean deleteUser(String email);
}
