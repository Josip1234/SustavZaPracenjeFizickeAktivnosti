package org.example.physical.activity.web;

import java.util.List;
import com.example.physical.activity.regist.Registration;

public interface DatabaseReprository {
   
   List<Registration>ispisiImePrezimeMail(String ime,String prezime,String email);
}
