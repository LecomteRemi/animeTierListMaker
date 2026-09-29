package animeTierList.animeTierList;

import java.util.Objects;

public class MediaData {

    @Override
    public int hashCode() {
        return Objects.hash(id, mediaType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MediaData other = (MediaData) obj;
        return id == other.id && mediaType == other.mediaType;
    }

    private MediaType mediaType;

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getId() {
        return id;
    }

    private String originalName;
    private String englishName;
    private String imageURL;
    private int id;

    public MediaData(MediaType mediaType, int id, String originalName, String englishName, String imageURL) {
        this.mediaType = mediaType;
        this.originalName = originalName;
        this.englishName = englishName;
        this.imageURL = imageURL;
        this.id = id;
    }

}
