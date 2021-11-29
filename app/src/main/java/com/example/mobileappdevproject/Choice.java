package com.example.mobileappdevproject;

public class Choice {
    //private int Id;
    //private int ParentId;
   // private String Dialog;
   // private String BGImage;
    public String[] choice = new String[2];
    public String answer; //represents the answer the user presses
    public String activity;


    public void choice (String answer, String[] choices, String activity) {

     //   ParentId = newParentId;
       // Dialog = newDialog;
       // BGImage = newBGImage;

        this.choice = choices;
        this.choice[0] = choices[0];
        this.choice[1] = choices[1];
        this.activity = activity;

    }

    public String getActivity(){ return activity; }

    public String getChoice(int i){return choice[i]; }

    public String getAnswer(){return answer;}

    public void setAnswer(String Answer){
        this.answer = answer;
    }
    public void setChoice(int i, String choice){ this.choice[i] = choice; }

    public void setActivity(String activity){
        this.activity = activity;
    }

   // public int getId() {
     //   return Id;
    //}

    //public int getParentId() {
      //  return ParentId;
    //}

    //public String getDialog() { return Dialog; }

   // public java.lang.String getBGImage() {
     //   return BGImage;
    //}

  //  public void setId(int newId ) {
    //    Id = newId;
    //}
    //public void setParentId( int newParentId ) { ParentId = newParentId; }

    //public void setBGImage(String BGImage) {
      //  this.BGImage = BGImage;
    //}

    //public void setDialog(String dialog) {
        //Dialog = dialog;
    //}
}