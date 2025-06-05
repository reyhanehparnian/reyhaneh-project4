import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A program that loads and sorts student directory information.
 *
 * @author rparnian
 */
public class DirectorySort {

    /**
     * Contains all student records loaded from the directory file
     */
    private static SortableArrayList<Student> directory;

    /**
     * Main entry point that loads the directory and displays various sorted results.
     */
    public static void main(String[] args) {
        loadDirectory();

        System.out.println("Student with smallest SU box: " + getSmallestSU() + "\n");
        System.out.println("Student with largest SU box: " + getLargestSU() + "\n");
        System.out.println("First student by last name: " + getFirstByLastName() + "\n");
        System.out.println("Last student by last name: " + getLastByLastName() + "\n");
        System.out.println("Student with the most vowels: " + getMostVowels() + "\n");
        System.out.println("Student with the least vowels: " + getLeastVowels() + "\n");
        System.out.println("Student with the fanciest phone number: " + getFancyPhone() + "\n");
    }

    /**
     * Loads student data from "directory.txt" file into memory.
     */
    private static void loadDirectory() {
        directory = new SortableArrayList<>();
        try (Scanner scan = new Scanner(new File("directory-files/directory.txt"))) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                directory.add(new Student(line));
            }
        } catch (Exception e) {
            System.out.println("Failed to read directory file: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Comparator for sorting students by their Smith Union box number.
     */
    private static class SuBoxComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return Integer.compare(a.getSuBox(), b.getSuBox());
        }
    }

    /**
     * Comparator for sorting students alphabetically by last name.
     */
    private static class LastNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return a.getLastName().compareToIgnoreCase(b.getLastName());
        }
    }

    /**
     * Comparator for sorting students by number of vowels in their full name.
     */
    private static class VowelCountComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return Integer.compare(countVowels(a), countVowels(b));
        }

        /**
         * Counts the number of vowels in the student's full name (counts both uppercase and lowercase vowels).
         *
         * @return total count of vowels in the student's first and last name
         */
        public int countVowels(Student student) {
            String fullName = (student.getFirstName() + student.getLastName()).toLowerCase();
            int count = 0;
            for (char c : fullName.toCharArray()) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Comparator for sorting students by most frequently occurring digit in their phone number.
     */
    private static class PhoneDigitComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return Integer.compare(maxDigitCount(a.getPhone()), maxDigitCount(b.getPhone()));
        }

        /**
         * Helper method that counts the maximum occurrences of any digit in a phone number.
         * @param phone the phone number string to analyze
         * @return the highest count of any single digit
         */
        public int maxDigitCount(String phone) {
            int[] counts = new int[10];
            for (char c : phone.toCharArray()) {
                if (Character.isDigit(c)) {
                    int digit = Character.getNumericValue(c);
                    counts[digit]++;
                }
            }

            int max = 0;
            for (int count : counts) {
                if (count > max) {
                    max = count;
                }
            }
            return max;
        }
    }

    /**
     * Gets the student(s) with the smallest SU box number.
     * @return formatted string of student(s) information
     */
    public static String getSmallestSU() {
        directory.sort(new SuBoxComparator());
        int smallestBox = directory.get(0).getSuBox();
        String result = "";

        for (int i = 0; i < directory.size(); i++) {
            Student s = directory.get(i);
            if (s.getSuBox() == smallestBox) {
                result += s + "\n";
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) with the largest SU box number.
     * @return formatted string of student(s) information
     */
    public static String getLargestSU() {
        directory.sort(new SuBoxComparator());
        int largestBox = directory.get(directory.size() - 1).getSuBox();
        String result = "";

        for (int i = directory.size() - 1; i >= 0; i--) {
            Student s = directory.get(i);
            if (s.getSuBox() == largestBox) {
                result = s + "\n" + result;
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) who would appear first in a directory sorted by last name.
     * @return formatted string of student(s) information
     */
    public static String getFirstByLastName() {
        directory.sort(new LastNameComparator());
        String firstLastName = directory.get(0).getLastName();
        String result = "";

        for (int i = 0; i < directory.size(); i++) {
            Student s = directory.get(i);
            if (s.getLastName().equalsIgnoreCase(firstLastName)) {
                result += s + "\n";
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) who would appear last in a directory sorted by last name.
     * @return formatted string of student(s) information
     */
    public static String getLastByLastName() {
        directory.sort(new LastNameComparator());
        String lastLastName = directory.get(directory.size() - 1).getLastName();
        String result = "";

        for (int i = directory.size() - 1; i >= 0; i--) {
            Student s = directory.get(i);
            if (s.getLastName().equalsIgnoreCase(lastLastName)) {
                result = s + "\n" + result;
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) with the most vowels in their full name.
     * @return formatted string of student(s) information
     */
    public static String getMostVowels() {
        VowelCountComparator comparator = new VowelCountComparator();
        directory.sort(comparator);
        int maxVowels = comparator.countVowels(directory.get(directory.size() - 1));
        String result = "";

        for (int i = directory.size() - 1; i >= 0; i--) {
            Student s = directory.get(i);
            if (comparator.countVowels(s) == maxVowels) {
                result = s + "\n" + result;
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) with the least vowels in their full name.
     * @return formatted string of student(s) information
     */
    public static String getLeastVowels() {
        VowelCountComparator comparator = new VowelCountComparator();
        directory.sort(comparator);
        int minVowels = comparator.countVowels(directory.get(0));
        String result = "";

        for (int i = 0; i < directory.size(); i++) {
            Student s = directory.get(i);
            if (comparator.countVowels(s) == minVowels) {
                result += s + "\n";
            } else {
                break;
            }
        }

        return result.trim();
    }

    /**
     * Gets the student(s) with the fanciest phone number (most repeated digit).
     * @return formatted string of student(s) information
     */
    public static String getFancyPhone() {
        PhoneDigitComparator comparator = new PhoneDigitComparator();
        directory.sort(comparator);
        int maxCount = comparator.maxDigitCount(directory.get(directory.size() - 1).getPhone());
        String result = "";

        for (int i = directory.size() - 1; i >= 0; i--) {
            Student s = directory.get(i);
            if (comparator.maxDigitCount(s.getPhone()) == maxCount) {
                result = s + "\n" + result;
            } else {
                break;
            }
        }

        return result.trim();
    }
}