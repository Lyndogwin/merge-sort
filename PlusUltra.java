import java.util.*;
public class PlusUltra{
	public static int mCounter=0;
	public static void main(String[] args) {
		//get array from input
		Scanner keyb=new Scanner(System.in);
		System.out.print("Enter an array separated with element separated by commas: ");
		String input=keyb.next();
		System.out.println();

		//spit input into array
		String parts[] = input.split(",");
		//initialize an integer array that is the same length as the inputed array
		int numbers[] = new int[parts.length];

		//parse each element of "parts" to int value and set elements of "numbers" to those values at the corresponding indexes 
		//print each value of numbers to test
		for(int i=0;i<parts.length;i++){
			numbers[i]=Integer.parseInt(parts[i]);
			//System.out.print(numbers[i]+",");
		}

		//run numbers through the merge sort
		mergeSort(numbers,0,numbers.length-1);

		//print the sorted array
		for(int i=0;i<numbers.length;i++){
			System.out.print(numbers[i]+",");
		}
	}

	public static void merge(int numbers[],int i,int j,int k){
		int mergedSize = k - i + 1;       // Size of merged partition
		int mergedNumbers [] = new int[mergedSize]; // Temporary array for merged numbers
		int mergePos = 0;                 // Position to insert merged number
		int leftPos = 0;                  // Position of elements in left partition
		int rightPos = 0;                 // Position of elements in right partition

		leftPos = i;                      // Initialize left partition position
		rightPos = j + 1;                 // Initialize right partition position

		// Add smallest element from left or right partition to merged numbers
		// This first while loop is the crux of the merge algorithm
		// This first loop will run while both partitions are non-empty
		while (leftPos <= j && rightPos <= k) {
			if (numbers[leftPos] < numbers[rightPos]) {
				mergedNumbers[mergePos] = numbers[leftPos];//The lesser number is added at index 0 of merged numbers
				++leftPos;
			} 
			else {
				mergedNumbers[mergePos] = numbers[rightPos];
				++rightPos;
			}
			++mergePos;
		}

		// If left partition is not empty, add remaining elements to merged numbers
		while (leftPos <= j) {
			mergedNumbers[mergePos] = numbers[leftPos];
			++leftPos;
			++mergePos;
		}

		// If right partition is not empty, add remaining elements to merged numbers
		while (rightPos <= k) {
			mergedNumbers[mergePos] = numbers[rightPos];
			++rightPos;
			++mergePos;
		}

		// Copy merge number back to numbers
		for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
			numbers[i + mergePos] = mergedNumbers[mergePos];
			System.out.println(numbers[i + mergePos]);
		}
		System.out.print("---------------------------");
		mCounter++;
		System.out.println(mCounter);
   
	}

	public static void mergeSort(int numbers [], int i, int k){
		int j = 0;

		if (i < k) {
		 j = (i + k) / 2;  // Find the midpoint in the partition

		 // Recursively sort left and right partitions
		 mergeSort(numbers, i, j);
		 mergeSort(numbers, j + 1, k);

		 // Merge left and right partition in sorted order
		 // ------does this run asynchronously while mergeSort() is recursively called--???????????????  
		 merge(numbers, i, j, k);
		}
	}

}