package edu.uoc.pfc2012.edusalva.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import edu.uoc.pfc2012.edusalva.bean.KoncepteParaula;
import edu.uoc.pfc2012.edusalva.db.DBController;

/**
 * Classe per fer c&agrave;rregues massives de paraules a la base de dades.
 *
 * Aquesta classe &eacute;s executable, i cont&eacute; el m&egrave;tode per fer c&agrave;rregues des d'un
 * fitxer de text, cap a una base de dades MongoDB.
 *
 * <p>
 * Projecte Final de Carrera - Desenvolupament d'aplicacions m&#242;bils en HTML5
 * </p>
 *
 * <p>
 * Data: Gener de 2013
 * </p>
 *
 * @author Eduard Capell Brufau (<a href="mailto:ecapell@uoc.edu">ecapell@uoc.edu</a>)
 * @author Salvador Lorca Sans (<a href="salvinha@uoc.edu">salvinha@uoc.edu</a>)
 *
 * @version 1.0
 *
 */
public class LoadDatabase {

	public static final int COLUMN_KANJI = 0;
	public static final int COLUMN_CATALAN = 1;
	public static final int COLUMN_PRONJAP_1 = 2;
	public static final int COLUMN_PRONJAP_2 = 3; // La que he d'utilitzar.

	public static final int MIN_COLUMNS = 4;

	public static final String COLUMN_SEPARATOR = "\t";

	/**
	 * Objecte Logger.
	 */
	private static final Logger logger = Logger.getLogger(LoadDatabase.class.getName());


	/**
	 * El m&egrave;tode principal.
	 * @param args
	 */
	public static void main(String[] args) {
		// Log4j console appender.
		org.apache.log4j.BasicConfigurator.configure();


		String filename = "/Users/edu/Desktop/words.txt";
		processFile(filename);


	}


	private static void processFile(String filename) {
		int maxlines = 30;
		List<String> lines = getLines(filename, maxlines);

		if (lines == null || lines.size() == 0) {
			return;
		}

		for (String s: lines) {
			String[] values = s.split(COLUMN_SEPARATOR);
			if (values != null && values.length >= MIN_COLUMNS) {
				logger.info(values[COLUMN_KANJI] + "/" + values[COLUMN_CATALAN] + "/" + values[COLUMN_PRONJAP_2]);
			}

			KoncepteParaula k = new KoncepteParaula();
			DBController.createKoncept(k);
		}
	}


	private static List<String> getLines(String filename, int maxlines) {
		try {
			File f = new File(filename);
			if (f.exists() && f.isFile() && f.canRead()) {
				logger.info("File is OK.");
				BufferedReader in = new BufferedReader(new FileReader(f));

				List<String> lines = new Vector<String>();
				String s = null;
				int n = 0;
				while ((s = in.readLine()) != null) {
					lines.add(s);
					n++;
					if (n == maxlines) {
						break;
					}
				}
				in.close();

				return lines;
			} else {
				throw new Exception("No es pot llegir el fitxer!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
