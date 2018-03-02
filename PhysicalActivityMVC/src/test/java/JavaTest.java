import java.text.SimpleDateFormat;
import java.util.Date;

import com.josip.physical.activity.walking.WalkingActivity;
import com.josip.physical.activity.walking.WalkingImplementation;

public class JavaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       WalkingImplementation implementation = new WalkingImplementation();
        Date date = new Date();
        //formatiranje datuma
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String vrijeme=sdf.format(date);
        implementation.spremiPodatke(new WalkingActivity(12.00,"1:75",20,12.56,"jbosnjak3@gmail.com",vrijeme));
        
	}

}
