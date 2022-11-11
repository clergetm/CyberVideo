package fc.Films;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Film is an abstract class that can implements the bluray class and the QRcode class
 * @author Clarisse
 *
 */
public class Film {

	protected String title;
	protected String synopsis;
	protected String[] actors;
	protected String FNameDirector;
	protected String LNameDirector;
	protected AgeRestriction restriction;
	protected Categories[] categories;
	
	
	/**
	 * Constructor of the class Film
	 * @param title is the title of the film
	 * @param synopsis is the summary of the film
	 * @param actors is the list of the actors of the film
	 * @param FNDirector is the first name of the director of the film
	 * @param LNDirector is the last name of the director of the film
	 * @param restriction is the age restriction of the films
	 * @param categories is the list of the different categories to which the film belongs
	 */
	public Film(String title, String synopsis, String[] actors, String FNDirector,
			String LNDirector, AgeRestriction restriction, Categories[] categories) {
		this.title=title;
		this.synopsis=synopsis;
		this.actors=actors;
		this.FNameDirector=FNDirector;
		this.LNameDirector=LNDirector;
		this.restriction=restriction;
		this.categories=categories;
	}
	
	/**
	 * Allows to know the film support
	 * @return the film support "QRCode" only
	 */
	public String getType() { 
		return "QRCode";
	}

	/**
	 * 
	 * @return the title of the film
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return the synopsis of the film
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * 
	 * @return the actors of the film : 
	 * "fNameActeur1 lNameActeur1, fNameActeur2 lNameActeur2" 
	 */
	public String getActors() {
		String actorsList="";
		for (int i=0;i<actors.length-1;i++) {
			actorsList+=actors[i]+", ";
		}
		actorsList+=actors[actors.length-1];
		return actorsList;
	}

	/**
	 * 
	 * @return the names of the director of the film :
	 * "fnameDir lnameDir"
	 */
	public String getNamesDirector() {
		return FNameDirector+" "+LNameDirector;
	}

	/**
	 * 
	 * @return the age restriction of the film
	 */
	public AgeRestriction getRestriction() {
		return restriction;
	}

	/**
	 * Generate the QRCode of the link of the film
	 * Create a file QRCode.png with the QRCode
	 * @return the link of the film
	 * 
	 * @return the categories of the film
	 * @see Ressources generator : https://www.digitalocean.com/community/tutorials/java-qr-code-generator-zxing-example
	 */
	public String generateQRCode() {
		String link = "https://www.cybervideo/location/"+this.getTitle()+".com";

		try {
		String filePath = "QRCode.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = null;
		byteMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);

		
		} catch (IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return link;
	}
	
}
