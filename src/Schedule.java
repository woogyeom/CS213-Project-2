import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Schedule {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH = 4;
    private FitnessClass[] classes = new FitnessClass[4];
    private int numClasses;
    public void load(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] tokens = data.split(" +");

            if (tokens.length != 4) {
                throw new IllegalArgumentException("Expected 4 tokens, but found " + tokens.length);
            }

            Offer offer;
            try {
                offer = Offer.valueOf(tokens[0]);
            } catch (IllegalArgumentException e) {
                // Handle the case where tokens[0] does not match any Offer enum constant
                throw new IllegalArgumentException("The token " + tokens[0] + " is not a valid Offer.", e);
            }

            Instructor instructor;
            try {
                instructor = Instructor.valueOf(tokens[1]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The token " + tokens[1] + " is not a valid instructor", e);
            }

            Time time;
            try {
                time = Time.valueOf(tokens[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The token " + tokens[2] + " is not a valid time", e);
            }

            Location homeStudio;
            try {
                homeStudio = Location.valueOf(tokens[3].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The token " + tokens[3] + " is not a valid city.", e);
            }

            FitnessClass lesson = new FitnessClass(offer, instructor, homeStudio, time);
            add(lesson);
        }

    }

    private void grow() {
        FitnessClass[] newClasses = new FitnessClass[classes.length + GROWTH];
        for (int i = 0; i < numClasses; i++) {
            newClasses[i] = classes[i];
        }
        classes = newClasses;
    }

    private void add(FitnessClass lesson) {
        if (numClasses == classes.length) {
            grow();
        }

        classes[numClasses] = lesson;
        numClasses++;
    }
}
