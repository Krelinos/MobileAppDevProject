package com.example.mobileappdevproject;

public class Choice {
    private int Id;
    private int ParentId;
    private String Dialog;
    private String BGImage;

    public Choice( int newId, int newParentId, String newDialog, String newBGImage ) {

        ParentId = newParentId;
        Dialog = newDialog;
        BGImage = newBGImage;

    }

    public int getId() {
        return Id;
    }

    public int getParentId() {
        return ParentId;
    }

    public String getDialog() { return Dialog; }

    public java.lang.String getBGImage() {
        return BGImage;
    }

    public void setId(int newId ) {
        Id = newId;
    }
    public void setParentId( int newParentId ) { ParentId = newParentId; }

    public void setBGImage(String BGImage) {
        this.BGImage = BGImage;
    }

    public void setDialog(String dialog) {
        Dialog = dialog;
    }
}