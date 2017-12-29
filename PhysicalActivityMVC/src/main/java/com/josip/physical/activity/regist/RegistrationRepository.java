package com.josip.physical.activity.regist;

import java.io.UnsupportedEncodingException;
import java.util.List;



public interface RegistrationRepository {
	//List<Registration> listaKorisnika();
	public List<Registration> spremiPodatke(String OIB,String ime,String prezime,String spol,String datumr,String email,String sifra);
	public Registration pronadjiPoOibu(String OIB) throws UnsupportedEncodingException;
}
