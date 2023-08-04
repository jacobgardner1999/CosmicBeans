package Components;

import java.util.List;

public class Game {

    public void setupGame(Player player) {
        Choice startChoice = generateChoices();
        giveChoice(player, startChoice);
    }

    private Choice generateChoices() {
        OptionFactory optionFactory = new OptionFactory();
        ChoiceFactory choiceFactory = new ChoiceFactory();

        Choice c0_0 = choiceFactory.createChoice("Your alarm blares. 6:25am.");
        Choice c1_0 = choiceFactory.createChoice("You get out of bed and start to get ready for work. Jumping in the shower, you reach to grab a body wash from the pot stuck to your wall with plastic suckers. The two shower gels you own are labelled 'Invigorate for Men', promising to energise you for the day, and 'Allure', promising to make you irresistible to the people around you. ");
        Choice c1_1 = choiceFactory.createChoice("You sleep in, have to rush to get ready for work and are almost late. Whilst at work you think about the new coffee shop that opened up round the corner. You decide that you want to go there before work tomorrow. That evening, you go to sleep, determined to wake up early the next day.");
        Choice c2_0 = choiceFactory.createChoice("Dressed and ready for work, you leave your house with plenty of time to spare. As you stroll towards your offices you remember the quirky new coffee shop that just opened round the corner. It would only add a minute to your route, you think to yourself. ");
        Choice c2_1 = choiceFactory.createChoice("Your alarm blares. 6:25am.");
        Choice c3_0 = choiceFactory.createChoice("You detour away from your normal walk to work onto a street you've never been on before. In fact... you're certain this road wasn't here last time you walked through this way. You definitely need a coffee. As you get to the door you notice a sign in the window. \"Serving now, hot bean juice with milk\". Quirky... you think to yourself.");
        Choice c3_1 = choiceFactory.createChoice("The second you decide against going to the new shop you get a text from your boss. \"Coffee.\" I guess you're going to the coffee shop after all. You rush back so you can still make it in time for work.");
        Choice c3_2 = choiceFactory.createChoice("You sleep in again. Furious at yourself you run to work to get in on time. All day you chastise yourself for your laziness and think about how nice it would've been to go to the coffee shop. That evening you go to sleep, all the more determined to wake up in time to get a coffee in the morning.");
        Choice c4_0 = choiceFactory.createChoice("");
        Choice c4_1 = choiceFactory.createChoice("Your alarm blares. 6:25am.");
        Choice c5_1 = choiceFactory.createChoice("Yet again you sleep through your alarm. At work you are no longer furious, just resigned to your own laziness. You sit at your desk all day, waiting to go home. That evening you go to bed. You don't set your alarm.");
        Choice c6_1 = choiceFactory.createChoice("You continue to sleep in every day. You work. You go home. You sleep. Every day the same. One day at work you overhear a conversation about the local coffee shop closing down. Someone remarks about that being sad. You continue working. Continue sleeping in. Continue existing.");

        Option o1_0_0 = optionFactory.createOption("Wake up.", c1_0);
        Option o2_0_0 = optionFactory.createOption("Sleep in.", c1_1, new Traits(0, -5, 0, 0), new Traits());
        c0_0.addOption(List.of(o1_0_0, o2_0_0));

        Option o1_1_0 = optionFactory.createOption("Invigorate for Men.", c2_0, new Traits(0, 10, 0, 0), new Traits());
        Option o2_1_0 = optionFactory.createOption("Allure.", c2_0, new Traits(0, 0, 10, 0), new Traits());
        Option o3_1_0 = optionFactory.createOption("Mix the two together.", c2_0, new Traits(0, 5, 5, 0), new Traits());
        c1_0.addOption(List.of(o1_1_0, o2_1_0, o3_1_0));

        Option o1_1_1 = optionFactory.createOption("Go to sleep.", c2_1);
        c1_1.addOption(o1_1_1);

        Option o1_2_0 = optionFactory.createOption("Go to the coffee shop.", c3_0);
        Option o2_2_0 = optionFactory.createOption("Walk straight to work. \n (Requires 30 Hustle)", c3_1, new Traits(), new Traits(0, 30, 0, 0));
        c2_0.addOption(List.of(o1_2_0, o2_2_0));

        Option o1_2_1 = optionFactory.createOption("Wake up.", c1_0);
        Option o2_2_1 = optionFactory.createOption("Sleep in.", c3_2, new Traits(0, -5, 0, 0), new Traits());
        c2_1.addOption(List.of(o1_2_1, o2_2_1));

        Option o1_3_0 = optionFactory.createOption("Enter the shop.", c4_0, new Traits(5, 0, 0, 0), new Traits());
        c3_0.addOption(o1_3_0);

        Option o1_3_1 = optionFactory.createOption("Enter the shop", c4_0, new Traits(5, 0, 0, 0), new Traits());
        c3_1.addOption(o1_3_1);

        Option o1_3_2 = optionFactory.createOption("Go to sleep", c4_1);
        c3_2.addOption(o1_3_2);

        // TODO- add options for c4_0

        Option o1_4_1 = optionFactory.createOption("Wake up.", c1_0);
        Option o2_4_1 = optionFactory.createOption("Sleep in.", c5_1, new Traits(0, -5, 0, 0), new Traits());
        c4_1.addOption(List.of(o1_4_1, o2_4_1));

        Option o1_5_1 = optionFactory.createOption("Go to sleep", c6_1);
        c5_1.addOption(o1_5_1);

        return c0_0;
    }


    public void giveChoice(Player player, Choice choice) {
        player.setCurrentChoice(choice);
    }

}