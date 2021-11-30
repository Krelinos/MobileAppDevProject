package com.example.mobileappdevproject;

public class Choice {
    private int Id;
    private int ParentId;
    private String Dialog;
    private String ButtonDialog;

    public Choice( int newId, int newParentId, String newDialog, String newButtonDialog ) {

        Id = newId;
        ParentId = newParentId;
        Dialog = newDialog;
        ButtonDialog = newButtonDialog;

    }

    public int getId() {
        return Id;
    }

    public int getParentId() {
        return ParentId;
    }

    public String getDialog() { return Dialog; }

    public String getButtonDialog() { return ButtonDialog; }

    public void setId(int newId ) {
        Id = newId;
    }
    public void setParentId( int newParentId ) { ParentId = newParentId; }
    public void setDialog(String dialog) {
        Dialog = dialog;
    }

    public void setButtonDialog(String buttonDialog) { ButtonDialog = buttonDialog; }
}