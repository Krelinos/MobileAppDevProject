package com.example.mobileappdevproject;

public class Choice {
    private int Id;
    private int ParentId;
    private String Dialog;
    public Choice( int newId, int newParentId, String newDialog ) {

        Id = newId;
        ParentId = newParentId;
        Dialog = newDialog;

    }

    public int getId() {
        return Id;
    }

    public int getParentId() {
        return ParentId;
    }

    public String getDialog() { return Dialog; }

    public void setId(int newId ) {
        Id = newId;
    }
    public void setParentId( int newParentId ) { ParentId = newParentId; }
    public void setDialog(String dialog) {
        Dialog = dialog;
    }
}