package com.example.javajoke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joker {

    private static final List<String> jokes = new ArrayList<>();
    private static final Random rand = new Random();

    // Jokes from https://www.short-funny.com
    static {
        jokes.add("Read those really good short jokes and find yourself laughing like a hyena.");
        jokes.add("How did the bunny rob a snowman?\n\nHe took out his hair dryer and said: Give me that carrot!");
        jokes.add("Why did the bee marry?\n\nHe’s finally found his honey.");
        jokes.add("What do you get when you wake up on a workday and realize you ran out of coffee?\n\nA depresso.");
        jokes.add("I used to breed rabbits. Then I realized they can handle it themselves.");
        jokes.add("What can you share and yet keep at the same time?\n\nAn STD for example.");
        jokes.add("Why are eggs not very much into jokes?\n\nBecause they could crack up.");
        jokes.add("What do you call the soft tissue between a shark's teeth?\n\nA slow swimmer.");
        jokes.add("Mom! I’m a 3d printer!\n\nOh come on, Tommy, close the door when you poop.");
        jokes.add("What’s black, red, black, red, black, red?\n\nA zebra with a sun burn.");
        jokes.add("My friend was planning to get a Labrador. Is he mad?! Hasn’t he seen how many of their owners go blind?!”");
        jokes.add(" What is green and sits crying in the corner?\n\nThe incredible Sulk.");
    }

    public static String getJoke() {
        int index = rand.nextInt(jokes.size());
        return jokes.get(index);
    }
}
