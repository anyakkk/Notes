package hel.saving;

public class SaverForm {
    private String name;
    private String keep;

    public SaverForm() {
    }

    public SaverForm(String name, String keep) {
        this.name = name;
        this.keep = keep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeep() {
        return keep;
    }

    public void setKeep(String keep) {
        this.keep = keep;
    }
}


