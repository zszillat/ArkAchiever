package com.apps.arkachiever3.model;

import java.util.ArrayList;
import java.util.List;

public class DatabasePopulator {

    public static List<Milestone> milestoneList() {
        List<Milestone> milestoness = new ArrayList<>();
        // Add Age Milestones
        Milestone ageMilestone1 = new Milestone(1, 1, "Reach 18", "Turn 18 years old", Achievement.dateToInt("06-26-2020"), "age1.jpg");
        Milestone ageMilestone2 = new Milestone(2, 1, "Reach 21", "Turn 21 years old", Achievement.dateToInt("06-26-2023"), "age2.jpg");
        Milestone ageMilestone3 = new Milestone(3, 1, "Reach 30", "Turn 30 years old", Achievement.dateToInt("06-26-2032"), "age3.jpg");

        milestoness.add(ageMilestone1);
        milestoness.add(ageMilestone2);
        milestoness.add(ageMilestone3);

        // Add Read Books Achievements
        Milestone readBooksMilestone1 = new Milestone(4, 2, "Read 1 book", "Read your first book", 1, "books1.jpg");
        Milestone readBooksMilestone2 = new Milestone(5, 2, "Read 3 books", "Read 3 books in a year", 3, "books2.jpg");
        Milestone readBooksMilestone3 = new Milestone(6, 2, "Read 5 books", "Read 5 books in a year", 5, "books3.jpg");

        milestoness.add(readBooksMilestone1);
        milestoness.add(readBooksMilestone3);
        milestoness.add(readBooksMilestone2);

        // Add Cooking Achievements
        Milestone cookingMilestone1 = new Milestone(7, 3, "Cook 1 new recipe", "Try a new recipe", 1, "culinary1.jpg");
        Milestone cookingMilestone2 = new Milestone(8, 3, "Cook 5 new recipes", "Cook 5 different recipes", 5, "culinary2.jpg");
        Milestone cookingMilestone3 = new Milestone(9, 3, "Cook 10 new recipes", "Cook 10 different recipes", 10, "culinary3.jpg");
        Milestone cookingMilestone4 = new Milestone(10, 3, "Cook 50 new recipes", "Become a culinary master", 50, "culinary4.jpg");

        milestoness.add(cookingMilestone2);
        milestoness.add(cookingMilestone1);
        milestoness.add(cookingMilestone4);
        milestoness.add(cookingMilestone3);

        // Add Buying a House Achievements
        Milestone buyingHouseMilestone = new Milestone(11, 4, "Buy a home", "Achieve the dream of homeownership", 1, "house1.jpg");
        milestoness.add(buyingHouseMilestone);

        // Add Romantic Life Achievements
        Milestone romanticLifeMilestone1 = new Milestone(12, 5, "Get into a relationship", "Start a romantic journey", 1, "romantic1.jpg");
        Milestone romanticLifeMilestone2 = new Milestone(13, 5, "Get engaged", "Take the next step", 2, "romantic2.jpg");
        Milestone romanticLifeMilestone3 = new Milestone(14, 5, "Get married", "Celebrate the union of love", 3, "romantic3.jpg");

        milestoness.add(romanticLifeMilestone1);
        milestoness.add(romanticLifeMilestone2);
        milestoness.add(romanticLifeMilestone3);

        // Add Saving Money Achievements
        Milestone savingMoneyMilestone1 = new Milestone(15, 6, "Save $10,000", "Start saving money", 10000, "savings1.jpg");
        Milestone savingMoneyMilestone2 = new Milestone(16, 6, "Save $20,000", "Reach a savings goal", 20000, "savings2.jpg");
        Milestone savingMoneyMilestone3 = new Milestone(17, 6, "Save $50,000", "Achieve a significant savings milestone", 50000, "savings3.jpg");
        Milestone savingMoneyMilestone4 = new Milestone(18, 6, "Save $100,000", "Become a financial wizard", 100000, "savings4.jpg");

        milestoness.add(savingMoneyMilestone1);
        milestoness.add(savingMoneyMilestone2);
        milestoness.add(savingMoneyMilestone3);
        milestoness.add(savingMoneyMilestone4);

        return milestoness;
    }

    public static List<Achievement> achievementList() {
        List<Achievement> achievementss = new ArrayList<>();
        // Add Age Milestones
        Achievement ageAchievement = new Achievement(2, "age", "get older", "years old", Achievement.dateToInt("06-26-2002"), true, 0, true, 0);
        achievementss.add(ageAchievement);
        // Add Read Books Achievements
        Achievement readBooksAchievement = new Achievement(2, "Bookworm", "Read 50 books in a year", "books", 0, true, 0, false, 7);
        achievementss.add(readBooksAchievement);
        // Add Cooking Achievements
        Achievement cookingAchievement = new Achievement(3, "Culinary Master", "Cook 50 new recipes", "recipes", 0, true, 0, false, 0);
        achievementss.add(cookingAchievement);
        // Add Buying a House Achievements
        Achievement buyingHouseAchievement = new Achievement(4, "Homeowner", "Buy a home", "property", 0, false, 0, false, 0);
        achievementss.add(buyingHouseAchievement);
        // Add Romantic Life Achievements
        Achievement romanticLifeAchievement = new Achievement(5, "Love Story", "Get married", "relationship", 0, false, 0, false, 0);
        achievementss.add(romanticLifeAchievement);
        // Add Saving Money Achievements
        Achievement savingMoneyAchievement = new Achievement(6, "Financial Wizard", "Save $100,000", "money", 0, true, 0, false, 0);
        achievementss.add(savingMoneyAchievement);
        return achievementss;
    }
}
