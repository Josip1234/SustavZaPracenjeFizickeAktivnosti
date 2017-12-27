package com.josip.physical.activity.summary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SummaryImplementation implements SummaryRepository {
@Autowired
SummaryActivity summar;
	@Override
	public List<SummaryActivity> show() {
		List<SummaryActivity> list=new ArrayList<SummaryActivity>();
		summar.setKorisnik("jbosnjak3@gmail.com");
		summar.setPrijedjeniKilometri(1.565);
		summar.setProsjecnaBrzina(15);
		summar.setUkupanBrojKoraka(15);
		summar.setUkupnoVrijeme("10 minuta");
		list.add(new SummaryActivity(
				summar.getKorisnik(),summar.getUkupanBrojKoraka(),summar.getUkupnoVrijeme(),summar.getPrijedjeniKilometri(),summar.getProsjecnaBrzina()
				
				
				));
		
		return list;
	}

}
