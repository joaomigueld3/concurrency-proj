package codigosLuis;

class TestesBasicosThreads {

  static void println(String str) {
     System.out.println(str);
  }

//Exemplos Básicos de Threads 
   static void ex1() throws Exception {
       println("Exemplo 1");
       Thread t1 = new Thread(new Runnable() {
          public void run() {
              for (int c=0;c<10;c++)
                 println("ola da thread 1 - " + c);
          }
       });
       Thread t2 = new Thread(new Runnable() {
          public void run() {
              for (int c=0;c<10;c++)
                 println("ola da thread 2 - " + c);
          }
       });
       t1.start(); t2.start(); //Executa as threads em paralelo
       t1.join(); t2.join();   //Espera que as threads terminem
       println("Fim do Exemplo 1");
       
   }
   
//Exemplos Básicos de Threads Usando expressões Lambda
   static void ex2() throws Exception {
       println("Exemplo 2");
       Thread t1 = new Thread( () -> {
              for (int c=0;c<10;c++)
                 println("ola da thread 1 - " + c);
       });
       Thread t2 = new Thread(() -> {
              for (int c=0;c<10;c++)
                 println("ola da thread 2 - " + c);
       });
       t1.start(); t2.start(); //Executa as threads em paralelo
       t1.join(); t2.join();   //Espera que as threads terminem
       println("Fim do Exemplo 2");
       
   }
   
   
   
   
   public static void main(String args[]) throws Exception {
      ex1();
      ex2();
   }

}
