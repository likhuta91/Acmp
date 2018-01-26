package by.lik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Task557 {

	private int matrixSize;
	private int numberOfMatrix;
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
		int[][] matrix;
		int[] resultLineMultiplicationMatrix;
		
		try (FileInputStream fis = new FileInputStream(inputPath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis))) {
			
			readInitialDataFromFile(bufferedReader);

			matrix = readMatrixFromFile(bufferedReader);
			resultLineMultiplicationMatrix = matrix[elementLineNumber];
			
			for (int indexMatrix = 1; indexMatrix < numberOfMatrix; indexMatrix++) {

				matrix = readMatrixFromFile(bufferedReader);
				resultLineMultiplicationMatrix = multiplicationMatrix(resultLineMultiplicationMatrix, matrix);
			}
			
			searchNumber = resultLineMultiplicationMatrix[elementColumnNuber];

			writeDataInFile(outputPath, searchNumber);

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public int[] multiplicationMatrix(int[] lineFirstMatrix, int [][] secondMatrix) {

		int[] lineResultMatrix = new int[matrixSize];
		int elementResultMatrix = 0;

		for (int column = 0; column < matrixSize; column++) {

			elementResultMatrix = 0;

			for (int i = 0; i < matrixSize; i++) {

				elementResultMatrix = elementResultMatrix
						+ lineFirstMatrix[i] * secondMatrix[i][column];
			}

			if (elementResultMatrix >= simpleNumber) {
				elementResultMatrix = elementResultMatrix % simpleNumber;
			}

			lineResultMatrix[column] = elementResultMatrix;

		}

		return lineResultMatrix;
	}

	public int [][] readMatrixFromFile(BufferedReader bufferedReader) throws IOException {
		String[] elementInLine;
		bufferedReader.readLine();
		int[][] matrix = new int[matrixSize][matrixSize];

		for (int indexLineMatrix = 0; indexLineMatrix < matrixSize; indexLineMatrix++) {

			elementInLine = bufferedReader.readLine().split(" ");

			for (int indexColumnMatrix = 0; indexColumnMatrix < elementInLine.length; indexColumnMatrix++) {
				matrix[indexLineMatrix][indexColumnMatrix] = Integer.parseInt(elementInLine[indexColumnMatrix]);
			}

		}
		return matrix;
	}
	
	public void readInitialDataFromFile(BufferedReader bufferedReader) throws IOException {
		
		String[] elementInLine;
				
		elementInLine = bufferedReader.readLine().split(" ");
		numberOfMatrix = Integer.parseInt(elementInLine[0]);
		matrixSize = Integer.parseInt(elementInLine[1]);

		elementInLine = bufferedReader.readLine().split(" ");
		elementLineNumber = Integer.parseInt(elementInLine[0]) - 1;
		elementColumnNuber = Integer.parseInt(elementInLine[1]) - 1;

		simpleNumber = Integer.parseInt(bufferedReader.readLine());
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
