package FileSystemSim;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a File Object containing info like name, size, date.
 */
public class FileSystemElement {
    public String name;
    public String timeStamp;
    public String size;

    public FileSystemElement(String name, String size) {
        this.name = name;
        this.size = size;
        this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Calendar lastModified) {
        this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
