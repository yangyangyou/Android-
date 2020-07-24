package cn.itcast.ssm.method;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;


public  class  MD5{
	public String getPassword(String password) throws UnsupportedEncodingException
	{
		if(password==null){
			password="123456";
		}
		  MessageDigest md5= null;
		try
		{
			 md5 = MessageDigest.getInstance("MD5");	        
		} 
		catch (Exception e) 
		{
			 e.printStackTrace();
		}
		byte[] byteArray = password.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        //StringBuffer hexValue = new StringBuffer();
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) 
        {
      	  int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));     		
        }
        return hexValue.toString();
}	
		

	
}
