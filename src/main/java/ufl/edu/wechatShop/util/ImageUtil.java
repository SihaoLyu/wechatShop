package ufl.edu.wechatShop.util;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * handle thumbnail and return relative addr of new image
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.error("current relativeAddr: " + relativeAddr);
        File dest = new File(PathUtil.getImageBasePath() + relativeAddr);
        logger.error("current completeAddr: " + PathUtil.getImageBasePath() + relativeAddr);    // PathUtil basePath != this.basePath
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).
                    outputQuality(0.8f).toFile(dest);
        }
        catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;    // multi-deploy use
    }

    /**
     * create dir of target addr if these dirs not exist
     * @param targetAddr (relative path)
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * obtain input file extension name
     * @param cFile
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        String[] output = originalFileName.split(".");
        return output[output.length-1];
    }

    /**
     * generate random file name -- current yyyy/mm/dd/hh/mm/ss + 5-digit random num
     * @return
     */
    private static String getRandomFileName() {
        // get 5-digit
        int ranNum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + ranNum;
    }

    public static void main(String[] args) throws IOException {
//        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("/Users/sihaolyu/Documents/testImage/fm.jpg"))
        .size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
        .outputQuality(0.8f).toFile("/Users/sihaolyu/Documents/testImage/fmNew.jpg");

    }
}
