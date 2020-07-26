package uk.ac.brunel.cs1702;

public class SudokuVerifier {

	public int[][] sudokuArray = new int[9][9];

	public int verify(String candidateSolution){
		
		// You can tell which function tests which rule,
		// by looking at the return statements
		// (remove negative sign, to get the rule value)
		
		if (candidateSolution.length() != 81) {
			return -5;
		} else if (!candidateSolution.matches("[1-9]+")) {
			return -1;
		} else {
			createSudokuArray(candidateSolution);
			
			if (checkRowValid() == false) {
				return -3;
			} else if (checkColumnValid() == false) {
				return -4;
			} else if (subGridSumValid() == false) {
				return -2;
			} else {
				return 0;
			}
			

		}
	}
	
	public boolean subGridSumValid() {
		int currentSum = 0;
		boolean valid = true;

		for (int subGridNum = 0; subGridNum < 9; subGridNum++) {
			currentSum = 0;
			for (int i = 0; i < sudokuArray.length / 3; i++) {
				for (int j = 0; j < sudokuArray.length / 3; j++) {
					currentSum += sudokuArray[i][j];
				}
			}
		
			if (currentSum != 45) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	public boolean checkRowValid() {
		// Subgrid
		for (int x = 0; x < 9; x++) {
			// Row
			for (int y = 0; y < 9; y++) {
				// Column
				for (int z = 0; z < 9; z++) {
					if (y != z && sudokuArray[x][y] == sudokuArray[x][z]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	public boolean checkColumnValid() {
		// Subgrid
		for (int x = 0; x < 9; x++) {
			// Row
			for (int y = 0; y < 9; y++) {
				// Column
				for (int z = 0; z < 9; z++) {
					// Gonna access column, followed by the subgrid
					if (y != z && sudokuArray[z][x] == sudokuArray[y][x]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void createSudokuArray(String sudokuNumbers) {
		char[] numChar = sudokuNumbers.toCharArray();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) { 
				sudokuArray[i][j] = numChar[(i*9) + j]-'0';
			}
		}
	}
}