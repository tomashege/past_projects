public class roman{
  public static void main (String[] args){
    int N = Integer.parseInt(args[0]);
    String ans = "";
    int n = N;
   while (n>0){
     if(n>=1000){
       ans += "M";
       n -= 1000;
     }
     else if(n>=900){
      ans += "CM";
      n -= 900;
     }
     else if(n>=500){
       ans +=  "D";
       n -= 500;
     }
     else if(n>=400){
       ans +=  "CD";
       n -= 400;
     }
     else if(n>=100){
       ans += "C";
       n -= 100;
     }
     else if (n>=90){
       ans +=  "XC";
       n -= 90;
     }
     else if (n>=50){
       ans +=  "L";
       n -= 50;
     }
     else if (n>=40){
       ans +=  "XL";
       n -= 40;
     }
     else if (n>=10){
       ans +=  "X";
       n -= 10;
     }
     else if (n>=9){
       ans += "IX";
       n -= 9;
     }
     else if (n>=5){
       ans += "V";
       n -= 5;
     }
     else if (n>=4){
       ans += "IV";
      n -= 4;
     }
     else {
        ans += "I";
        n -= 1;
     }
   }
   System.out.println(N +": " + ans);
}
}
