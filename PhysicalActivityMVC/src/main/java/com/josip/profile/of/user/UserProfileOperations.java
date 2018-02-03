package com.josip.profile.of.user;
import java.sql.Date;
import java.util.List;
public interface UserProfileOperations {
public List<UserProfileModel> updateUser(String oib,String ime,String prezime,String spol,Date datumRodjenja, String email,String sifra);
}
