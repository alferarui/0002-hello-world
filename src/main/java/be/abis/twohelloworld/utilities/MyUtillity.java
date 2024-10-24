package be.abis.twohelloworld.utilities;

import be.abis.twohelloworld.model.Course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MyUtillity {
    public static void printCourse(Course course) {
        System.out.println(course);
    }
    public static void printCourses(List<Course> courses) {
        org.apache.commons.lang3.stream.Streams.of(courses)
                .forEach(System.out::println);
    }
    public static Double getStarDate1970(LocalDate ld){
        int currentYear = ld.getYear();
        int dayOfYear = ld.getDayOfYear();
        int totalDaysInYear = ld.lengthOfYear();
        double dayOfYearFraction = (double) dayOfYear / totalDaysInYear;
        return (currentYear - 1970) + (dayOfYearFraction);
    }

    public static boolean isNullOrEmpty(String obj) {
        return obj == null || obj.isEmpty();
    }

    public static String randomVowel(){
        String vowels = "aeiou";
        return vowels.charAt((int) (Math.random() * vowels.length())) + "";
    }

    public static String randomConsonant(){
        String consonants = "bcdfgjlmnprstuvz";
        return consonants.charAt((int) (Math.random() * consonants.length())) + "";
    }

    public enum SyllableModel{
        VCV("VCV"),
        CVC("CVC"),
        CV("CV"),
        VC("VC");

        private String value;
        SyllableModel(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
        public static SyllableModel randomModel(){
            SyllableModel[] models={VCV,CVC,CV,VC};
            return models[(int) (Math.random() * models.length)];
        }
    }
    public static String randomSyllable(SyllableModel model){
        return Arrays.stream(model.getValue().split(""))
                .map(chr -> {
                    switch (chr) {
                        case "V":
                            return randomVowel();
                        default:
                            return randomConsonant();
                    }
                })
                .collect(Collectors.joining(""));
    }
    public static List<Long> range(Long start,Long end,Long step) {
        ArrayList<Long> vals = new ArrayList<>();
        for(Long index = start;index<=end;index+=step){
            vals.add(index);
        }
        return vals;
    }
    public static List<Long> range(Integer start,Integer end,Integer step){
        return range(start.longValue(),end.longValue(),step.longValue());
    }
    public static String randomWord(Integer syllableCount){
        return range(1L,syllableCount.longValue(),1L).stream()
                .map(ix -> {
                    return randomSyllable(SyllableModel.randomModel());
                })
                .collect(Collectors.joining(""));
    }
    public static <T> T randomItem(T[] tokens){
        return tokens[(int) (Math.random() * tokens.length)];
    }

    public static String commonNames = "John, Mary, James, Patricia, Robert, Jennifer, Michael, Linda, William, Elizabeth";
    public static String randomCommonName(){
        return randomItem(commonNames.split(", "));
    }
    public static String familyNames = "Smith, Johnson, Brown, Taylor, Anderson, Thomas, Jackson, White, Harris, Martin";
    public static String randomFamilyName(){
        return randomItem(familyNames.split(", "));
    }
    public static String cities = "New York, Los Angeles, Chicago, Houston, Phoenix, Philadelphia, San Antonio, San Diego, Dallas, San Jose";
    public static String randomCity(){
        return randomItem(cities.split(", "));
    }
    public static String streetNames = "Main St, High St, Elm St, Oak St, Maple Ave, Cedar Rd, Pine Dr, Birch Ln, Walnut Blvd, Cherry Cir";
    public static String randomStreetName(){
        return randomItem(streetNames.split(", "));
    }
    public static String companyNames = "Google, Microsoft, Apple, Amazon, Facebook, IBM, Oracle, Intel, Tesla, Adobe";
    public static String randomCompanyName(){
        return randomItem(companyNames.split(", "));
    }
    public static String domainTLDs = "com, org, net, io, co, us, co.uk, co.ca, de, co.au, info, tw";
    public static String randomDomainTLD(){
        return randomItem(domainTLDs.split(", "));
    }
    public static String languageId = "en, es, fr, de, zh, ja, us, ar, pt, it";
    public static String randomLanguageId(){
        return randomItem(languageId.split(", "));
    }

    public static LocalDate randomLocalDate(String start,String end) {
        LocalDate startDate=LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate=LocalDate.parse(end,DateTimeFormatter.ISO_LOCAL_DATE);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println(daysBetween);
        long randomDays = ThreadLocalRandom.current().nextLong(0, daysBetween + 1);
        return startDate.plusDays(randomDays);
    }

    public static String getRandomPhoneNumber() {
        Random random = new Random();
        // Generate a random 10-digit number
        int areaCode = random.nextInt(900) + 100; // Ensure area code is 3 digits
        int centralOfficeCode = random.nextInt(900) + 100; // Ensure central office code is 3 digits
        int lineNumber = random.nextInt(10000); // 4-digit line number

        return String.format("(%03d) %03d-%04d", areaCode, centralOfficeCode, lineNumber);
    }

    public static String getRandomZipCode() {
        Random random = new Random();
        int zipCode = random.nextInt(90000) + 10000; // Ensures a 5-digit zip code between 10000 and 99999
        return String.format("%05d", zipCode);
    }


    public static final String[] descriptions = {
            "A thrilling tale of procrastination and coffee.",
            "The epic journey from couch to fridge.",
            "Adventures in avoiding laundry since 2023.",
            "An unintentional expert in the art of napping.",
            "Mastering the art of binge-watching like a pro.",
            "A pizza lover's guide to surviving Mondays.",
            "Running late: the autobiography.",
            "Certified cat video connoisseur.",
            "Living life one meme at a time.",
            "Expert at talking to pets like they understand.",
            "When life gives you lemons, make a mess in the kitchen and order takeout.",
            "The art of dodging phone calls: a masterclass in silent mode.",
            "Eating chips like nobody’s watching... even when everybody's watching.",
            "A highly caffeinated adventure through an unfinished to-do list.",
            "Defeating Wi-Fi dead zones, one awkward stance at a time.",
            "Conquering Mondays with nothing but sarcasm and a cold coffee.",
            "Accidentally becoming the nap champion of the world.",
            "Unlocking the mysteries of adulting, but deciding to take a nap instead.",
            "Getting lost in a grocery store because of an overcomplicated shopping list.",
            "Doing yoga once and now spiritually connected to snacks."
    };

    public static String randomDescription() {
        Random random = new Random();
        return descriptions[random.nextInt(descriptions.length)];
    }

    public static final String[] titles = {
            "Captain Procrastination: The Saga Continues",
            "Lord of the Fries: The Snack Returns",
            "The Chronicles of Naplandia",
            "Game of Scones: A Baking Adventure",
            "Harry Potter and the Cup of Coffee",
            "Star Woes: The Internet Strikes Back",
            "The Hitchhiker's Guide to Monday Mornings",
            "The Fellowship of the Remote",
            "Mission Impossible: Finding Clean Socks",
            "The Great Escape: Dodging Responsibilities",
            "Jurassic Bark: The Tale of a Dog's Backyard Adventures",
            "The Walking Dad: A Journey to Bedtime",
            "Indiana Jones and the Last Slice of Pizza",
            "The Office Escape Plan: Dodging Meetings",
            "To-Do List of the Dead: Tasks That Will Never Be Completed",
            "Mission: Procrastination – The Never-Ending Task",
            "The Fast and the Curious: Why Did I Walk Into This Room?",
            "Pirates of the Couch: The Remote’s Hidden Treasure",
            "The Lord of the Chores: Fellowship of Laundry",
            "Marvel's Latest Hero: Captain Clumsy and the Case of Spilled Coffee"
    };

    public static String randomTitle() {
        Random random = new Random();
        return titles[random.nextInt(titles.length)];
    }

}
