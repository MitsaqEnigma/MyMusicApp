package MyMusic.Control;

abstract class Files {

    private String Path;
    private String Availabilty;
    private String Owner;

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getAvailabilty() {
        return Availabilty;
    }

    public void setAvailabilty(String Availabilty) {
        this.Availabilty = Availabilty;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
}
