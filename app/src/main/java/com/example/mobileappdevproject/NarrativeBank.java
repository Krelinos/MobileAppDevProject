package com.example.mobileappdevproject;


public class NarrativeBank {
    //declaring the objects

    List<Activity> list = new ArrayList<>();
    DatabaseManager databaseManager;

    //method to pull the narrative story

    //pull a question
    public String getActivity(int a){return list.get(a).getActivity();}

    //method will return a choice item in the list
    public String getChoice(int index, int num){
        return list.getChoice(index).getChoice(num-1); }

        //initialize database at the beginning
    public void initActivity(Context context){
        databaseManager = new DatabaseManager(context);
        list = databaseManager.getAllActivityList();

        if (list.isEmpty()){
            databaseManager.addInitialActivity(new Choice("activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice("activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));
            databaseManager.addInitialActivity(new Choice(" activity will go here",
                    new String[] {"choices here",} , "choices here"));

            list = databaseManager.getAllActivityList();
        }
    }
}
