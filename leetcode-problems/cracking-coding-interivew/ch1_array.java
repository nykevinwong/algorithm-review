package Q1_01_Is_Unique;
// 獨特串. 字數一. 或布林. 用遮罩. 排序字. 鄰非等. 比各字. 增二歷..

	/* Assumes only letters a through z. */
	public static boolean isUniqueChars(String str) {
		if (str.length() > 26) { // Only 26 characters
			return false;
		}
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word));
		}
	}

package Q1_02_Check_Permutation;
// 排組串. 排序等. 字數同. 字數表. 遍歷增. 遍歷減. 負不等..
	public static String sort(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}
	
	public static boolean permutation(String s, String t) {
		return sort(s).equals(sort(t)); //  排序等. 字數同.
	}	

	public static boolean permutation(String s, String t) {
		if (s.length() != t.length()) return false; // Permutations must be same length
		
		int[] letters = new int[128]; // Assumption: ASCII // 字數表.
		for (int i = 0; i < s.length(); i++) { // 遍歷增. 
			letters[s.charAt(i)]++;
		}
		  
		for (int i = 0; i < t.length(); i++) { // 遍歷減. 
			letters[t.charAt(i)]--;
		    if (letters[t.charAt(i)] < 0) { // 負不等.
		    	return false;
		    }
		}
		  
		return true; // letters array has no negative values, and therefore no positive values either
	}
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = permutation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
		}
	}

package Q1_03_URLify;
import CtCILibrary.AssortedMethods;
// 替換字. 長不同. 計長度. 標字尾. 倒遍歷. 倒換字..
	// Assume string has sufficient free space at the end
	public static void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) { // 長不同.
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		index = trueLength + spaceCount * 2; // 計長度
		if (trueLength < str.length) str[trueLength] = '\0'; // 標字尾.
		for (i = trueLength - 1; i >= 0; i--) { // 倒遍歷. 倒換字.
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index - 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}
	
	public static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String str = "Mr John Smith    ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		replaceSpaces(arr, trueLength);	
	}

package Q1_04_Palindrome_Permutation;

public class Common {
// (Hackerank: Game of Thrones) 迴排串. 左右等. 計需字. 字數表. 有奇偶. 奇元計. 不超一. 全遍歷. 奇增偶減. 計單雙. 用開關.. 查元組. 唯一一. 自閘自減一. 得為零. 
	public static void main(String[] args) {
		String pali = "Ratzs live on no evil starz";
		System.out.println(isPermutationOfPalindrome(pali));
		String pali2 = "Zeus was deified, saw Suez";
		System.out.println(isPermutationOfPalindrome(pali2));
	}
	// approach 1
	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		
		int val = Character.getNumericValue(c);
		if (a <= val && val <= z) {
			return val - a;
		}
		return -1;
	}
	// 計需字. 字數表.
	public static int[] buildCharFrequencyTable(String phrase) {
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			if (x != -1) {
				table[x]++;
			}
		}
		return table;
	}

	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table) { 
			if (count % 2 == 1) { // 有奇偶. 
				if (foundOdd) {   // 奇元計. 不超一.
					return false;
				}
				foundOdd = true;
			}
		}
		return true;
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int[] table = Common.buildCharFrequencyTable(phrase);
		return checkMaxOneOdd(table);
	}
	// approach 2
	public static boolean isPermutationOfPalindrome(String phrase) {
		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) { // 全遍歷. 
			int x = Common.getCharNumber(c); 
			if (x != -1) {
				table[x]++;

				if (table[x] % 2 == 1) { // 奇增
					countOdd++;
				} else {
					countOdd--; // 偶減.
				}
			}
		}
		return countOdd <= 1;
	}
	

	// approach 3
	/* Toggle the ith bit in the integer. */
	//制開關 零轉一 一轉零 值小零 無法制 遮罩一 左移值 查某位 值閘罩 零或一 空或有 空設或 有閘反罩  
	public static int toggle(int bitVector, int index) { //將同一位元組的某個位元開或關
		if (index < 0) return bitVector; //值小零 無法制
		
		int mask = 1 << index; // 遮罩一 左移值
		if ((bitVector & mask) == 0) { // 查某位 值閘罩 零或一 空或有
			bitVector |= mask;   // 空設或
		} else {
			bitVector &= ~mask; // 有閘反罩 
		}
		return bitVector;
	}
	
	/* Create bit vector for string. For each letter with value i,
	 * toggle the ith bit. */
	public static int createBitVector(String phrase) {
		int bitVector = 0;
		for (char c : phrase.toCharArray()) { //  計單雙. 用開關..
			int x = Common.getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}
	
	/* Check that at most one bit is set by subtracting one from the 
	 * integer and ANDing it with the original integer. */
	public static boolean checkAtMostOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0; // 查元組 唯一一 自閘自減一 得為零    
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int bitVector = createBitVector(phrase);
		return checkAtMostOneBitSet(bitVector);
	}

package Q1_05_One_Away;
	// 單編輯. 替換別. 長度等. 一字差. 插入別. 長度增. 一字增. 刪插別. 換參位. 刪反插..
	// approach 1
	public static boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) { 
			if (s1.charAt(i) != s2.charAt(i)) {
			// 一字差異，代表S1還是可以單編輯成為S2,但二字差異就無法單編輯成為S2.
				if (foundDifference) return false;
				foundDifference = true;
			}
		}
		return true;
	}
	
	/* Check if you can insert a character into s1 to make s2. */
	public static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		//  長不同. 長短鏢. 鏢小長. 當字同. 各進一. 當字異. 長鏢進. 當再異. 鏢不等.
		// case1: 只有頭尾的一個字不同，其它相同. case2: 所有字相等，但只有一個字在不同位置.
		// 一個以上字不相等. 長短鏢. 早有差別. 可以長短差來看是不為第二次不同.
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}		
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}	
	
	public static boolean oneEditAway(String first, String second) {
	 
		if (first.length() == second.length()) { 	// 替換別. 長度等.
			return oneEditReplace(first, second);   // 一字差.
		} else if (first.length() + 1 == second.length()) { // 插入別. 長度增.
			return oneEditInsert(first, second); // 查fist是否比second多一字. 一字增.
		} else if (first.length() - 1 == second.length()) {
			return oneEditInsert(second, first); // 查second是否比second多一字.
		} 
		return false;
	}
	
	public static void main(String[] args) {
		String a = "pse";
		String b = "pale";
		boolean isOneEdit = oneEditAway(a, b);
		System.out.println(a + ", " + b + ": " + isOneEdit);
	}
	
	// approach 2
	public static boolean oneEditAway(String first, String second) {
		/* Length checks. */
		if (Math.abs(first.length() - second.length()) > 1) {
			return false;
		}
		
		/* Get shorter and longer string.*/
		String s1 = first.length() < second.length() ? first : second;
		String s2 = first.length() < second.length() ? second : first;

		int index1 = 0;
		int index2 = 0;
		boolean foundDifference = false;
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				/* Ensure that this is the first difference found.*/
				if (foundDifference) return false;
				foundDifference = true;
				if (s1.length() == s2.length()) { // On replace, move shorter pointer
					index1++;
				}
			} else {
				index1++; // If matching, move shorter pointer 
			}
			index2++; // Always move pointer for longer string 
		}
		return true;
	}
	
	public static void main(String[] args) {
		String a = "palee";
		String b = "pale";
		boolean isOneEdit1 = oneEditAway(a, b);
		System.out.println(a + ", " + b + ": " + isOneEdit1);

		String c = "pale";
		String d = "pkle";
		boolean isOneEdit2 = oneEditAway(c, d);
		System.out.println(c + ", " + d + ": " + isOneEdit2);
	}
	
package Q1_06_String_Compression;
//(LeetCode 443. String Compression) 字串縮. 全遍歷. 計字數. 底後或. 當非後. 壓字串. 重置數. 傳短串..
	// approach 1
	public static String compress(String str) {
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) { //全遍歷.
			countConsecutive++; // 計字數
			
			// 底後或. 當非後
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(str.charAt(i));    // 壓字串.
				compressed.append(countConsecutive);
				countConsecutive = 0; //重置數
			}
		}
		//傳短串
		return compressed.length() < str.length() ? compressed.toString() : str;
	}
	
	// approach 2
	public static String compress(String str) {
		int finalLength = countCompression(str);
		if (finalLength >= str.length()) return str; // 傳短串
		
        /// ... the same as above code 
		return compressed.toString();
	}
	
	public static int countCompression(String str) {
		int compressedLength = 0;
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
			
			/* If next character is different than current, append this char to result.*/
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressedLength += 1 + String.valueOf(countConsecutive).length(); // 計字數.
				countConsecutive = 0;
			}
		}
		return compressedLength;
	}		
	
	public static void main(String[] args) {
		String str = "aa";
		System.out.println(str);
		System.out.println(compress(str));
	}

package Q1_07_Rotate_Matrix;
// LeetCode 48. Rotate Image)
// 右旋矩. 外至內. 增二歷. 至一半. 初減一. 換四位. 暫存數. 順時轉. 逆時換. 逆時轉. 順時換. 遂拷位. 帶當前.  
	public static boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // Not a square
		int N = matrix.length;
		int[][] m = matrix;
		
		// 外至內 增二歷.
		for (int i = 0; i < N / 2; i++) {  // 至一半. 
			for (int j = i; j < N - i - 1; j++) { // 初減一.
	  
				int temp = a[i][j]; /// 換四位. 暫存數.
				// 順時轉. 逆時換.
				m[i][j] = m[N - j - 1][i];  // 遂拷位. 帶當前. (遂字拷貝的位置，將會使用當前迴圈值，也就是這裡的j)
				m[N - j - 1][i] = m[N - i - 1][N - j - 1]; 
				m[N - i - 1][N - j - 1] = m[j][N - i - 1]; 
				m[j][N - i - 1] = temp; 
				
				// 逆時轉. 順時換.
				/*	int temp = m[i][j];   
				m[i][j] = m[j][N-i-1]; 
				m[j][N-i-1] = m[N-i-1][N-j-1]; 
				m[N-i-1][N-j-1] = m[N-j-1][i]; 
				m[N-j-1][i] = temp; */
			} 
		} 
	
		return true;
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}

package Q1_08_Zero_Matrix;
import CtCILibrary.AssortedMethods;
// 零矩陣. 全遍歷. 遇零位. 記列標. 記欄標. 遍列標. 整列零. 遍欄標. 整欄零.
// 零矩陣. 全遍歷. 遇零位. 四方值. 皆他值. 遍歷遇. 設為零..

	public static void nullifyRow(int[][] matrix, int row) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}		
	}

	public static void nullifyColumn(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}		
	}			
	
	public static void setZeros(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];	
		boolean[] column = new boolean[matrix[0].length];

		// Store the row and column index with value 0
		// 零矩陣. 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length;j++) {
				if (matrix[i][j] == 0) { // 遇零位. 
					row[i] = true;  // 記列標. 
					column[j] = true; // 記欄標.
		 		}
			}
		}
		
		// Nullify rows
		for (int i = 0; i < row.length; i++) {
			if (row[i]) { // 遍列標. 
				nullifyRow(matrix, i); // 整列零.
			}
		}
		
		// Nullify columns
		for (int j = 0; j < column.length; j++) {
			if (column[j]) { // 遍欄標. 
				nullifyColumn(matrix, j); // 整欄零.
			}
		}
	}	
	
	public static boolean matricesAreEqual(int[][] m1, int[][] m2) {
		if (m1.length != m2.length || m1[0].length != m2[0].length) {
			return false;
		}
		
		for (int k = 0; k < m1.length; k++) {
			for (int j = 0; j < m1[0].length; j++) {
				if (m1[k][j] != m2[k][j]) {
					return false;
				}
			}
		}	
		return true;
	}
	
	public static int[][] cloneMatrix(int[][] matrix) {
		int[][] c = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				c[i][j] = matrix[i][j];
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		int nrows = 10;
		int ncols = 15;
		int[][] matrix = AssortedMethods.randomMatrix(nrows, ncols, -10, 10);		

		AssortedMethods.printMatrix(matrix);
		setZeros(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}
}

package Q1_08_Zero_Matrix;
import CtCILibrary.AssortedMethods;

	public static void nullifyRow(int[][] matrix, int row) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}		
	}

	public static void nullifyColumn(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}		
	}		
	
	public static void setZeros(int[][] matrix) {
		boolean rowHasZero = false;
		boolean colHasZero = false;		
		
		// Check if first row has a zero
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				rowHasZero = true;
				break;
			}
		}		
		
		// Check if first column has a zero
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				colHasZero = true;
				break;
			}
		}		
		
		// Check for zeros in the rest of the array
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length;j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
		 		}
			}
		}		
		
		// Nullify rows based on values in first column
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				nullifyRow(matrix, i);
			}
		}		
		
		// Nullify columns based on values in first row
		for (int j = 1; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				nullifyColumn(matrix, j);
			}
		}	
		
		// Nullify first row
		if (rowHasZero) {
			nullifyRow(matrix, 0);
		}
		
		// Nullify first column
		if (colHasZero) {
			nullifyColumn(matrix, 0);
		}
	}	

package Q1_09_String_Rotation;

	public static boolean isSubstring(String big, String small) {
		return (big.indexOf(small) >= 0);
	}
	
	public static boolean isRotation(String s1, String s2) {
	    int len = s1.length();
	    /* check that s1 and s2 are equal length and not empty */
	    if (len == s2.length() && len > 0) { 
	    	/* concatenate s1 and s1 within new buffer */
	    	String s1s1 = s1 + s1;
	    	return isSubstring(s1s1, s2);
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
	}