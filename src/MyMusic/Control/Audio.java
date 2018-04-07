package MyMusic.Control;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Audio extends Files {

    private String ID;
    private String title;
    private String artist;
    private String album;
    private int track;
    private int year;
    private String genre;
    private String composer;
    private String label;
    private String description;
    private String Lyric;
    private Image artwork;
    private FileInputStream imageOnBinary;
    private int imageSize;

    FileInputStream file;
    AudioStream audio;

    public Audio() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Image getArtwork() {
        return artwork;
    }

    public void setArtwork(Image artwork) {
        this.artwork = artwork;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLyric() {
        return Lyric;
    }

    public void setLyric(String Lyric) {
        this.Lyric = Lyric;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public FileInputStream getImageOnBinary() {
        return imageOnBinary;
    }

    public void setImageOnBinary(FileInputStream imageOnBinary) {
        this.imageOnBinary = imageOnBinary;
    }

    @Override
    public String getPath() {
        return super.getPath();
    }

    @Override
    public void setPath(String Path) {
        super.setPath(Path);
    }

    @Override
    public String getAvailabilty() {
        return super.getAvailabilty();
    }

    @Override
    public void setAvailabilty(String Availabilty) {
        super.setAvailabilty(Availabilty);
    }

    @Override
    public String getOwner() {
        return super.getOwner();
    }

    @Override
    public void setOwner(String Owner) {
        super.setOwner(Owner);
    }

    public AudioStream audioStream(AudioStream audio) {
        AudioPlayer.player.stop(audio);
        try {
            file = new FileInputStream(new File(getPath()));
            audio = new AudioStream(file);
        } catch (IOException e) {
            e.toString();
        }
        return audio;
    }

    public void stopStream() throws IOException {
        if (AudioPlayer.player.isAlive()) {
            file.close();
        }
    }

    public void stopStream(AudioStream audio) {
        AudioPlayer.player.stop(audio);
    }
}
