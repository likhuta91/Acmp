package by.lik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Task579 {

	@SuppressWarnings("unused")
	private int lengthSequence;
	private String[] allSequenceElement;

	private int lengthSearchSequence;
	private String dataSearchSequence;

	public static void main(String[] args) {

		String inputPath = "C:\\INPUT.TXT";
		String outputPath = "C:\\OUTPUT.TXT";

		Task579 task = new Task579();
		task.searchSequence(inputPath, outputPath);

	}

	public void searchSequence(String inputPath, String outputPath) {

		readDataFromFile(inputPath);

		takeDataNewSequence();

		writeDataInFile(outputPath, lengthSearchSequence, dataSearchSequence);

	}

	private void takeDataNewSequence() {

		int indexElementIncrement;

		int sequenceElement;

		int lengthPositiveSequence = 0;
		int lengthNegativeSequence = 0;

		int sumPositiveSequenceElement = 0;
		int sumNegativeSequenceElement = 0;

		String allPositiveSequenceElement = "";
		String allNegativeSequenceElement = "";

		for (int i = 0; i < allSequenceElement.length; i++) {

			indexElementIncrement = i + 1;
			sequenceElement = Integer.parseInt(allSequenceElement[i]);

			if (sequenceElement > 0) {

				lengthPositiveSequence++;
				sumPositiveSequenceElement = sumPositiveSequenceElement + sequenceElement;
				allPositiveSequenceElement = allPositiveSequenceElement + indexElementIncrement + " ";

			} else if (sequenceElement < 0) {

				lengthNegativeSequence++;
				sumNegativeSequenceElement = sumNegativeSequenceElement + sequenceElement;
				allNegativeSequenceElement = allNegativeSequenceElement + indexElementIncrement + " ";

			}
		}

		if (sumPositiveSequenceElement > -sumNegativeSequenceElement) {

			lengthSearchSequence = lengthPositiveSequence;
			dataSearchSequence = allPositiveSequenceElement.substring(0, allPositiveSequenceElement.length() - 1);

		} else if (sumPositiveSequenceElement < -sumNegativeSequenceElement) {

			lengthSearchSequence = lengthNegativeSequence;
			dataSearchSequence = allNegativeSequenceElement.substring(0, allNegativeSequenceElement.length() - 1);

		} else {

			lengthSearchSequence = 1;
			dataSearchSequence = "1";

		}
	}

	public void readDataFromFile(String inputPath) {

		try (FileInputStream fis = new FileInputStream(inputPath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {

			lengthSequence = Integer.parseInt(bufferedReader.readLine());
			allSequenceElement = bufferedReader.readLine().split(" ");

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public void writeDataInFile(String outputPath, int lengthSequence, String allSequenceElement) {

		try (FileOutputStream fos = new FileOutputStream(outputPath);
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"))) {

			bufferedWriter.write(lengthSequence + "\n");
			bufferedWriter.write(allSequenceElement);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}