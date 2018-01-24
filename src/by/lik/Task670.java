package by.lik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Task670 {

	private int numberInInput;

	public static void main(String[] args) {

		String inputPath = "C:\\INPUT.TXT";
		String outputPath = "C:\\OUTPUT.TXT";

		Task670 task = new Task670();
		task.searchNumber(inputPath, outputPath);

	}

	public void searchNumber(String inputPath, String outputPath) {

		int numberInOutput;

		readDataFromFile(inputPath);

		numberInOutput = takeIndexNumber(numberInInput);

		if (numberInOutput != 0) {
			writeDataInFile(outputPath, numberInOutput);
		}

	}

	public int takeIndexNumber(int number) {

		char[] indexToChar;
		int sumUniqueNumber = 0;
		int requiredNumber = 0;

		for (int i = 1; i > 0; i++) {

			indexToChar = String.valueOf(i).toCharArray();
			Arrays.sort(indexToChar);
			if (i < 10) {
				sumUniqueNumber++;
			}
			for (int j = 0; j < indexToChar.length - 1; j++) {

				if (indexToChar[j] == indexToChar[j + 1]) {
					break;
				}
				if (j == indexToChar.length - 2) {
					sumUniqueNumber++;
				}

			}

			if (sumUniqueNumber == number) {
				requiredNumber = i;
				break;
			}

		}

		return requiredNumber;
	}

	public void readDataFromFile(String inputPath) {

		try (FileInputStream fis = new FileInputStream(inputPath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {

			numberInInput = Integer.parseInt(bufferedReader.readLine());

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeDataInFile(String outputPath, int number) {

		try (FileOutputStream fos = new FileOutputStream(outputPath);
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos))) {

			bufferedWriter.write(number + "\r");

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
