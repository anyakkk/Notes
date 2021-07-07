package hel.saving;

public class Saver {
    private String name;
    private String saver;


    public Saver(String name, String saver) {
        this.name = name;
        this.saver = saver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaver() {
        return saver;
    }

    public void setSaver(String saver) {
        this.saver = saver;
    }
}


