/*	Author: Chintan Parikh
 * 	
 * 	COEN:281 Pattern Recognition and Data Mining. 
 * 
 * 	Programming Assignment #1.
 *	
 *	Approach: Pose this problem as a DFS problem and try to create a path from root to leaf and store the path 
 *	in an Arraylist and then store all paths using another arraylist. 
 *	Use a recursive method to visit all the nodes until desired node is reached and measure 
 *	the distance between the desired leaf and the root and show the path from the leaf to the node.
 */


import java.util.ArrayList;
import java.util.*;

public class P1  {
	
	//Creating a class Payload to store each path in an arrayList after each iteration of the recursive function along with String s and a deviation d.
	//Use deviation to handle change in size of string after deletion and insertion.
	public class Payload {
		String s;
		StringBuilder sb;
		ArrayList<String> path = new ArrayList<String>();
		int d= 0;
	}
	//calculate minimum distance by computing matrix
	public void Edit_Distance(String a, String b) {
		int Cost = 0;
		char[] x = a.toCharArray();
		char[] y = b.toCharArray();
		int m = x.length;
		int n = y.length;
		int d[][] = new int[m+1][n+1];
		for(int i=0; i <= m; i++){
				d[i][0] = i;
		}
		for(int i=0; i <= n; i++){
				d[0][i] = i;
		}
		for (int i = 1; i <= a.length() ;i++){	
			for (int j = 1 ; j <= b.length(); j++){	
				if (a.charAt(i - 1) == b.charAt(j - 1)){//check if values are equal or not,
					Cost = 0;
			    }
			    else{
			        Cost = 1;
			    }
			    d[i][j] = minimum(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1] + Cost);  // substitution			 
			}
		}
		
		ArrayList<Payload> childPaths = traverseDFS(d,a.length(),b.length(),a,b);
		
		System.out.println("There are total of " + childPaths.size() +" sequences");
		// check for all paths and print them one by one.
		for(Payload path : childPaths) {	
			System.out.print(a);
			for(String s : path.path){
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
	
		public int minimum (int a, int b,int c) {
			int min = a;
			if(a>b)
				min=b;
			if(min>c)
				min=c;
			return min;
		}
		// In this recursive function,there are 2 conditions :first is terminating condition and the second is to find the children/child of that node. 
		public ArrayList<Payload> traverseDFS(int[][] matrix,int i,int j,String A,String B) {	
			//termination conditions (found node)
			if(i==0 && j==0){
				ArrayList<Payload> paths = new ArrayList<Payload>();
				Payload path = new Payload();
				path.sb = new StringBuilder(A);
				path.d = 0;
				path.path = new ArrayList<String>();
				paths.add(path);
				return paths;
			}
			
			//for each child
			ArrayList<Payload> paths = new ArrayList<Payload>();
			if(i-1>=0 && j-1>=0 && A.charAt(i-1) == B.charAt(j-1)){
				ArrayList<Payload> childPaths = traverseDFS(matrix,i-1,j-1,A,B);
				return childPaths;
			} 
			else {
				// Check from top row
				if(i-1 >= 0 && matrix[i-1][j] + 1 == matrix[i][j]) {
					ArrayList<Payload> childPaths = traverseDFS(matrix,i-1,j,A,B);
					for(Payload path : childPaths) { 
						path.sb.deleteCharAt(i-1+path.d);
						path.d--;
						path.path.add(" delete " + A.charAt(i-1)+" -> " + path.sb.toString());
					}
					paths.addAll(childPaths);
				}
				// Check from left column
				if(j-1 >= 0 && matrix[i][j-1] + 1 == matrix[i][j]) {
					ArrayList<Payload> childPaths = traverseDFS(matrix,i,j-1,A,B);
					for(Payload path : childPaths) {
						path.sb.insert(i+path.d,B.charAt(j-1));
						path.d++;
						path.path.add( " insert " + B.charAt(j-1) +"->"+ path.sb.toString());
					}
					paths.addAll(childPaths);
				}
				// Check from diagonal
				if(i-1 >= 0 && j-1>=0 && matrix[i-1][j-1] + 1 == matrix[i][j]) {
					ArrayList<Payload> childPaths = traverseDFS(matrix,i-1,j-1,A,B); 
					for(Payload path : childPaths) {
						path.sb.setCharAt(i-1+path.d,B.charAt(j-1));
						path.path.add(  " replace " + A.charAt(i-1) + " by " + B.charAt(j-1)+ "->" + path.sb.toString());
					}
					paths.addAll(childPaths);
				}
			}
			return paths; //return all paths.
		}
	
		public static void main(String args[]){
			Scanner in = new Scanner(System.in);
			//error handling
			int totalWords = 0;
			String[] inputs = new String[2];
			while(in.hasNext()) {
				String line = in.nextLine();
				if(line.length() > 0) {
					if(totalWords > 1) {
						System.err.println("Too many strings. Please provide only two strings");
						return;
					}
					inputs[totalWords] = line;
					totalWords++;
				}
			}
			if(totalWords == 1) {
				String a = inputs[0];
				String b = "";
				P1 distance = new P1(); 
				distance.Edit_Distance(a, b);
			
			}
			else {
			String a = inputs[0];
			String b = inputs[1];
				if(!a.matches("[a-zA-Z]+")||(!b.matches("[a-zA-Z]+"))){
					System.out.println("Please Enter Valid Input");
				}
				else {
					P1 distance = new P1(); 
					distance.Edit_Distance(a, b);
				}
			in.close();
				
		}
}
}
