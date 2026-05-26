package storage;

import java.io.*;
import java.util.ArrayList;

public class statisDb implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "database.ser";

    public static class Experiment implements Serializable {
        private static final long serialVersionUID = 1L;
        public enum enType {
            QUAL, QUAN, QUANCF
        }

        public enType type;
        private int classesNum;
        public ArrayList<String> samplesS;
        public ArrayList<Integer> samplesInt;
        public ArrayList<String> classes;
        public ArrayList<Integer> freq;
        public String experimentName;

        public int getClassesNum() {
            return classesNum;
        }

        @Override
        public String toString() {
            return experimentName + " (" + type + ")";
        }
    }

    private ArrayList<Experiment> expers;

    public statisDb() {
        loadFromFile();
    }

    public Experiment addExper(Experiment.enType type, String name, ArrayList<String> s, ArrayList<Integer> ints) {
        return addExper(type, name, s, ints, 0);
    }

    public Experiment addExper(Experiment.enType type, String name, ArrayList<String> s, ArrayList<Integer> ints,
            int classesNum) {
        if (getExperByName(name) != null) {
            throw new IllegalArgumentException("Experiment name already exists.");
        }

        Experiment ex = new Experiment();
        ex.type = type;
        ex.experimentName = name.trim();

        switch (type) {
            case QUAL:
                ex.samplesS = s;
                break;
            case QUAN:
                ex.samplesInt = ints;
                ex.classesNum = classesNum;
                break;
            case QUANCF:
                ex.freq = ints;
                ex.classes = s;
                break;
        }

        expers.add(ex);
        saveToFile();
        return ex;
    }

    public ArrayList<Experiment> getAllExperiments() {
        return expers;
    }

    public Experiment getExperiment(int index) {
        if (index >= 0 && index < expers.size()) {
            return expers.get(index);
        }
        return null;
    }

    public Experiment getExperByName(String name) {
        String searchedName = name.trim();

        for (int i = 0; i < expers.size(); ++i) {
            if (expers.get(i).experimentName.equalsIgnoreCase(searchedName)) {
                return expers.get(i);
            }
        }
        return null;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expers);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                expers = (ArrayList<Experiment>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                expers = new ArrayList<>();
                System.err.println("Error loading file, starting fresh: " + e.getMessage());
            }
        } else {
            expers = new ArrayList<>();
        }
    }
}
