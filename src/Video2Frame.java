import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.FileChannelWrapper;
import org.jcodec.common.NIOUtils;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;
import org.jcodec.scale.AWTUtil;
import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc;

public class Video2Frame {
	public static void main(String[] args) throws IOException, JCodecException {

        long time = System.currentTimeMillis();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        for (int i = 0; i < 30; i++) { 
            Picture frame = FrameGrab.getNativeFrame(new File("C:/cygwin64/home/TonyYip/docman.mp4"), i);
            Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
            Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
            transform.transform(frame, rgb);
            BufferedImage bi = AWTUtil.toBufferedImage(rgb);
            String ScreenName = "C:/cygwin64/home/TonyYip/docman_"+i+".png";
            ImageIO.write(bi, "png", new File(ScreenName));
            //new TempMatch().run(ScreenName, templateFile, "C:/cygwin64/home/TonyYip/Match/result_" + i + ".png" , Imgproc.TM_CCOEFF);
        }
        System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
        
    }

	

}
