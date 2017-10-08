import java.io.*;
public class AVLTree {
	protected AVLNode rootnode; // define root node
	class AVLNode {
		public AVLNode left;
		public AVLNode right;
		public AVLNode root;
		public int value;
		public int balance;
		public AVLNode(int k) {
			left = right = root = null;
			balance = 0;
			value = k;
		}
		public String toString() {
			return "" + value;
		}
	}
	
	// Insert mehod. k is element to insert into the tree
	public void insert(int k) {
		// create new node
		AVLNode n = new AVLNode(k);
		// recursively call insert mehod
		insertAVL(this.rootnode, n);
	}
	//t : The AVLNode to evaluate for recursive insertion
	public void insertAVL(AVLNode t, AVLNode x) {
		if (t == null) {
			this.rootnode = x;
		} else {
			if (x.value < t.value) {
				if (t.left == null) {
					t.left = x;
					x.root = t;
					// check for violation condition
					reBalance(t);
				} else {
					insertAVL(t.left, x);
				}
			} else if (x.value > t.value) {
				if (t.right == null) {
					t.right = x;
					x.root = t;
					// check for violation condition
					reBalance(t);
				} else {
					insertAVL(t.right, x);
				}
			} else {
			}
		}
	}
	
	public void delete(int k) {
		findAVL(this.rootnode,k);
		
	}
	
	public void findAVL(AVLNode t,int k){
		AVLNode n = t;
		if(k == n.value){
			deleteAVL(n);
		} else if( k > n.value) {
		//if value of k is higher than current node 
		//look for node directly right of it
			findAVL(n.right, k);
		} else {
		//if value of k is higher than current node 
		//look for node directly left of it
			findAVL(n.left, k);
		}
	}
	
	public void deleteAVL (AVLNode n){
		if(n.right!= null){
			if(n.right.left != null){
				n.root = n.right.left;
				n.right.left = null;
				reBalance(n);
			} else{
				n.root = n.right;
				n.right = null;
				reBalance(n);
			}
		} else if(n.left!= null){
			if(n.left.right != null){
				n.root = n.left.right;
				n.left.right = null;
				reBalance(n);
			} else{
				n.root = n.left;
				n.left = null;
				reBalance(n);
			}
		} else{
			n.root = null;
			reBalance(n);
		}
	}
	// balance the violating subtree
	public void reBalance(BSTNode avl) {
		setBalance(avl);
		int balance = avl.balance;
		if (balance == -2) {
			if (height(avl.left.left) >= height(avl.left.right)) {
				avl = singleRotateWithRight(avl);
			} else {
				avl = doubleRotateWithRight(avl);
			}
		} else if (balance == 2) {
			if (height(avl.right.right) >= height(avl.right.left)) {
				avl = singleRotateWithLeft(avl);
			} else {
				avl = doubleRotateRightLeft(avl);
			}
		}
		if (avl.root != null) {
			reBalance(avl.root);
		} else {
			this.rootnode = avl;
			System.out.println("AVL Tree is balanced\n\n");
		}
	}
	
	public AVLNode singleRotateWithLeft(BSTNode t) {
		AVLNode t1 = t.right;
		t1.root = t.root;
		t.right = t1.left;
		if (t.right != null) {
			t.right.root = t;
		}
		t1.left = t;
		t.root = t1;
		if (t1.root != null) {
			if (t1.root.right == t) {
				t1.root.right = t1;
			} else if (t1.root.left == t) {
				t1.root.left = t1;
			}
		}
		setBalance(t);
		setBalance(t1);
		return t1;
	}
	
	public AVLNode singleRotateWithRight(BSTNode t) {
		AVLNode t1 = t.left;
		t1.root = t.root;
		t.left = t1.right;
		if (t.left != null) {
			t.left.root = t;
		}
		t1.right = t;
		t.root = t1;
		if (t1.root != null) {
			if (t1.root.right == t) {
				t1.root.right = t1;
			} else if (t1.root.left == t) {
				t1.root.left = t1;
			}
		}
		setBalance(t);
		setBalance(t1);
		return t1;
	}
	
	
	public AVLNode doubleRotateWithRight(BSTNode k3) {
		k3.left = singleRotateWithLeft(k3.left);
		return singleRotateWithRight(k3);
	}
	
	public AVLNode doubleRotateRightLeft(BSTNode k3) {
		k3.right = singleRotateWithRight(k3.right);
		return singleRotateWithLeft(k3);
	}
	//height() method, which is used in calculating the balance factor between two subtrees of a parent AVLNode
	private int height(BSTNode avl) {
		if (avl == null) {
			return -1;
		}
		if (avl.left == null && avl.right == null) {
			return 0;
		} else if (avl.left == null) {
			return 1 + height(avl.right);
		} else if (avl.right == null) {
			return 1 + height(avl.left);
		} else {
			return 1 + maximum(height(avl.left), height(avl.right));
		}
	}
	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}
	
	// check the violation through difference of height of left and right
	private void setBalance(BSTNode avl) {
		avl.balance = height(avl.right) - height(avl.left);
	}
	
	// Returns tree in INORDER traversal
	private void inOrder(AVLNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.value);
			inOrder(node.right);
		}
	}
	
	void inOrder() {
		inOrder(rootnode);
	}
	
	// Check for Tree is Empty or not
	boolean isEmpty() {
		return rootnode == null;
	}
	
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = -1;
		int value;
		
		while (true) {
			System.out.println("Select an option");// Evertime user have to
												   // select one option
			System.out.println("1-Insert a node"); // At a time user can insert
												   // only one node
			System.out.println("2-Remove a node"); // At a time user can remove
												   // only one node
			System.out.println("3-Display tree in INORDER Traversal");
			System.out.println("0-EXIT");
			try {
				choice = Integer.valueOf(inputReader.readLine()).intValue();
				switch (choice) {
					case 0:
					System.out.println("Exit");
					return;
					
					case 1:
					System.out.println("Enter a value to insert");
					value = Integer.parseInt(inputReader.readLine());
					tree.insert(value);
					break;
					
					case 2:
					//System.out.println("It's your Assignment. Do code for deletion.");
					System.out.println("Enter a value to delete");
					value = Integer.parseInt(inputReader.readLine());
					tree.delete(value);
					break;
					
					case 3:
					if (tree.isEmpty()) {
						System.out.println("Sorry....in order traversal not possible..AVL Tree is empty!\n\n");
					} else {
						System.out.println("The inorder Traversal of AVL Tree is:");
						tree.inOrder();
						System.out.println();
					}
					break;
					default:
					System.out.println("Invalid option. \n Try Again!!!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Incorrect choice");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
