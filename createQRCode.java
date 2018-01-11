package myQRCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class createQRCode {
	
	private static String path;
	private static String writePath;
	private static boolean isPathSelected = false;
	static Random fileNameGenerator = new Random();
	
	public static void encondeQRCode(String msg) throws WriterException, IOException {
		
		int width = 300;
		int height = 300;
		String format = "png";
		String content = msg;
		HashMap map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		map.put(EncodeHintType.MARGIN, 1);
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, map);
		
		writePath = path.replaceAll("\\\\", "/");
		writePath  = path + "/img"+ fileNameGenerator.nextInt(10000) + msg.substring(0, 2)+".png";
		
		File file = new File(writePath);
		try {
			MatrixToImageWriter.writeToFile(bitMatrix, format, file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public static String getPath() {
		return path;
	}
	
	public static ImageIcon showQRCode() {
		ImageIcon icon = new ImageIcon(writePath);
		return icon;
	}
	
	public static void setPath(String path2) {
		path = path2;
		isPathSelected = false;
	}
	
}
