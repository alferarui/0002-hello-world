package be.abis.csvmagic;


import java.time.LocalDate;

class YouHoo{
    @MagicCsvId
    String name;
    @MagicCsvField(serializer = "",deserializer = "")
    String value;
    @MagicCsvField(serializer = "",deserializer = "")
    LocalDate modified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
