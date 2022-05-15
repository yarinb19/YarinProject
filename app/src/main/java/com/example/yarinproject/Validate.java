package com.example.yarinproject;

import android.util.Patterns;

public class Validate {
    public static boolean checkUserName(String username){
        if(username.trim().equals(""))return false;
        return true;
    }
    public static boolean checkPassword(String password){
        if(password.trim().equals(""))return false;
        else{
            int countSmallLetter=0,countBigLetter=0,countDigit=0,countBlank=0,countSpacial=0;
            int n=password.length();
            char ch;
            for(int i=0;i<n;i++){
                ch=password.charAt(i);
                if(ch>='a'&&ch<='z')countSmallLetter++;
                else  if(ch>='A'&&ch<='Z')countBigLetter++;
                else  if(ch>='0'&&ch<='9')countDigit++;
                else  if(ch==' ')countBlank++;
                else countSpacial++;
            }
            if(countSmallLetter>0 && countBigLetter>0 && countDigit>0 &&
                    countSpacial>0 && countBlank==0)return true;
            return  false;
        }
    }
    public boolean checkEmail(String email){
        if(email.trim().equals(""))return false;
        else{
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches())return true;
            return  false;
        }
    }
    public boolean checkTelephone(String phone){
        if(phone.trim().equals("") || phone.charAt(0)!='0' ||
                phone.charAt(1)<'2' || phone.length()<9 || phone.length()>10)return false;
        else{
            if(Patterns.PHONE.matcher(phone).matches())return true;
            return  false;
        }
    }
}
