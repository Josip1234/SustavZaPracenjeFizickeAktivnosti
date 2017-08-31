package com.josip.physical.activity.baza;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.josip.physical.activity.regist.Registration;

public class terstconn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String[] username={""};
	    char username2;
	    String[] username3={""};
		PhysicalActivityDatabase db= new PhysicalActivityDatabase();
		//System.out.println(db.listaKorisnika().get(2).getOIB());
		//System.out.println(db.listaKorisnika().get(2).getIme());
        //System.out.println(db.listaKorisnika().size());
        username[0]=db.listaKorisnika().get(0).getEmail();
        System.out.println(username[0]);
        System.out.println(username[0].toString()); 
        String a=username[0].toString();
        System.out.println(a.substring(0,1));
	}

}
