package de.mwiesner.firstdroid.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Week {
    public int getWeek_nr() {
        return week_nr;
    }

    public void setWeek_nr(int week_nr) {
        this.week_nr = week_nr;
    }

    @JsonProperty("week_nr")
    public int week_nr;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @JsonProperty("start_date")
    public String start_date;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @JsonProperty("end_date")
    public String end_date;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @JsonProperty("month")
    public String month;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonProperty("person")
    public Person person;
}