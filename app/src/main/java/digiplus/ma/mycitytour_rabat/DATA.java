package digiplus.ma.mycitytour_rabat;

/**
 * Object contains resource ids
 */
public class DATA {

    private int mName;
    private int mDescription;
    private int mMusicResource;
    private int mImage = NO_IMAGE;
    private static final int NO_IMAGE = -1;

    public DATA(int Name, int Description, int MusicResource) {
        mName = Name;
        mDescription = Description;
        mMusicResource = MusicResource;
    }

    public DATA(int Name, int Description, int Image,
                int MusicResource) {
        mName = Name;
        mDescription = Description;
        mMusicResource = MusicResource;
        mImage = Image;
    }

    public int getName() {
        return mName;
    }

    public int getDescription() {
        return mDescription;
    }

    public int getImage() {
        return mImage;
    }

    public boolean hasImage() {
        return mImage != NO_IMAGE;
    }

    public int getMusicResource() {
        return mMusicResource;
    }
}
