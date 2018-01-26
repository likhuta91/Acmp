package by.lik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Task557 {

	private int[][][] allMatrix;
	private int matrixSize;
	private int elementLineNumber;
	private int elementColumnNuber;
	private int simpleNumber;

	public static void main(String[] args) {

		String inputPath = "C:\\INPUT.TXT";
		String outputPath = "C:\\OUTPUT.TXT";
		Task557 task = new Task557();
		task.searchElement(inputPath, outputPath);

	}

	public void searchElement(String inputPath, String outputPath) {

		int searchNumber;
		readDataFromFile(inputPath);

		int[] resultLineMultiplicationMatrix = allMatrix[0][elementLineNumber];

		for (int i = 1; i < allMatrix.length; i++) {
			resultLineMultiplicationMatrix = multiplicationMatrix(resultLineMultiplicationMatrix, i);
		}

		searchNumber = resultLineMultiplicationMatrix[elementColumnNuber];

		writeDataInFile(outputPath, searchNumber);

	}

	public int[] multiplicationMatrix(int[] lineFirstMatrix, int indexSecondMatrix) {

		int[] lineResultMatrix = new int[matrixSize];
		int elementResultMatrix = 0;

		for (int column = 0; column < matrixSize; column++) {

			elementResultMatrix = 0;

			for (int i = 0; i < matrixSize; i++) {

				elementResultMatrix = elementResultMatrix
						+ lineFirstMatrix[i] * allMatrix[indexSecondMatrix][i][column];
			}

			if (elementResultMatrix >= simpleNumber) {
				elementResultMatrix = elementResultMatrix % simpleNumber;
			}

			lineResultMatrix[column] = elementResultMatrix;

		}

		return lineResultMatrix;
	}

	public void readDataFromFile(String inputPath) {

		String[] elementInLine;
		int[][] matrix;
		int numberOfMatrix;

		try (FileInputStream fis = new FileInputStream(inputPath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {

			elementInLine = bufferedReader.readLine().split(" ");
			numberOfMatrix = Integer.parseInt(elementInLine[0]);
			matrixSize = Integer.parseInt(elementInLine[1]);

			elementInLine = bufferedReader.readLine().split(" ");
			elementLineNumber = Integer.parseInt(elementInLine[0]) - 1;
			elementColumnNuber = Integer.parseInt(elementInLine[1]) - 1;

			simpleNumber = Integer.parseInt(bufferedReader.readLine());

			allMatrix = new int[numberOfMatrix][matrixSize][matrixSize];

			for (int indexMatrix = 0; indexMatrix < numberOfMatrix; indexMatrix++) {

				bufferedReader.readLine();
				matrix = new int[matrixSize][matrixSize];

				for (int indexLineMatrix = 0; indexLineMatrix < matrixSize; indexLineMatrix++) {

					elementInLine = bufferedReader.readLine().split(" ");

					for (int indexColumnMatrix = 0; indexColumnMatrix < elementInLine.length; indexColumnMatrix++) {
						matrix[indexLineMatrix][indexColumnMatrix] = Integer.parseInt(elementInLine[indexColumnMatrix]);
					}

				}

				allMatrix[indexMatrix] = matrix;
			}

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
