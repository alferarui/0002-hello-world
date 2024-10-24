package be.abis.twohelloworld.model;

import be.abis.csvmagic.MagicCsvId;

import java.util.Objects;

public class Course {

    public static final Course NULL = new Course ();

    private Integer courseId;
    @MagicCsvId
    private String shortTitle;
    private String longTitle;
    private Integer numberOfDays;
    private Integer pricePerDay;


    public Course(){}

    public Course(Integer courseId, String shortTitle, String longTitle, Integer numberOfDays, Integer pricePerDay) {
        this.courseId = courseId;
        this.shortTitle = shortTitle;
        this.longTitle = longTitle;
        this.numberOfDays = numberOfDays;
        this.pricePerDay = pricePerDay;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(shortTitle, course.shortTitle) && Objects.equals(longTitle, course.longTitle) && Objects.equals(numberOfDays, course.numberOfDays) && Objects.equals(pricePerDay, course.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, shortTitle, longTitle, numberOfDays, pricePerDay);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + getCourseId() +
                ", shortTitle=" + ((getShortTitle()==null)?"null":('"' + getShortTitle() + '"'))  +
                ", longTitle=" + ((getLongTitle()==null)?"null":('"' + getLongTitle() + '"'))  +
                ", numberOfDays=" + getNumberOfDays() +
                ", pricePerDay=" + getPricePerDay() +
                '}';
    }
}
