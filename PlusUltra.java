import java.util.*;
public class PlusUltra{
	public static int mCounter=0;
	public static void main(String[] args) {
		Scanner keyb = new Scanner(System.in);
		boolean arrayIn = false;
		boolean exit = false;
		String ans = "0";
		int key = -1;
		ArrayList<Integer> sorted = new ArrayList<Integer>();

		while(!exit){
			System.out.println("Please make a menu selection: \n"+
				"1: Sort an array from input.\n"+
				"2: Search recently sorted array for value.\n"+
				"3: Exit.\n");
			ans = keyb.next();
			
			if(ans.equals("1")){
				arrayIn = false;
				int num[] = inputArray(keyb);
				System.out.println("Array length is "+num.length+
					"\nWith last index at "+(num.length - 1));
				for(int i=0;i<num.length;i++){
					sorted.add(num[i]);
					System.out.print(sorted.get(i)+",");
				}
				System.out.println();
				arrayIn = true;
			}
			else if(ans.equals("2")){
				if (arrayIn){
					System.out.println("What is the number you're looking for? ");
					key = keyb.nextInt();
					recursiveBinarySearch(sorted,key, 0,(sorted.size()-1));
					//binarySearch(sorted,keyb);
				}
				else{
					System.out.println("Please use option one to create and sort an array first.\n");
				}
			}
			else if(ans.equals("3")){
				exit = true;
			}
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
		 System.out.println("Split complete(mergeSort) From:"+numbers[i]+" to "+numbers[k]);

		 // Merge left and right partition in sorted order  
		 merge(numbers, i, j, k);
		}
	}


	public static void binarySearch(ArrayList<Integer> numbers, Scanner keyb){
		int i = 0;
		int k = numbers.size() - 1;
		int j = 0;
		boolean found = false;

		System.out.println("What is the number you're looking for? ");
		int search = keyb.nextInt();

		while (i <= k && !found){
			System.out.println("Current mid point: "+j);
			System.out.println("searching...");
			j = (i + k) / 2;
			if(numbers.get(j) < search){
				i = j + 1;
			}
			else if(numbers.get(j) > search){
				k = j - 1;
			}
			else{
				System.out.println("Value found at index "+j);
				found = true;
			}
		}
	}

	// the redundance is for academic analysis
	public static void recursiveBinarySearch(ArrayList<Integer> numbers, int key, int i, int k){
		System.out.println("searching...");
		int j = (i + k) / 2;

		if (numbers.get(j) == key){
			// if midpoint "j" is equal to key, recursion will cease 
			System.out.println("Value found at index "+j);
		}
		else if(i != k){
			//recusively split array until match is found
			recursiveBinarySearch(numbers,key,(j + 1),k);
			recursiveBinarySearch(numbers,key,i,(j - 1));
		}
		else{
			System.out.println("Value is not in array.");
		}

	}

	public static int[] inputArray(Scanner keyb){
		//get array from input
		System.out.print("Enter an array separated with element separated by commas: ");
		String input=keyb.next();
		System.out.println();

		//spit input into array
		String parts[] = input.split(",");
		//initialize an integer array that is the same length as the inputed array
		int numbers[] = new int[parts.length];

		//parse each element of "parts" to int value and set elements of "numbers" to those values at the corresponding indexes 
		//print each value of numbers to test
		for(int i = 0;i < parts.length;i++){
			numbers[i] = Integer.parseInt(parts[i]);
			//System.out.print(numbers[i]+",");
		}

		//run numbers through the merge sort
		mergeSort(numbers,0,numbers.length-1);

		//print the sorted array
		for(int i = 0; i<numbers.length; i++){
			//System.out.print(numbers[i]+",");
		}
		System.out.println();
		return numbers;
	}
}
