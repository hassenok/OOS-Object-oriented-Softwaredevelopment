public class Praktikum1 {

    public static class payment{
        private String Date; // Datum als String in Form : tt.mm.jj
        private double Amount; //Der zu sendenden Betrag
        public String Description; // Verwendungzweck
        private double incomingInterest;// Die Zinsen ,die bei einer Einzahlung anfallen (in Prozent)
        private double outgoingInterest;// Die Zinsen ,die bei einer Auszahlung anfallen (in Prozent)

        public payment(String s1,double d,String s2){
            setDate(s1);
            setAmount(d);
            setDescription(s2);
        }
        public payment( String s1,double d1,String s2,double d2,double d3){
            new payment(s1,d1,s2);
            setIncomingInterest(d2);
            setOutgoingInterest(d3);
        }
        // Copy Konstruktor
        public payment(payment p){
            this.setDate(p.getDate());
            this.setAmount(p.getAmount());
            this.setDescription(p.Description);
            this.setOutgoingInterest(p.getOutgoingInterest());
            this.setIncomingInterest(p.getIncomingInterest());
        }
        // Getter un Setter der Attribute
        void setDate(String s){Date=s;}
        public String getDate(){return Date;}
        void setAmount(double a){Amount=a;}
        public double getAmount(){return Amount;}
        void setDescription(String s1){Description=s1;}
        String getDescription(){return Description;}
        void setIncomingInterest(double i1){incomingInterest=i1;}
        public double getIncomingInterest(){return incomingInterest; }
        void setOutgoingInterest(double i2){outgoingInterest=i2;}
        public double getOutgoingInterest(){return outgoingInterest;}
        //die Attribute eines Objekts ausgeben
        void printObject(){
            System.out.println("Date : " + getDate());
            System.out.println("Amount : " + getAmount());
            System.out.println("Description : "+ getDescription());
            System.out.println("incomingInterest : "+getIncomingInterest());
            System.out.println("outgoingInterest : "+getOutgoingInterest());
        }
    }


    public static class transfer{
        private   String Date;// Datum als String in Form : tt.mm.jj
        private  double Amount;//Der zu sendenden Betrag
        public String Description;// Verwendungzweck
        private String sender; // Der Sende
        private String recipient;// Der Empfaenger

        transfer(String s1,double d,String s2){
            setDate(s1);
            setAmount(d);
            setDescription(s2);
        }
        transfer( String s1,double d1,String s2,String s3,String s4){
            new transfer(s1,d1,s2);
            this.setSender(s3);
            this.setRecipient(s4);
        }
        // Copy Konstruktor
        public transfer(transfer p){
            this.setDate(p.getDate());
            this.setAmount(p.getAmount());
            this.setDescription(p.Description);
            setSender(p.getSender());
            setRecipient(p.getRecipient());

        }
        // Getter un Setter der Attribute
        void setDate(String s){Date=s;}
        public String getDate(){return Date;}
        void setAmount(double a){Amount=a;}
        public double getAmount(){return Amount;}
        void setDescription(String s1){Description=s1;}
        String getDescription(){return Description;}
        void setSender(String s1){ sender=s1;}
        public String getSender(){return sender;}
        void setRecipient(String s2){recipient=s2;}
        public String getRecipient(){return recipient;}
        //die Attribute eines Objekts ausgeben
        void printObject(){
            System.out.println("Date : " + getDate());
            System.out.println("Amount : " + getAmount());
            System.out.println("Description : "+ getDescription());
            System.out.println("sender : "+getSender());
            System.out.println("recipient : "+ getRecipient());
        }


    }
    public static void main(String[] args){
        transfer Objekt1=new transfer("10.10.2005",250.5,"Miete");
        payment Objekt2=new payment("10.11.2011",300,"Kaution");
        transfer Objekt3=new transfer("10.10.2005",250.52,"test","X","Y");
        Objekt3.printObject();
        System.out.println();
        Objekt1.printObject();
        System.out.println();
        Objekt2.printObject();
    }




}
