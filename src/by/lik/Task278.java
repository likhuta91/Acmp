package by.lik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Task278 {

	private String dnkParentSequence;
	private String dnkChildSequence;

	public static void main(String[] args) {

		String inputPath = "C:\\INPUT.TXT";
		String outputPath = "C:\\OUTPUT.TXT";

		Task278 task = new Task278();
		task.checkDnk(inputPath, outputPath);

	}

	public void checkDnk(String inputPath, String outputPath) {

		String result;

		readDataFromFile(inputPath);

		if (comparisonOfTwoDnkSequences(dnkChildSequence, dnkParentSequence)) {
			result = "YES";
		} else {
			result = "NO";
		}

		writeDataInFile(outputPath, result);

	}

	public boolean comparisonOfTwoDnkSequences(String dnkChild, String dnkParent) {

		boolean result = false;

		String[] dChild = dnkChild.split("");
		String[] dParent = dnkParent.split("");

		int startChildIndex = 0;

		for (int i = 0; i < dParent.length; i++) {

			result = false;

			for (int j = startChildIndex; j < dChild.length; j++) {

				if (dParent[i].equals(dChild[j])) {

					startChildIndex = j + 1;
					result = true;
					break;
				}
			}

			if (result == false) {
				break;
			}

		}

		return result;
	}

	public void readDataFromFile(String inputPath) {

		try (FileInputStream fis = new FileInputStream(inputPath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {

			dnkParentSequence = bufferedReader.readLine();
			dnkChildSequence = bufferedReader.readLine();

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public void writeDataInFile(String outputPath, String text) {

		try (FileOutputStream fos = new FileOutputStream(outputPath);
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos))) {

			bufferedWriter.write(text + "\r");

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
