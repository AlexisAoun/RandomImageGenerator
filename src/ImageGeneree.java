import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.awt.*;

public class ImageGeneree extends JComponent{
    Random graine = new Random();
    Expr expR;
    Expr expG;
    Expr expB;
    RenderedImage im;

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    Expr randomExpr(int level){
        if(level==0){
            if(graine.nextBoolean())
                return(new X());
            else 
                return (new Y());
        }
        else{
            Expr e;
            switch(graine.nextInt(4)){
                case 0:
                    e = new Sin(randomExpr(level-1));
                    break;
                case 1:
                    e = new Cos(randomExpr(level-1));
                    break;
                case 2:
                    e = new Moyenne(randomExpr(level-1),randomExpr(level-1));
                    break;
                case 3:
                    e = new Mult(randomExpr(level-1),randomExpr(level-1));
                    break;
                default:
                    e = new Expr();
                    break;
            }
            return e;
        }

        
    }

    void construitImage (int width, int height){
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int r = (int) (255* expR.eval((float)i/width, (float)j/height));
                int g = (int) (255* expG.eval((float)i/width, (float)j/height));
                int b = (int) (255* expB.eval((float)i/width, (float)j/height));
                
                r = Math.max(r, 0);
                b = Math.max(b, 0);
                g = Math.max(g, 0);
                
                buff.setRGB(i,j,(new Color(r,g,b)).getRGB());
            }
        }
        im = buff;
    }

    void ranGenImage(int width, int height, int levelR, int levelG, int levelB){
        expR = randomExpr(levelR);
        expG = randomExpr(levelG);
        expB = randomExpr(levelB);
        construitImage(width, height);
    }

    public void paint (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRenderedImage(im, null);
    }

    public void saveImage(){

        String filename = formatter.format(java.time.LocalDateTime.now());
        try{
            File f = new File (filename);
            ImageIO.write(im,"jpg",f);
        }catch (IOException e){
            System.out.println("Failed to save the image !");
        }
    }
}
