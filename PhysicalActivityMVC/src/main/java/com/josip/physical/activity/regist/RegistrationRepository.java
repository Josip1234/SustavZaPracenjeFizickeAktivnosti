package com.josip.physical.activity.regist;

import java.io.IOException;
import java.util.List;

public interface RegistrationRepository {
	List<Registration> listaKorisnika() throws IOException;
}
