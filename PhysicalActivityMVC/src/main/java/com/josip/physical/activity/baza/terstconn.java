package com.josip.physical.activity.baza;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.josip.physical.activity.regist.Registration;

public class terstconn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    /*
	    char username2;
	    String[] username3;*/
		PhysicalActivityDatabase db= new PhysicalActivityDatabase();
		/*
		int size=db.listaKorisnika().size();
		String[] email=new String[size];
		String[] sifra=new String[size];
		//System.out.println(db.listaKorisnika().get(2).getOIB());
		//System.out.println(db.listaKorisnika().get(2).getIme());
        //System.out.println(db.listaKorisnika().size());
        //username[0]=db.listaKorisnika().get(0).getEmail().substring(1,11);
        //System.out.println(username[0]);
        
		for(int i=0;i<size;i++){
			email[i]=db.listaKorisnika().get(i).getEmail();
			System.out.println(email[i].substring(1, email[i].length()-1));
			sifra[i]=db.listaKorisnika().get(i).getSifra();
			System.out.println(sifra[i].substring(1, sifra[i].length()-1));
		}
		*/
		
		String polje[]=db.mojprofil("jbosnjak3@gmail.com");
        for (String string : polje) {
			System.out.println(string);
		}
        
        
	}

}
