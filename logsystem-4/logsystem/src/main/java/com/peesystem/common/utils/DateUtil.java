package com.peesystem.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date StringToDate(String stringDate ,String formatStyle){
		SimpleDateFormat simpledateformat = new SimpleDateFormat(formatStyle);
		try {
			return simpledateformat.parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String DateToString(Date date,String formatStyle){
		SimpleDateFormat simpledateformat = new SimpleDateFormat(formatStyle);
		return simpledateformat.format(date);
	}
	
	//由出生日期获得年龄  
    public static  int getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);   
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);   
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }  
    
	public static Date DateToDate(Date date,String formatStyle){
		SimpleDateFormat dateFormater = new SimpleDateFormat(formatStyle);
		String dateStr = dateFormater.format(date);
		Date ss = null;
		try {
			ss = dateFormater.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ss;
	}

   public static  int[]  getDateLength(String  fromDate, String  toDate)  { 
	   String from = "";
	   String to = "";
	   String [] ss_fromDate = fromDate.split("-");
	   String [] ss_toDate = toDate.split("-");
	   	  for(String ss_f :ss_fromDate){
	   		from+=ss_f;
	   	  }
	   	  for(String ss_t :ss_toDate){
	   		to+=ss_t;
	   	  }
	      Calendar  c1  =  getCal(from); 
	      Calendar  c2  =  getCal(to); 
	      int[]  p1  =  {  c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)  }; 
	      int[]  p2  =  {  c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)  }; 
	      return  new  int[]  {  p2[0]  -  p1[0], p2[0]  *  12  +  p2[1]  -  p1[0]  *  12  -  p1[1], (int)  ((c2.getTimeInMillis()  -  c1.getTimeInMillis())  /  (24  *  3600  *  1000))  }; 
	   } 
   
   
   public  static  Calendar  getCal(String  date)  { 
	      Calendar  cal  =  Calendar.getInstance(); 
	      cal.clear(); 
	      cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))  -  1, Integer.parseInt(date.substring(6, 8))); 
	      return  cal; 
	   } 

   
   /**
    * 比较时间差
    * @param startDate
    * @param endDate
    */
   //1 minute = 60 seconds  
   //1 hour = 60 x 60 = 3600  
   //1 day = 3600 x 24 = 86400  
   public static Long getDifference(Date startDate, Date endDate){  
  
       //milliseconds  
       long different = endDate.getTime() - startDate.getTime();  
  
       long secondsInMilli = 1000;  
       long minutesInMilli = secondsInMilli * 60;  
       long hoursInMilli = minutesInMilli * 60;  
     //  long daysInMilli = hoursInMilli * 24;  
  
     /*  long elapsedDays = different / daysInMilli;  
       different = different % daysInMilli;  */
  
       long elapsedHours = different / hoursInMilli;  
     //  different = different % hoursInMilli;  
  
      /* long elapsedMinutes = different / minutesInMilli;  
       different = different % minutesInMilli;  
  
       long elapsedSeconds = different / secondsInMilli;  */
  
      return elapsedHours;
  
   }  
   
   
   
}
