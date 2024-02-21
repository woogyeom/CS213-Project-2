import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Schedule {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH = 4;
    private FitnessClass[] classes = new FitnessClass[4];
    private int numClasses;
    public void load(File file) throws IOException {
        System.out.println("-Fitness classes loaded-");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.trim().isEmpty()) {
                continue;
            }

            String[] tokens = data.split(" +");

            Offer offer;
            offer = Offer.valueOf(tokens[0].toUpperCase());

            Instructor instructor;
            instructor = Instructor.valueOf(tokens[1].toUpperCase());

            Time time;
            time = Time.valueOf(tokens[2].toUpperCase());

            Location homeStudio;
            homeStudio = Location.valueOf(tokens[3].toUpperCase());

            FitnessClass lesson = new FitnessClass(offer, instructor, homeStudio, time);
            add(lesson);
            System.out.println(lesson);
        }

        System.out.println("-end of class list.");

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

    public int getNumClasses() {
        return numClasses;
    }

    public FitnessClass[] getClasses() {
        return classes;
    }
    public FitnessClass find(Offer classInfo, Instructor instructor, Location studio) {
        FitnessClass fitnessClass = new FitnessClass(classInfo, instructor, studio, null);
        for (int i = 0; i < numClasses; i++) {
            if (classes[i].equals(fitnessClass)) {
                return classes[i];
            }
        }
        return null;
    }
}
