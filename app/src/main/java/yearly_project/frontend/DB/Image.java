package yearly_project.frontend.DB;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.lang.reflect.Field;

public class Image {
    private final static String type = "png";
    private final static String folderName = "Pictures";
    private String folder;
    private String name;
    private String path;
    private Moles moles;

    public Image(String name, String path, Mat mat) {
        this.name = name + "." + type;
        this.folder = path + "/" + folderName;
        this.path = this.folder + "/" + this.name;
        moles = new Moles();
        createImage(mat);
    }

    private void createImage(Mat mat) {
        new File(folder).mkdirs();
        Imgcodecs.imwrite(folder + "/" + name, mat);//
    }

    public String getFolder(){
        return folder;
    }

    public Bitmap getImageAsBitmap() {
        File imgFile = new  File(folder + "/" + name);
        if(imgFile.exists()){
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }

        return null;
    }

    public boolean verifyFields() throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.get(this) == null)
                return false;
        }

        return true;
    }

    public boolean verifyMoles() throws IllegalAccessException {
        boolean isValid = true;

        for(Mole mole : getMoles()){
            isValid = isValid && mole.isValidMole();
        }

        return isValid && getMoles().getSize()>0;
    }

    public String getPath() {
        return path;
    }

    public Moles getMoles() {
        return moles;
    }
}
