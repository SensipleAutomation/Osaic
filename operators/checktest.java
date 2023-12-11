package operators;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checktest {

	public static void main(String[] args) {
		
		
		Date now1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_YY_hh_mm_ss");
		String time1 = dateFormat.format(now1);
		System.out.println("timestamp : "+time1);
		// TODO Auto-generated method stub

	}

}
